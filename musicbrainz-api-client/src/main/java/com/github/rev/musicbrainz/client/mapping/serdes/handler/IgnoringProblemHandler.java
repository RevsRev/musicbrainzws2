package com.github.rev.musicbrainz.client.mapping.serdes.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

import java.io.IOException;
import java.util.Set;

/**
 * A problem handler implementation that ignores certain fields during parsing.
 */
public final class IgnoringProblemHandler extends DeserializationProblemHandler {

    private final Set<String> exceptionsToSkip;

    /**
     * @param exceptionsToSkip Fields to ignore when parsing.
     */
    public IgnoringProblemHandler(final Set<String> exceptionsToSkip) {
        this.exceptionsToSkip = exceptionsToSkip;
    }

    @Override
    public boolean handleUnknownProperty(final DeserializationContext ctxt,
                                         final JsonParser p,
                                         final JsonDeserializer<?> deserializer,
                                         final Object beanOrClass,
                                         final String propertyName) throws IOException {
        if (exceptionsToSkip.contains(propertyName)) {
            p.skipChildren();
            return true;
        }
        return false;
    }
}
