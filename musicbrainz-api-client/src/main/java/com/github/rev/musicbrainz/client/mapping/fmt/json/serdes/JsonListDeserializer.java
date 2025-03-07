package com.github.rev.musicbrainz.client.mapping.fmt.json.serdes;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.DeserializerWithClass;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

/**
 * For dealing with funky lists returned by MusicBrainz json endpoints.
 * @param <T> The type of XXXList object deserialized by this class.
 */
public final class JsonListDeserializer<T> extends JsonDeserializer<T> implements DeserializerWithClass {

    private final Class<T> resultClazz;

    /**
     * Constructor.
     * @param resultClazz The result of deserialization.
     */
    public JsonListDeserializer(final Class<T> resultClazz) {
        this.resultClazz = resultClazz;
    }

    @Override
    public T deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
        try {
            if (p.currentToken() == JsonToken.START_ARRAY) {
                return parseAsArray(p, ctxt);
            }
            return parseAsObject(p, ctxt);
        } catch (Exception e) {
            //TODO - Better error handling!
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToDeserializers(final SimpleDeserializers deserializers) {
        deserializers.addDeserializer(resultClazz, this);
    }

    private T parseAsObject(final JsonParser p, final DeserializationContext ctxt) {
        //TODO - Do we need to implement this? Either way, should handle better.
        throw new RuntimeException("Method not implemented!");
    }

    private T parseAsArray(final JsonParser p, final DeserializationContext ctxt)
            throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException,
            InstantiationException {
        Method getListMethod = getListMethod();

        ParameterizedType listType = (ParameterizedType) getListMethod.getGenericReturnType();
        Class<?> actualTypeArgument = (Class<?>) listType.getActualTypeArguments()[0];

        T instance = constructInstance();
        p.nextToken();
        Iterator<?> iterator = p.getCodec().readValues(p, actualTypeArgument);

        List<Object> objects = (List<Object>) getListMethod.invoke(instance);
        while (iterator.hasNext()) {
            Object next = iterator.next();
            objects.add(next);
        }
        return instance;
    }

    private Method getListMethod() {
        Method[] methods = resultClazz.getMethods();
        for (Method method : methods) {
            if (method.getReturnType().equals(List.class)) {
                return method;
            }
        }
        //TODO - Error handling..
        throw new RuntimeException("Could not parse list class");
    }

    private T constructInstance()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = resultClazz.getConstructor();
        return constructor.newInstance();
    }
}
