package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.MbSerdesException;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Some MusicBrainz API results do not have the same structure as the schema.
 * e.g. in the schema we have:
 *
 * Gender {
 *     id,
 *     content
 * }
 *
 * But MusicBrainz returns:
 *
 * Artist {
 *     ...
 *     gender: male
 *     gender-id: xyz
 *     ...
 * }
 *
 * This class is used to group the fields that should be encapsulated in the schema defined pojo ("Gender") from the
 * returned result.
 *
 * @param <T> The object containing the fields to be mapped ("Artist").
 * @param <R> The class of the POJO that the fields should be encapsulated in ("Gender").
 */
public final class FieldsToObjectHandler<T, R> extends BadKeyGroupHandler<T> {

    private final Method setter;
    private final Supplier<R> constructor;
    private final Map<String, Pair<Method, Class<?>>> objectSetters;

    private FieldsToObjectHandler(final Class<T> clazz,
                                  final Method setter,
                                  final Supplier<R> constructor,
                                  final Map<String, Pair<Method, Class<?>>> objectSetters) {
        super(clazz, objectSetters.keySet().toArray(new String[]{}));
        this.setter = setter;
        this.constructor = constructor;
        this.objectSetters = objectSetters;
    }

    @Override
    protected void acceptImpl(final T t,
                              final JsonNode jsonNode,
                              final ObjectMapper mapper) throws MbSerdesException {
        try {
            R objectInstance = constructor.get();
            for (Map.Entry<String, Pair<Method, Class<?>>> fieldNameToMethod : objectSetters.entrySet()) {
                String fieldName = fieldNameToMethod.getKey();
                Method objectSetter = fieldNameToMethod.getValue().getLeft();
                Class<?> type = fieldNameToMethod.getValue().getRight();
                objectSetter.invoke(objectInstance, mapper.convertValue(jsonNode.get(fieldName), type));
            }
            setter.invoke(t, objectInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new MbSerdesException(e);
        }
    }

    /**
     * Factory method.
     * @param clazz
     * @param objectSetter set the instance of R on T.
     * @param objectClazz
     * @param fieldNameToSetters the fieldNames on the returned (JSON) result, and the corresponding setter method they
     *                           must invoke on R.
     * @return The instance of the handler.
     * @param <T>
     * @param <R>
     */
    @SuppressWarnings("checkstyle.LineLength")
    public static <T, R> FieldsToObjectHandler<T, R> factory(final Class<T> clazz,
                                                             final String objectSetter,
                                                             final Class<R> objectClazz,
                                                             final Map<String, Pair<String, Class<?>>> fieldNameToSetters) {
        try {
            Supplier<R> supplier = asSupplier(objectClazz.getConstructor());
            Map<String, Pair<Method, Class<?>>> objectSetters = asSetters(objectClazz, fieldNameToSetters);
            Method setter = getSetter(clazz, objectSetter, objectClazz);
            return new FieldsToObjectHandler<>(clazz, setter, supplier, objectSetters);
        } catch (NoSuchMethodException e) {
            //TODO - Error handling?
            throw new RuntimeException(e);
        }
    }

    private static <T> Method getSetter(final Class<T> parentClazz,
                                        final String setterName,
                                        final Class<?> setClazz) {
        try {
            return parentClazz.getMethod(setterName, setClazz);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static <R> Map<String, Pair<Method, Class<?>>> asSetters(final Class<R> objectClazz,
                                                 final Map<String, Pair<String, Class<?>>> fieldNameToSetters) {
        Map<String, Pair<Method, Class<?>>> settersMap = new HashMap<>();
        for (Map.Entry<String, Pair<String, Class<?>>> fieldAndSetterName : fieldNameToSetters.entrySet()) {
            String fieldName = fieldAndSetterName.getKey();
            Pair<String, Class<?>> setterAndType = fieldAndSetterName.getValue();
            Method setter = getSetter(objectClazz, setterAndType.getLeft(), setterAndType.getRight());
            settersMap.put(fieldName, Pair.of(setter, setterAndType.getRight()));
        }
        return settersMap;
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
