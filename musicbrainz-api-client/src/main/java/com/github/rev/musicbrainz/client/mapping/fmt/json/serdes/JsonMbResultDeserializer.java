package com.github.rev.musicbrainz.client.mapping.fmt.json.serdes;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.rev.musicbrainz.client.MbResult;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Deals with top level music brainz results returned in json format from the API.
 * @param <T> The type of result dealt with by the parser.
 */
public final class JsonMbResultDeserializer<T extends MbResult<?>> extends JsonDeserializer<T> {

    private final Class<T> resultClazz;

    /**
     * Constructor.
     * @param resultClazz the result of deserialization.
     */
    public JsonMbResultDeserializer(final Class<T> resultClazz) {
        this.resultClazz = resultClazz;
    }

    @Override
    public T deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectNode node = p.getCodec().readTree(p);
        JsonNode createdNode = node.remove("created");

        T instance = constructInstance();

        Method[] methods = resultClazz.getMethods();
        Method setListMethod = null;
        Method setCreatedField = null;
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set") && name.contains("List")) {
                setListMethod = method;
            } else if (name.startsWith("set") && name.contains("Created")) {
                setCreatedField = method;
            }
        }

        if (setListMethod == null) {
            //TODO - This shouldn't be an IOException! Use something else:)
            throw new IOException(String.format("Cannot find set method for list result for class '%s'", resultClazz));
        }
        if (setCreatedField == null) {
            //TODO - This shouldn't be an IOException! Use something else:)
            throw new IOException(String.format("Cannot find set created method for class '%s'", resultClazz));
        }

        Object deserialize = ctxt.readTreeAsValue(node, setListMethod.getParameterTypes()[0]);
//        Object deserialize = codec.treeToValue(node, setListMethod.getParameterTypes()[0]);
        setList(setListMethod, deserialize, instance);
        setCreated(setCreatedField, createdNode.textValue(), instance);
        return instance;
    }

    private T constructInstance() throws IOException {
        try {
            Constructor<T> constructor = resultClazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            //TODO - This shouldn't be an IOException! Use something else:)
            throw new IOException(e);
        }
    }

    private void setList(final Method setListMethod,
                         final Object parsedList,
                         final T instance) throws IOException {

        try {
            setListMethod.invoke(instance, parsedList);
        } catch (Exception e) {
            //TODO - This shouldn't be an IOException! Use something else:)
            throw new IOException(e);
        }
    }

    private void setCreated(final Method setCreatedMethod,
                            final String s,
                            final T instance) {
        //TODO - Pass in date format and set this correctly
//        createdField.set(instance, s);
    }
}
