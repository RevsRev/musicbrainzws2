package com.github.rev.musicbrainz.client.search.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class MbQueryTest {

    private static final String INVALID_FIELD_NAME = "invalid-not-mb-field";

    @ParameterizedTest
    @MethodSource("getMbQueries")
    public void testInvalidFieldName(MbQuery<?> query) {
        Assertions.assertThrows(MbQuery.InvalidQueryFieldException.class, () -> query.add(INVALID_FIELD_NAME, ""));
    }

    private static List<MbQuery<?>> getMbQueries() {
        return List.of(new MbAnnotationQuery());
    }
}
