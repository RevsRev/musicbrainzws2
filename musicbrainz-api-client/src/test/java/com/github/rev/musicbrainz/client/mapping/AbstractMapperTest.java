package com.github.rev.musicbrainz.client.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.search.result.MbAnnotationResult;
import com.github.rev.musicbrainz.client.search.result.MbAreaResult;
import com.github.rev.musicbrainz.client.search.result.MbArtistResult;
import com.github.rev.musicbrainz.client.search.result.MbCdStubResult;
import com.github.rev.musicbrainz.client.search.result.MbEventResult;
import com.github.rev.musicbrainz.client.search.result.MbInstrumentResult;
import com.github.rev.musicbrainz.client.search.result.MbLabelResult;
import com.github.rev.musicbrainz.client.search.result.MbPlaceResult;
import com.github.rev.musicbrainz.client.search.result.MbRecordingResult;
import com.github.rev.musicbrainz.client.search.result.MbReleaseGroupResult;
import com.github.rev.musicbrainz.client.search.result.MbReleaseResult;
import com.github.rev.musicbrainz.client.search.result.MbSeriesResult;
import com.github.rev.musicbrainz.client.search.result.MbTagResult;
import com.github.rev.musicbrainz.client.search.result.MbUrlResult;
import com.github.rev.musicbrainz.client.search.result.MbWorkResult;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AbstractMapperTest {

    private static final String SEARCH_FOLDER_NAME = "search";
    private static final String LOOKUP_FOLDER_NAME = "lookup";

    @ParameterizedTest
    @MethodSource("getMapperTestParams")
    public <T> void testMappers(final MapperTestParams<T> testParams) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(testParams.resourceName);
        T obj = testParams.mapper.parse(is);
    }

    public static List<MapperTestParams<?>> getMapperTestParams() {
        List<MapperTestParams<?>> testParams = new ArrayList<>();

        //XML TEST CASES
        testParams.add(MapperTestParams.factory(MbAnnotationResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbAreaResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbArtistResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbCdStubResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbEventResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
//        testParams.add(MapperTestParams.factory(MbGenreResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbInstrumentResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbLabelResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbPlaceResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbRecordingResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbReleaseResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbReleaseGroupResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbSeriesResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbTagResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbWorkResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbUrlResult.class, SEARCH_FOLDER_NAME, MbFormat.XML));

        //JSON TEST CASES
        testParams.add(MapperTestParams.factory(MbAnnotationResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbAreaResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbArtistResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbCdStubResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbEventResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
//        testParams.add(MapperTestParams.factory(MbGenreResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbInstrumentResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbLabelResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbPlaceResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbRecordingResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbReleaseResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbReleaseGroupResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbSeriesResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbTagResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbWorkResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbUrlResult.class, SEARCH_FOLDER_NAME, MbFormat.JSON));

        //XML TEST CASES
        testParams.add(MapperTestParams.factory(MbAnnotationResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbAreaResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbArtistResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbCdStubResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbEventResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
//        testParams.add(MapperTestParams.factory(MbGenreResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbInstrumentResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbLabelResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbPlaceResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbRecordingResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbReleaseResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbReleaseGroupResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbSeriesResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbTagResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbWorkResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbUrlResult.class, LOOKUP_FOLDER_NAME, MbFormat.XML));

        //JSON TEST CASES
        testParams.add(MapperTestParams.factory(MbAnnotationResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbAreaResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbArtistResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbCdStubResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbEventResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
//        testParams.add(MapperTestParams.factory(MbGenreResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbInstrumentResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbLabelResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbPlaceResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbRecordingResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbReleaseResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbReleaseGroupResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbSeriesResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbTagResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbWorkResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));
        testParams.add(MapperTestParams.factory(MbUrlResult.class, LOOKUP_FOLDER_NAME, MbFormat.JSON));

        return testParams;
    }


    public static class MapperTestParams<T> {
        private final InputStreamMapper<T> mapper;
        private final String resourceName;
        private final MbFormat format;

        private MapperTestParams(final InputStreamMapper<T> mapper,
                                 final String resourceName, MbFormat format) {
            this.mapper = mapper;
            this.resourceName = resourceName;
            this.format = format;
        }

        private static <T> MapperTestParams<T> factory(final Class<T> clazz,
                                                       final String searchFolderName,
                                                       final MbFormat format) {
            InputStreamMapper<T> mapper = InputStreamMapper.factory(clazz, format);
            String extension = format == MbFormat.XML ? "xml" : "json";
            String resourceLocation = String.format("example_data/%s/%s.%s", searchFolderName, clazz.getSimpleName(), extension);
            return new MapperTestParams<>(mapper, resourceLocation, format);
        }

    }

}
