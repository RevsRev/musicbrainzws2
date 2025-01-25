package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Works.
 */
public final class MbWorkQuery extends MbQuery<MbEntity.MbWork> {

    /**
     * (part of) any alias attached to the work (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * The MBID of an artist related to the event (e.g. a composer or lyricist).
     */
    public static final String ARID = "arid";

    /**
     * (part of) the name of an artist related to the work (e.g. a composer or lyricist).
     */
    public static final String ARTIST = "artist";

    /**
     * (part of) the work's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * Any ISWC associated to the work.
     */
    public static final String ISWC = "iswc";

    /**
     * The ISO 639-3 code for any of the languages of the work's lyrics.
     */
    public static final String LANG = "lang";

    /**
     * (part of) the title of a recording related to the work.
     */
    public static final String RECORDING = "recording";

    /**
     * The number of recordings related to the work.
     */
    public static final String RECORDING_COUNT = "recording_count";

    /**
     * The MBID of a recording related to the work.
     */
    public static final String RID = "rid";

    /**
     * (part of) a tag attached to the work.
     */
    public static final String TAG = "tag";

    /**
     * The work's type (e.g. "opera", "song", "symphony").
     */
    public static final String TYPE = "type";

    /**
     * The work's MBID.
     */
    public static final String WID = "wid";

    /**
     * (part of) the work's title (diacritics are ignored).
     */
    public static final String WORK = "work";

    /**
     * (part of) the work's title (with the specified diacritics).
     */
    public static final String WORK_ACCENT = "workaccent";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                ARID,
                ARTIST,
                COMMENT,
                ISWC,
                LANG,
                RECORDING,
                RECORDING_COUNT,
                RID,
                TAG,
                TYPE,
                WID,
                WORK,
                WORK_ACCENT
        );
    }
}
