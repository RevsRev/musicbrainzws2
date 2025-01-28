package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Artists.
 */
public final class MbArtistQuery extends MbQuery<MbEntity.MbArtist> {

    /**
     * (part of) any alias attached to the artist (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * (part of) any primary alias attached to the artist (diacritics are ignored).
     */
    public static final String PRIMARY_ALIAS = "primary_alias";

    /**
     * (part of) the name of the artist's main associated area.
     */
    public static final String AREA = "area";

    /**
     * The artist's MBID.
     */
    public static final String ARID = "arid";

    /**
     * (part of) the artist's name (diacritics are ignored).
     */
    public static final String ARTIST = "artist";

    /**
     * (part of) the artist's name (with the specified diacritics).
     */
    public static final String ARTISTACCENT = "artistaccent";

    /**
     * The artist's begin date (e.g. "1980-01-22").
     */
    public static final String BEGIN = "begin";

    /**
     * (part of) the name of the artist's begin area.
     */
    public static final String BEGINAREA = "beginarea";

    /**
     * (part of) the artist's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The 2-letter code (ISO 3166-1 alpha-2) for the artist's main associated country.
     */
    public static final String COUNTRY = "country";

    /**
     * The artist's end date (e.g. "1980-01-22").
     */
    public static final String END = "end";

    /**
     * (part of) the name of the artist's end area.
     */
    public static final String ENDAREA = "endarea";

    /**
     * A boolean flag (true/false) indicating whether the artist has ended (is dissolved/deceased).
     */
    public static final String ENDED = "ended";

    /**
     * The artist's gender (“male”, “female”, “other” or “not applicable”).
     */
    public static final String GENDER = "gender";

    /**
     * An IPI code associated with the artist.
     */
    public static final String IPI = "ipi";

    /**
     * An ISNI code associated with the artist.
     */
    public static final String ISNI = "isni";

    /**
     * (part of) the artist's sort name.
     */
    public static final String SORTNAME = "sortname";

    /**
     * (part of) a tag attached to the artist.
     */
    public static final String TAG = "tag";

    /**
     * The artist's type (“person”, “group”, etc.).
     */
    public static final String TYPE = "type";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                PRIMARY_ALIAS,
                AREA,
                ARID,
                ARTIST,
                ARTISTACCENT,
                BEGIN,
                BEGINAREA,
                COMMENT,
                COUNTRY,
                END,
                ENDAREA,
                ENDED,
                GENDER,
                IPI,
                ISNI,
                SORTNAME,
                TAG,
                TYPE
        );
    }
}
