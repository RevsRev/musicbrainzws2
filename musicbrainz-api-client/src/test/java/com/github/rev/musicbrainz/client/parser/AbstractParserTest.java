package com.github.rev.musicbrainz.client.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;

public class AbstractParserTest {

    private final JsonMapper jsonMapper = new JsonMapper();

    protected JsonNode loadJson(final String resourcePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        try {
            return jsonMapper.readTree(is);
        } catch (IOException e) {
            Assertions.fail(e);
        }
        return null;
    }

}
