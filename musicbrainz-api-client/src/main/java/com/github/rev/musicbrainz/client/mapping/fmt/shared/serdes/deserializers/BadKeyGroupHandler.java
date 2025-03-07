package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.MbSerdesException;

/**
 * Base class of all our custom jackson handling.
 *
 * Implementations should only deal with fields passed in as "badKeyNames", everything else is left to jackson.
 * Once the bad keys are dealt with, they are removed from the parse tree.
 *
 * @param <T> The type of class this handler is responsible for handling.
 */
public abstract class BadKeyGroupHandler<T> {

    private final Class<T> clazz;
    private final String[] badKeyNames;

    /**
     * Constructor.
     * @param clazz
     * @param badKeyNames
     */
    protected BadKeyGroupHandler(final Class<T> clazz, final String... badKeyNames) {
        this.clazz = clazz;
        this.badKeyNames = badKeyNames;
    }

    /**
     * Deal with the bad keys on the instance of T which is passed from the deserializer.
     * @param t The instance of T being built by the parsing process.
     * @param objectNode The node parsed from the raw JSON/XML representing T.
     * @param mapper An object mapper that can be delegated to continue parsing nodes in the objectNode.
     */
    public final void accept(final T t, final ObjectNode objectNode, final ObjectMapper mapper) {
        try {
            acceptImpl(t, objectNode, mapper);
            for (String badKeyName : badKeyNames) {
                objectNode.remove(badKeyName);
            }
        } catch (MbSerdesException e) {
            //TODO - Is this the most appropriate thing to do here?
            throw new RuntimeException(e);
        }
    }

    /**
     * Deal with the bad keys on the instance of T which is passed from the deserializer.
     * @param t The instance of T being built by the parsing process.
     * @param jsonNode The node parsed from the raw JSON/XML representing T.
     * @param mapper An object mapper that can be delegated to continue parsing nodes in the objectNode.
     */
    protected abstract void acceptImpl(T t, JsonNode jsonNode, ObjectMapper mapper) throws MbSerdesException;
}
