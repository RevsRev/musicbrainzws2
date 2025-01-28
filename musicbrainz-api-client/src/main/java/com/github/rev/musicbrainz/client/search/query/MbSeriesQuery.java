package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Series.
 */
public final class MbSeriesQuery extends MbQuery<MbEntity.MbSeries> {

    /**
     * (part of) any alias attached to the series (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) the series' disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * (part of) the series' name (diacritics are ignored).
     */
    public static final String SERIES = "series";

    /**
     * (part of) the series' name (with the specified diacritics).
     */
    public static final String SERIES_ACCENT = "seriesaccent";

    /**
     * The series' MBID.
     */
    public static final String SID = "sid";

    /**
     * (part of) a tag attached to the series.
     */
    public static final String TAG = "tag";

    /**
     * The series' type.
     */
    public static final String TYPE = "type";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                COMMENT,
                SERIES,
                SERIES_ACCENT,
                SID,
                TAG,
                TYPE
        );
    }
}
