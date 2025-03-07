package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.MbSerdesException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MismatchFieldNameHandler<T> extends BadKeyGroupHandler<T> {

    private final String badKeyName;
    private final Method setterMethod;
    private final Class<?> setMethodClazz;

    private MismatchFieldNameHandler(final Class<T> clazz,
                                     final String badKeyName,
                                     final Method setterMethod,
                                     final Class<?> setMethodClazz) {
        super(clazz, badKeyName);
        this.badKeyName = badKeyName;
        this.setterMethod = setterMethod;
        this.setMethodClazz = setMethodClazz;
    }

    @Override
    public void acceptImpl(final T t, final JsonNode jsonNode, final ObjectMapper mapper) throws MbSerdesException {
        try {
            setterMethod.invoke(t, mapper.convertValue(jsonNode.get(badKeyName), setMethodClazz));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MbSerdesException(e);
        }
    }

    /**
     * Factory method.
     * @param clazz
     * @param badKeyName
     * @param setterName
     * @return A new MismatchFieldNameHandler instance
     * @param <T>
     */
    public static <T> MismatchFieldNameHandler<T> factoryString(final Class<T> clazz,
                                                                final String badKeyName,
                                                                final String setterName) {
        return factory(clazz, badKeyName, setterName, String.class);
    }

    /**
     * Factory method.
     * @param clazz
     * @param badKeyName
     * @param setterName
     * @param targetClazz The target type of the set method.
     * @return A new MismatchFieldNameHandler instance
     * @param <T>
     */
    public static <T> MismatchFieldNameHandler<T> factory(final Class<T> clazz,
                                                          final String badKeyName,
                                                          final String setterName,
                                                          final Class<?> targetClazz) {
        Method setMethod = getSetMethod(clazz, setterName, targetClazz);
        return new MismatchFieldNameHandler<>(clazz, badKeyName, setMethod, targetClazz);
    }

    private static <T> Method getSetMethod(final Class<T> clazz, final String setterName, final Class<?> setterClazz) {
        try {
            return clazz.getMethod(setterName, setterClazz);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
