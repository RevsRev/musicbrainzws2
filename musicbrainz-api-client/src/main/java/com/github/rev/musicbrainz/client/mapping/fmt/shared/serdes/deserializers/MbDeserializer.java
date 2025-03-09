package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.rev.musicbrainz.client.mapping.fmt.json.JsonNameStrategy;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.DeserializerWithClass;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * One deserializer class to rule them all.
 * This class works by giving the majority of the work to the underlying parsing library (jackson), with custom handlers
 * for the tricky cases that jackson cannot deal with.
 * @param <T> The type that this deserializer deserializes to.
 */
public final class MbDeserializer<T> extends JsonDeserializer<T> implements DeserializerWithClass {

    private final Class<T> clazz;
    private final Supplier<T> constructor;
    private final Collection<BadKeyGroupHandler<T>> handlers;
    private final ObjectMapper mapper;

    private MbDeserializer(final Class<T> clazz,
                           final Supplier<T> constructor,
                           final ObjectMapper mapper,
                           final Collection<BadKeyGroupHandler<T>> handlers) {
        this.clazz = clazz;
        this.constructor = constructor;
        this.mapper = mapper;
        this.handlers = handlers;
    }

    @Override
    public T deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectNode treeNode = p.getCodec().readTree(p);
        T instance = constructor.get();
        for (BadKeyGroupHandler<T> handler: handlers) {
            handler.accept(instance, treeNode, mapper);
        }
        return mapper.updateValue(instance, treeNode);
    }

    private static <T> MbDeserializer<T> factory(final Class<T> clazz,
                                                 final Collection<DeserializerWithClass> nestedSerializers,
                                                 final Collection<BadKeyGroupHandler<T>> handlers,
                                                 final Map<String, String> mappedKeys) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
             ObjectMapper om = new ObjectMapper()
                    .setPropertyNamingStrategy(new JsonNameStrategy(mappedKeys))
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            SimpleDeserializers deserializers = new SimpleDeserializers();
            for (DeserializerWithClass nestedSerializer : nestedSerializers) {
                nestedSerializer.addToDeserializers(deserializers);
            }
            SimpleModule m = new SimpleModule();
            m.setDeserializers(deserializers);
            om.registerModule(m);

            return new MbDeserializer<>(clazz, asSupplier(constructor), om, handlers);
        } catch (NoSuchMethodException e) {
            //TODO - Perhaps a different exception here?
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDeserializers(final SimpleDeserializers deserializers) {
        deserializers.addDeserializer(clazz, this);
    }

    private static <T> Supplier<T> asSupplier(final Constructor<T> constructor) {
        return () -> {
            try {
                return constructor.newInstance();
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                //TODO - Perhaps a different exception here?
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Builder class.
     * @param <T> The target type of deserialization.
     */
    public static class Builder<T> {
        private final Class<T> clazz;

        private final Collection<Builder<?>> nestedDeserializerBuilders = new ArrayList<>();
        private final Collection<DeserializerWithClass> nestedDeserializers = new ArrayList<>();
        private final Collection<BadKeyGroupHandler<T>> badKeyGroupHandlers = new ArrayList<>();
        private final Map<String, String> mappedKeys = new HashMap<>();

        /**
         * Constructor.
         * @param clazz
         */
        public Builder(final Class<T> clazz) {
            this.clazz = clazz;
        }

        /**
         * Build the deserializer.
         * @return A new deserializer instance.
         */
        public MbDeserializer<T> build() {
            for (Builder<?> nestedDeserializerBuilder : nestedDeserializerBuilders) {
                nestedDeserializers.add(nestedDeserializerBuilder.build());
            }
            return MbDeserializer.factory(clazz, nestedDeserializers, badKeyGroupHandlers, mappedKeys);
        }

        /**
         * Add a nested deserializer.
         * @param nested
         * @return this.
         * @param <N>
         */
        public <N> Builder<T> withNestedDeserializer(final MbDeserializer.Builder<N> nested) {
            nestedDeserializerBuilders.add(nested);
            return this;
        }

        /**
         * Add a nested deserializer.
         * @param deser
         * @return this.
         */
        public Builder<T> withNestedDeserializer(final DeserializerWithClass deser) {
            nestedDeserializers.add(deser);
            return this;
        }

        /**
         * Map a key from the source being parsed to a set method on the POJO.
         * @param source
         * @param target
         * @return this.
         */
        public Builder<T> withMappedKey(final String source, final String target) {
            mappedKeys.put(target, source);
            return this;
        }

        /**
         * Ignore a field during parsing.
         * @param fieldName
         * @return this.
         */
        public Builder<T> ignoringField(final String fieldName) {
            MissingFieldNameHandler<T> handler = MissingFieldNameHandler.factory(clazz, fieldName);
            return withHandler(handler);
        }

        /**
         * Map a set of fields to a single field on the target.
         * @param objectSetter The field on T to set.
         * @param objectClazz The type R that the set method on T calls.
         * @param fieldNameToSetters Methods to map from fields on the source being parsed to methods on R.
         * @return this.
         * @param <R> The type of object being invoked by the set method on T.
         */
        public <R> Builder<T> withFieldsToObjectHandler(final String objectSetter,
                                                        final Class<R> objectClazz,
                                                        final Map<String, Pair<String, Class<?>>> fieldNameToSetters) {
            FieldsToObjectHandler<T, R> handler =
                    FieldsToObjectHandler.factory(clazz, objectSetter, objectClazz, fieldNameToSetters);
            return withHandler(handler);
        }

        /**
         * Add specific handling for MusicBrainz "List" types.
         * @param sourceKeyName
         * @param setListMethodName
         * @param musicBrainzListEntityClazz
         * @param targetKeyName
         * @return this.
         * @param <R> The type of music brainz list entity, e.g. "ArtistList".
         */
        public <R> Builder<T> withArrayHandler(final String sourceKeyName,
                                               final String targetKeyName,
                                               final String setListMethodName,
                                               final Class<R> musicBrainzListEntityClazz) {
            ArrayHandler<T, R> handler = ArrayHandler.factory(clazz,
                    sourceKeyName,
                    targetKeyName,
                    setListMethodName,
                    musicBrainzListEntityClazz);
            return withHandler(handler);
        }

        private Builder<T> withHandler(final BadKeyGroupHandler<T> badKeyGroupHandler) {
            badKeyGroupHandlers.add(badKeyGroupHandler);
            return this;
        }
    }
}
