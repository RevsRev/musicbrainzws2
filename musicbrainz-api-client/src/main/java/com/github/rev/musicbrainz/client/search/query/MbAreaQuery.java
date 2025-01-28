package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Areas.
  */
public final class MbAreaQuery extends MbQuery<MbEntity.MbArea> {

    /**
     * The area's MBID.
     */
    public static final String AID = "aid";

    /**
     * (part of) any alias attached to the artist (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) the area's name (diacritics are ignored).
     */
    public static final String AREA = "area";

    /**
     * (part of) the area's name (with the specified diacritics).
     */
    public static final String AREA_ACCENT = "areaaccent";

    /**
     * The area's begin date (e.g. "1980-01-22").
     */
    public static final String BEGIN = "begin";

    /**
     * (part of) the area's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The area's end date (e.g. "1980-01-22").
     */
    public static final String END = "end";

    /**
     * A boolean flag (true/false) indicating whether the area has ended (is no longer current).
     */
    public static final String ENDED = "ended";

    /**
     * An ISO 3166-1, 3166-2 or 3166-3 code attached to the area.
     */
    public static final String ISO = "iso";

    /**
     * An ISO 3166-1 code attached to the area.
     */
    public static final String ISO1 = "iso1";

    /**
     * An ISO 3166-2 code attached to the area.
     */
    public static final String ISO2 = "iso2";

    /**
     * An ISO 3166-3 code attached to the area.
     */
    public static final String ISO3 = "iso3";

    /**
     * Equivalent to name (areas no longer have separate sort names).
     */
    public static final String SORT_NAME = "sortname";

    /**
     * (part of) a tag attached to the area.
     */
    public static final String TAG = "tag";

    /**
     * The area's type.
     */
    public static final String TYPE = "type";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                AID,
                ALIAS,
                AREA,
                AREA_ACCENT,
                BEGIN,
                COMMENT,
                END,
                ENDED,
                ISO,
                ISO1,
                ISO2,
                ISO3,
                SORT_NAME,
                TAG,
                TYPE
        );
    }
}
