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
        return om.convertValue(input, clazz);
    }

    /**
     * The ANNOTATION_MAPPER.
     */
    public static final MbJsonMapper<MbAnnotationResult> ANNOTATION_MAPPER
            = new MbJsonMapper<>(MbAnnotationResult.class);

    /**
     * The AREA_MAPPER.
     */
    public static final MbJsonMapper<MbAreaResult> AREA_MAPPER
            = new MbJsonMapper<>(MbAreaResult.class);

    /**
     * The ARTIST_MAPPER.
     */
    public static final MbJsonMapper<MbArtistResult> ARTIST_MAPPER
            = new MbJsonMapper<>(MbArtistResult.class);

    /**
     * The STUB_MAPPER.
     */
    public static final MbJsonMapper<MbCdStubResult> STUB_MAPPER
            = new MbJsonMapper<>(MbCdStubResult.class);

    /**
     * The EVENT_MAPPER.
     */
    public static final MbJsonMapper<MbEventResult> EVENT_MAPPER
            = new MbJsonMapper<>(MbEventResult.class);

    /**
     * The GENRE_MAPPER.
     */
    public static final MbJsonMapper<MbGenreResult> GENRE_MAPPER
            = new MbJsonMapper<>(MbGenreResult.class);

    /**
     * The INSTRUMENT_MAPPER.
     */
    public static final MbJsonMapper<MbInstrumentResult> INSTRUMENT_MAPPER
            = new MbJsonMapper<>(MbInstrumentResult.class);

    /**
     * The LABEL_MAPPER.
     */
    public static final MbJsonMapper<MbLabelResult> LABEL_MAPPER
            = new MbJsonMapper<>(MbLabelResult.class);

    /**
     * The PLACE_MAPPER.
     */
    public static final MbJsonMapper<MbPlaceResult> PLACE_MAPPER
            = new MbJsonMapper<>(MbPlaceResult.class);

    /**
     * The RECORDING_MAPPER.
     */
    public static final MbJsonMapper<MbRecordingResult> RECORDING_MAPPER
            = new MbJsonMapper<>(MbRecordingResult.class);

    /**
     * The RELEASE_MAPPER.
     */
    public static final MbJsonMapper<MbReleaseResult> RELEASE_MAPPER
            = new MbJsonMapper<>(MbReleaseResult.class);

    /**
     * The RELEASEGROUP_MAPPER.
     */
    public static final MbJsonMapper<MbReleaseGroupResult> RELEASEGROUP_MAPPER
            = new MbJsonMapper<>(MbReleaseGroupResult.class);

    /**
     * The SERIES_MAPPER.
     */
    public static final MbJsonMapper<MbSeriesResult> SERIES_MAPPER
            = new MbJsonMapper<>(MbSeriesResult.class);

    /**
     * The TAG_MAPPER.
     */
    public static final MbJsonMapper<MbTagResult> TAG_MAPPER
            = new MbJsonMapper<>(MbTagResult.class);

    /**
     * The WORK_MAPPER.
     */
    public static final MbJsonMapper<MbWorkResult> WORK_MAPPER
            = new MbJsonMapper<>(MbWorkResult.class);

    /**
     * The URL_MAPPER.
     */
    public static final MbJsonMapper<MbUrlResult> URL_MAPPER
            = new MbJsonMapper<>(MbUrlResult.class);
}
