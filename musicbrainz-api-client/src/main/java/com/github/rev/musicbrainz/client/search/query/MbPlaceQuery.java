package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Places.
 */
public final class MbPlaceQuery extends MbQuery<MbEntity.MbPlace> {

    /**
     * (part of) the physical address for this place.
     */
    public static final String ADDRESS = "address";

    /**
     * (part of) any alias attached to the place (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) the name of the place's main associated area.
     */
    public static final String AREA = "area";

    /**
     * The place's begin date (e.g. "1980-01-22").
     */
    public static final String BEGIN = "begin";

    /**
     * (part of) the place's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The place's end date (e.g. "1980-01-22").
     */
    public static final String END = "end";

    /**
     * A boolean flag (true/false) indicating whether or not the place has ended (is closed).
     */
    public static final String ENDED = "ended";

    /**
     * The (WGS 84) latitude of the place's coordinates (e.g. "58.388226").
     */
    public static final String LAT = "lat";

    /**
     * The (WGS 84) longitude of the place's coordinates (e.g. "26.702817").
     */
    public static final String LONG = "long";

    /**
     * (part of) the place's name (diacritics are ignored).
     */
    public static final String PLACE = "place";

    /**
     * (part of) the place's name (with the specified diacritics).
     */
    public static final String PLACE_ACCENT = "placeaccent";

    /**
     * The place's MBID.
     */
    public static final String PID = "pid";

    /**
     * The place's type.
     */
    public static final String TYPE = "type";


    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ADDRESS,
                ALIAS,
                AREA,
                BEGIN,
                COMMENT,
                END,
                ENDED,
                LAT,
                LONG,
                PLACE,
                PLACE_ACCENT,
                PID,
                TYPE
        );
    }
}
