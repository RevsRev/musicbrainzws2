package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.rev.musicbrainz.client.mapping.fmt.json.JacksonJsonMapperFactory;
import com.github.rev.musicbrainz.client.mapping.fmt.xml.JacksonXmlMapperFactory;

/**
 * Container class for JSON and XML mappers.
 */
public final class JacksonMappers {

    private JacksonMappers() {
    }

    /**
     * Get a JSON mapper.
     * @return A new JsonMapper instance.
     */
    public static JsonMapper jsonMapper() {
        return JacksonJsonMapperFactory.jsonMapper();
    }

    /**
     * Get an XML mapper.
     * @return A new XmlMapper instance.
     */
    public static XmlMapper xmlMapper() {
        return JacksonXmlMapperFactory.xmlMapper();
    }
}
