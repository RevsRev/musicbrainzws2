package com.github.rev.musicbrainz.client.mapping;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AbstractMapperTest {

    @ParameterizedTest
    @MethodSource("getMapperTestParams")
    public <T> void testMappers(final MapperTestParams<T> testParams) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(testParams.resourceName);
        T obj = testParams.mapper.parse(is);
    }

    public static List<MapperTestParams<?>> getMapperTestParams() {
        List<MapperTestParams<?>> testParams = new ArrayList<>();

        testParams.add(MapperTestParams.factory(MbAnnotationResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbAreaResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbArtistResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbCdStubResult.class, MbFormat.XML)); //TODO - Empty test data
        testParams.add(MapperTestParams.factory(MbEventResult.class, MbFormat.XML));
//        testParams.add(MapperTestParams.factory(MbGenreResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbInstrumentResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbLabelResult.class, MbFormat.XML)); //TODO - Empty test data
        testParams.add(MapperTestParams.factory(MbPlaceResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbRecordingResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbReleaseResult.class, MbFormat.XML)); //TODO - Empty test data
        testParams.add(MapperTestParams.factory(MbReleaseGroupResult.class, MbFormat.XML)); //TODO - Empty test data
        testParams.add(MapperTestParams.factory(MbSeriesResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbTagResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbWorkResult.class, MbFormat.XML));
        testParams.add(MapperTestParams.factory(MbUrlResult.class, MbFormat.XML)); //TODO - Empty test data

        //TODO - Fix me
//        testParams.add(MapperTestParams.factory(MbArtistResult.class, MbFormat.JSON));
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
                                                       final MbFormat format) {
            InputStreamMapper<T> mapper = InputStreamMapper.factory(clazz, format);
            String extension = format == MbFormat.XML ? "xml" : "json";
            String resourceLocation = String.format("example_data/%s.%s", clazz.getSimpleName(), extension);
            return new MapperTestParams<>(mapper, resourceLocation, format);
        }

    }

}
