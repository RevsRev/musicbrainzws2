package com.github.rev.musicbrainz.client.search.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;

public class MbQueryTest {

    private static final String INVALID_FIELD_NAME = "invalid-not-mb-field";

    @ParameterizedTest
    @MethodSource("getMbQueries")
    public void testInvalidFieldName(MbQuery<?> query) {
        Assertions.assertThrows(MbQuery.InvalidQueryFieldException.class, () -> query.add(INVALID_FIELD_NAME, ""));
    }

    @ParameterizedTest
    @MethodSource("getMbQueries")
    public void testValidFieldName(MbQuery<?> query) {
        Set<String> queryFields = query.getQueryFields();
        for (String queryField : queryFields) {
            Assertions.assertDoesNotThrow(() -> query.add(queryField, ""));
        }
    }

    @Test
    public void testCorrectQueryStringSingleField() throws MbQuery.InvalidQueryFieldException {
        MbArtistQuery artistQuery = new MbArtistQuery();
        artistQuery.add(MbArtistQuery.ARTIST, "Fleetwood Mac");
        Assertions.assertEquals("artist:Fleetwood Mac", artistQuery.asQueryString());
    }

    @Test
    public void testCorrectQueryStringMultipleFields() throws MbQuery.InvalidQueryFieldException {
        MbArtistQuery artistQuery = new MbArtistQuery();
        artistQuery.add(MbArtistQuery.ARTIST, "Fleetwood Mac");
        artistQuery.add(MbArtistQuery.TYPE, "group");
        Assertions.assertEquals("artist:Fleetwood Mac AND type:group", artistQuery.asQueryString());
    }

    private static List<MbQuery<?>> getMbQueries() {
        return List.of(
                new MbAnnotationQuery(),
                new MbAreaQuery(),
                new MbArtistQuery(),
                new MbCdStubQuery(),
                new MbEventQuery(),
                new MbGenreQuery(),
                new MbInstrumentQuery(),
                new MbLabelQuery(),
                new MbPlaceQuery(),
                new MbRecordingQuery(),
                new MbReleaseGroupQuery(),
                new MbReleaseQuery(),
                new MbSeriesQuery(),
                new MbTagQuery(),
                new MbUrlQuery(),
                new MbWorkQuery()
        );
    }
}
