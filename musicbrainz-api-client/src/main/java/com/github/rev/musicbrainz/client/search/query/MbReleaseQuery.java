package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Releases.
 */
public final class MbReleaseQuery extends MbQuery<MbEntity.MbRelease> {

    /**
     * (part of) any alias attached to the release (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * The MBID of any of the release artists.
     */
    public static final String ARID = "arid";

    /**
     * (part of) the combined credited artist name for the release, including join phrases (e.g. "Artist X feat.").
     */
    public static final String ARTIST = "artist";

    /**
     * (part of) the name of any of the release artists.
     */
    public static final String ARTIST_NAME = "artistname";

    /**
     * An Amazon ASIN for the release.
     */
    public static final String ASIN = "asin";

    /**
     * The barcode for the release.
     */
    public static final String BARCODE = "barcode";

    /**
     * Any catalog number for this release (insensitive to case, spaces, and separators).
     */
    public static final String CATNO = "catno";

    /**
     * (part of) the release's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The 2-letter code (ISO 3166-1 alpha-2) for any country the release was released in.
     */
    public static final String COUNTRY = "country";

    /**
     * (part of) the credited name of any of the release artists on this particular release.
     */
    public static final String CREDIT_NAME = "creditname";

    /**
     * A release date for the release (e.g. "1980-01-22").
     */
    public static final String DATE = "date";

    /**
     * The total number of disc IDs attached to all mediums on the release.
     */
    public static final String DISCIDS = "discids";

    /**
     * The number of disc IDs attached to any one medium on the release.
     */
    public static final String DISC_IDS_MEDIUM = "discidsmedium";

    /**
     * The format of any medium in the release (insensitive to case, spaces, and separators).
     */
    public static final String FORMAT = "format";

    /**
     * The MBID of any of the release labels.
     */
    public static final String LAID = "laid";

    /**
     * (part of) the name of any of the release labels.
     */
    public static final String LABEL = "label";

    /**
     * The ISO 639-3 code for the release language.
     */
    public static final String LANG = "lang";

    /**
     * The number of mediums on the release.
     */
    public static final String MEDIUMS = "mediums";

    /**
     * The format of the release (insensitive to case, spaces, and separators).
     */
    public static final String PACK_AGING = "packaging";

    /**
     * The primary type of the release group for this release.
     */
    public static final String PRIMARY_TYPE = "primarytype";

    /**
     * The listed quality of the data for the release (2 for “high”, 1 for “normal”; cannot search for “low” at the
     * moment; see the related bug report).
     */
    public static final String QUALITY = "quality";

    /**
     * The release's MBID.
     */
    public static final String REID = "reid";

    /**
     * (part of) the release's title (diacritics are ignored).
     */
    public static final String RELEASE = "release";

    /**
     * (part of) the release's title (with the specified diacritics).
     */
    public static final String RELEASE_ACCENT = "releaseaccent";

    /**
     * The MBID of the release group for this release.
     */
    public static final String RGID = "rgid";

    /**
     * The ISO 15924 code for the release script.
     */
    public static final String SCRIPT = "script";

    /**
     * Any of the secondary types of the release group for this release.
     */
    public static final String SECONDARY_TYPE = "secondarytype";

    /**
     * The status of the release.
     */
    public static final String STATUS = "status";

    /**
     * (part of) a tag attached to the release.
     */
    public static final String TAG = "tag";

    /**
     * The total number of tracks on the release.
     */
    public static final String TRACKS = "tracks";

    /**
     * The number of tracks on any one medium on the release.
     */
    public static final String TRACKS_MEDIUM = "tracksmedium";

    /**
     * Legacy release group type field that predates the ability to set multiple types (see calculation code).
     */
    public static final String TYPE = "type";


    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                ARID,
                ARTIST,
                ARTIST_NAME,
                ASIN,
                BARCODE,
                CATNO,
                COMMENT,
                COUNTRY,
                CREDIT_NAME,
                DATE,
                DISCIDS,
                DISC_IDS_MEDIUM,
                FORMAT,
                LAID,
                LABEL,
                LANG,
                MEDIUMS,
                PACK_AGING,
                PRIMARY_TYPE,
                QUALITY,
                REID,
                RELEASE,
                RELEASE_ACCENT,
                RGID,
                SCRIPT,
                SECONDARY_TYPE,
                STATUS,
                TAG,
                TRACKS,
                TRACKS_MEDIUM,
                TYPE
        );
    }
}
