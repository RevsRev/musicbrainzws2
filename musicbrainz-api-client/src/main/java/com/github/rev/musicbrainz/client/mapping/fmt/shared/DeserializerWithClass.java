package com.github.rev.musicbrainz.client.mapping.fmt.shared;

import com.fasterxml.jackson.databind.module.SimpleDeserializers;

/**
 * Simple interface to allow easily adding instances of this to a deserializer as part of a jackson module.
 */
public interface DeserializerWithClass {

    /**
     * Add this to a SimpleDeserializers object.
     * @param deserializers
     */
    void addToDeserializers(SimpleDeserializers deserializers);
}
