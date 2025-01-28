package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Labels.
 */
public final class MbLabelQuery extends MbQuery<MbEntity.MbLabel> {

    /**
     * (part of) any alias attached to the label (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) the name of the label's main associated area.
     */
    public static final String AREA = "area";

    /**
     * The label's begin date (e.g. "1980-01-22").
     */
    public static final String BEGIN = "begin";

    /**
     * The label code for the label (only the numbers, without "LC").
     */
    public static final String CODE = "code";

    /**
     * (part of) the label's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The 2-letter code (ISO 3166-1 alpha-2) for the label's associated country.
     */
    public static final String COUNTRY = "country";

    /**
     * The label's end date (e.g. "1980-01-22").
     */
    public static final String END = "end";

    /**
     * A boolean flag (true/false) indicating whether or not the label has ended (is dissolved).
     */
    public static final String ENDED = "ended";

    /**
     * An IPI code associated with the label.
     */
    public static final String IPI = "ipi";

    /**
     * An ISNI code associated with the label.
     */
    public static final String ISNI = "isni";

    /**
     * (part of) the label's name (diacritics are ignored).
     */
    public static final String LABEL = "label";

    /**
     * (part of) the label's name (with the specified diacritics).
     */
    public static final String LABEL_ACCENT = "labelaccent";

    /**
     * The label's MBID.
     */
    public static final String LAID = "laid";

    /**
     * The amount of releases related to the label.
     */
    public static final String RELEASE_COUNT = "release_count";

    /**
     * Equivalent to name (labels no longer have separate sort names).
     */
    public static final String SORTNAME = "sortname";

    /**
     * (part of) a tag attached to the label.
     */
    public static final String TAG = "tag";

    /**
     * The label's type.
     */
    public static final String TYPE = "type";


    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                AREA,
                BEGIN,
                CODE,
                COMMENT,
                COUNTRY,
                END,
                ENDED,
                IPI,
                ISNI,
                LABEL,
                LABEL_ACCENT,
                LAID,
                RELEASE_COUNT,
                SORTNAME,
                TAG,
                TYPE
        );
    }
}
