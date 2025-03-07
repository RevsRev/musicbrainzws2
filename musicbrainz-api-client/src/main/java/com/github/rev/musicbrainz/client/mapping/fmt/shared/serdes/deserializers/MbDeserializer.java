package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.DeserializerWithClass;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
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
    private final BadKeyGroupHandler<T>[] handlers;
    private final ObjectMapper mapper;

    private MbDeserializer(final Class<T> clazz,
                           final Supplier<T> constructor,
                           final ObjectMapper mapper,
                           final BadKeyGroupHandler<T>[] handlers) {
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
        T update = mapper.convertValue(treeNode, clazz);
        return mapper.updateValue(instance, update);
    }

    /**
     * Factory method.
     * @param clazz
     * @param handlers
     * @return a new MbDeserializer instance.
     * @param <T>
     */
    public static <T> MbDeserializer<T> factory(final Class<T> clazz,
                                                final BadKeyGroupHandler<T>... handlers) {
        return factory(clazz, Collections.emptyList(), handlers);
    }

    /**
     * Factory method.
     * @param clazz
     * @param nestedSerializers Additional deserializers to be used on nested objects.
     * @param handlers
     * @return a new MbDeserializer instance.
     * @param <T>
     */
    public static <T> MbDeserializer<T> factory(final Class<T> clazz,
                                                final List<DeserializerWithClass> nestedSerializers,
                                                final BadKeyGroupHandler<T>... handlers) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
             ObjectMapper om = new ObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
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
}
