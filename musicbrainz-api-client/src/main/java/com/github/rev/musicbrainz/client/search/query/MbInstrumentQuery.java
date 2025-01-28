package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Instruments.
 */
public final class MbInstrumentQuery extends MbQuery<MbEntity.MbInstrument> {

    /**
     * (part of) any alias attached to the instrument (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) the instrument's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * (part of) the description of the instrument (in English).
     */
    public static final String DESCRIPTION = "description";

    /**
     * The MBID of the instrument.
     */
    public static final String IID = "iid";

    /**
     * (part of) the instrument's name (diacritics are ignored).
     */
    public static final String INSTRUMENT = "instrument";

    /**
     * (part of) the instrument's name (with the specified diacritics).
     */
    public static final String INSTRUMENTACCENT = "instrumentaccent";

    /**
     * (part of) a tag attached to the instrument.
     */
    public static final String TAG = "tag";

    /**
     * The instrument's type.
     */
    public static final String TYPE = "type";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                COMMENT,
                DESCRIPTION,
                IID,
                INSTRUMENT,
                INSTRUMENTACCENT,
                TAG,
                TYPE
        );
    }
}
