package com.github.rev.musicbrainz.client.parse;

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
public final class MbJsonParser<R> implements MbParser<JsonNode, R> {

    private final Class<R> clazz;
    private final ObjectMapper om = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE);

    private MbJsonParser(final Class<R> clazz) {
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
    public static final MbJsonParser<MbAnnotationResult> ANNOTATION_PARSER
            = new MbJsonParser<>(MbAnnotationResult.class);

    /**
     * The AREA_PARSER.
     */
    public static final MbJsonParser<MbAreaResult> AREA_PARSER
            = new MbJsonParser<>(MbAreaResult.class);

    /**
     * The ARTIST_PARSER.
     */
    public static final MbJsonParser<MbArtistResult> ARTIST_PARSER
            = new MbJsonParser<>(MbArtistResult.class);

    /**
     * The STUB_PARSER.
     */
    public static final MbJsonParser<MbCdStubResult> STUB_PARSER
            = new MbJsonParser<>(MbCdStubResult.class);

    /**
     * The EVENT_PARSER.
     */
    public static final MbJsonParser<MbEventResult> EVENT_PARSER
            = new MbJsonParser<>(MbEventResult.class);

    /**
     * The GENRE_PARSER.
     */
    public static final MbJsonParser<MbGenreResult> GENRE_PARSER
            = new MbJsonParser<>(MbGenreResult.class);

    /**
     * The INSTRUMENT_PARSER.
     */
    public static final MbJsonParser<MbInstrumentResult> INSTRUMENT_PARSER
            = new MbJsonParser<>(MbInstrumentResult.class);

    /**
     * The LABEL_PARSER.
     */
    public static final MbJsonParser<MbLabelResult> LABEL_PARSER
            = new MbJsonParser<>(MbLabelResult.class);

    /**
     * The PLACE_PARSER.
     */
    public static final MbJsonParser<MbPlaceResult> PLACE_PARSER
            = new MbJsonParser<>(MbPlaceResult.class);

    /**
     * The RECORDING_PARSER.
     */
    public static final MbJsonParser<MbRecordingResult> RECORDING_PARSER
            = new MbJsonParser<>(MbRecordingResult.class);

    /**
     * The RELEASE_PARSER.
     */
    public static final MbJsonParser<MbReleaseResult> RELEASE_PARSER
            = new MbJsonParser<>(MbReleaseResult.class);

    /**
     * The RELEASEGROUP_PARSER.
     */
    public static final MbJsonParser<MbReleaseGroupResult> RELEASEGROUP_PARSER
            = new MbJsonParser<>(MbReleaseGroupResult.class);

    /**
     * The SERIES_PARSER.
     */
    public static final MbJsonParser<MbSeriesResult> SERIES_PARSER
            = new MbJsonParser<>(MbSeriesResult.class);

    /**
     * The TAG_PARSER.
     */
    public static final MbJsonParser<MbTagResult> TAG_PARSER
            = new MbJsonParser<>(MbTagResult.class);

    /**
     * The WORK_PARSER.
     */
    public static final MbJsonParser<MbWorkResult> WORK_PARSER
            = new MbJsonParser<>(MbWorkResult.class);

    /**
     * The URL_PARSER.
     */
    public static final MbJsonParser<MbUrlResult> URL_PARSER
            = new MbJsonParser<>(MbUrlResult.class);
}
