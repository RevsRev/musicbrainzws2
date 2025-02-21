package com.github.rev.musicbrainz.client.mapping.serdes.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.rev.musicbrainz.client.mapping.serdes.handler.IgnoringProblemHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * JsonDeserializer for handling entities that do not conform nicely to the spec provided by the MusicBrainz API.
 * @param <T> The type of class this deserializer handles.
 */
public final class BadKeyDeserializer<T> extends JsonDeserializer<T> {

    private final Class<T> clazz;
    private final BiConsumer<T, JsonNode> badKeyHandler;

    private final ObjectMapper happyOm = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private final ObjectMapper unhappyOm;

    private BadKeyDeserializer(final Class<T> clazz,
                               final BiConsumer<T, JsonNode> badKeyHandler,
                               final ObjectMapper unhappyOm) {
        this.clazz = clazz;
        this.badKeyHandler = badKeyHandler;
        this.unhappyOm = unhappyOm;
    }

    @Override
    public T deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);
        try {
            return happyOm.convertValue(node, clazz);
        } catch (Exception e) {
            T converted = unhappyOm.convertValue(node, clazz);
            badKeyHandler.accept(converted, node);
            return converted;
        }
    }

    /**
     * Factory method for constructing bad key deserializers.
     * @param clazz Class to be deserialized.
     * @param badKeyName Name of the bad key that is encountered during parsing.
     * @param setterName Name of the method that should be invoked to set the data corresponding to the bad key.
     * @return A new BadKeyDeserializer instance.
     * @param <T> Type of the class being deserialized.
     */
    public static <T> BadKeyDeserializer<T> factory(final Class<T> clazz,
                                                    final String badKeyName,
                                                    final String setterName) {
        final Method method;
        try {
            method = clazz.getMethod(setterName, String.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }

        ObjectMapper badKeyOm = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
                .addHandler(new IgnoringProblemHandler(Set.of(badKeyName)))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return new BadKeyDeserializer<>(clazz, (t, node) -> {
            try {
                method.invoke(t, node.get(badKeyName).textValue());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }, badKeyOm);
    }
}
