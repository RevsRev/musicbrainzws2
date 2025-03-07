package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.MbSerdesException;

/**
 * BadKeyGroupHandler to ignore field names that do not appear in the schema but are returned by the API.
 * @param <T>
 */
public final class MissingFieldNameHandler<T> extends BadKeyGroupHandler<T> {

    private MissingFieldNameHandler(final Class<T> clazz,
                                    final String missingKeyName) {
        super(clazz, missingKeyName);
    }

    @Override
    public void acceptImpl(final T t, final JsonNode jsonNode, final ObjectMapper mapper) throws MbSerdesException {
    }

    /**
     * Factory method.
     * @param clazz
     * @param missingKeyName
     * @return A new MissingFieldNameHandler instance.
     * @param <T>
     */
    public static <T> MissingFieldNameHandler<T> factory(final Class<T> clazz, final String missingKeyName) {
        return new MissingFieldNameHandler<>(clazz, missingKeyName);
    }
}
