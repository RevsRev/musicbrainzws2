package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AbstractMapperTest {

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

    @ParameterizedTest
    @MethodSource("getMapperTestParams")
    public <R> void testMappers(final MapperTestParams<JsonNode,R> testParams) {
        JsonNode jsonNode = loadJson(testParams.resourceName);
        Object parsed = testParams.mapper.parse(jsonNode);
    }

    public static List<MapperTestParams<JsonNode,?>> getMapperTestParams() {
        List<MapperTestParams<JsonNode,?>> testParams = new ArrayList<>();
        testParams.add(new MapperTestParams<>(MbJsonMapper.ARTIST_MAPPER, "example_data/artist_result.json"));
        return testParams;
    }


    public static class MapperTestParams<T,R> {
        private final MbMapper<T,R> mapper;
        private final String resourceName;

        private MapperTestParams(final MbMapper<T, R> mapper,
                                 final String resourceName) {
            this.mapper = mapper;
            this.resourceName = resourceName;
        }

    }

}
