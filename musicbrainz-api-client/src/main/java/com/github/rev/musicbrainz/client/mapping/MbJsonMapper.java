package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.github.rev.musicbrainz.client.artist.MbArtistResult;
import com.github.rev.musicbrainz.client.entity.result.MbAnnotationResult;
import com.github.rev.musicbrainz.client.entity.result.MbAreaResult;
import com.github.rev.musicbrainz.client.entity.result.MbCdStubResult;
import com.github.rev.musicbrainz.client.entity.result.MbEventResult;
import com.github.rev.musicbrainz.client.entity.result.MbGenreResult;
import com.github.rev.musicbrainz.client.entity.result.MbInstrumentResult;
import com.github.rev.musicbrainz.client.entity.result.MbLabelResult;
import com.github.rev.musicbrainz.client.entity.result.MbPlaceResult;
import com.github.rev.musicbrainz.client.entity.result.MbRecordingResult;
import com.github.rev.musicbrainz.client.entity.result.MbReleaseGroupResult;
import com.github.rev.musicbrainz.client.entity.result.MbReleaseResult;
import com.github.rev.musicbrainz.client.entity.result.MbSeriesResult;
import com.github.rev.musicbrainz.client.entity.result.MbTagResult;
import com.github.rev.musicbrainz.client.entity.result.MbUrlResult;
import com.github.rev.musicbrainz.client.entity.result.MbWorkResult;

/**
 * Parser for turning JSON into POJOs.
 * @param <R> The target POJO.
 */
public final class MbJsonMapper<R> implements MbMapper<JsonNode, R> {

    private final Class<R> clazz;
    private final ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new MbSerdesModule());

    private MbJsonMapper(final Class<R> clazz) {
        this.clazz = clazz;
    }

    @Override
    public R parse(final JsonNode input) {
//        System.out.println(input.toPrettyString());
        return om.convertValue(input, clazz);
//        return null;
    }

    /**
     * The ANNOTATION_PARSER.
     */
    public static final MbJsonMapper<MbAnnotationResult> ANNOTATION_PARSER
            = new MbJsonMapper<>(MbAnnotationResult.class);

    /**
     * The AREA_PARSER.
     */
    public static final MbJsonMapper<MbAreaResult> AREA_PARSER
            = new MbJsonMapper<>(MbAreaResult.class);

    /**
     * The ARTIST_PARSER.
     */
    public static final MbJsonMapper<MbArtistResult> ARTIST_PARSER
            = new MbJsonMapper<>(MbArtistResult.class);

    /**
     * The STUB_PARSER.
     */
    public static final MbJsonMapper<MbCdStubResult> STUB_PARSER
            = new MbJsonMapper<>(MbCdStubResult.class);

    /**
     * The EVENT_PARSER.
     */
    public static final MbJsonMapper<MbEventResult> EVENT_PARSER
            = new MbJsonMapper<>(MbEventResult.class);

    /**
     * The GENRE_PARSER.
     */
    public static final MbJsonMapper<MbGenreResult> GENRE_PARSER
            = new MbJsonMapper<>(MbGenreResult.class);

    /**
     * The INSTRUMENT_PARSER.
     */
    public static final MbJsonMapper<MbInstrumentResult> INSTRUMENT_PARSER
            = new MbJsonMapper<>(MbInstrumentResult.class);

    /**
     * The LABEL_PARSER.
     */
    public static final MbJsonMapper<MbLabelResult> LABEL_PARSER
            = new MbJsonMapper<>(MbLabelResult.class);

    /**
     * The PLACE_PARSER.
     */
    public static final MbJsonMapper<MbPlaceResult> PLACE_PARSER
            = new MbJsonMapper<>(MbPlaceResult.class);

    /**
     * The RECORDING_PARSER.
     */
    public static final MbJsonMapper<MbRecordingResult> RECORDING_PARSER
            = new MbJsonMapper<>(MbRecordingResult.class);

    /**
     * The RELEASE_PARSER.
     */
    public static final MbJsonMapper<MbReleaseResult> RELEASE_PARSER
            = new MbJsonMapper<>(MbReleaseResult.class);

    /**
     * The RELEASEGROUP_PARSER.
     */
    public static final MbJsonMapper<MbReleaseGroupResult> RELEASEGROUP_PARSER
            = new MbJsonMapper<>(MbReleaseGroupResult.class);

    /**
     * The SERIES_PARSER.
     */
    public static final MbJsonMapper<MbSeriesResult> SERIES_PARSER
            = new MbJsonMapper<>(MbSeriesResult.class);

    /**
     * The TAG_PARSER.
     */
    public static final MbJsonMapper<MbTagResult> TAG_PARSER
            = new MbJsonMapper<>(MbTagResult.class);

    /**
     * The WORK_PARSER.
     */
    public static final MbJsonMapper<MbWorkResult> WORK_PARSER
            = new MbJsonMapper<>(MbWorkResult.class);

    /**
     * The URL_PARSER.
     */
    public static final MbJsonMapper<MbUrlResult> URL_PARSER
            = new MbJsonMapper<>(MbUrlResult.class);
}
