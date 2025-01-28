package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for ReleaseGroups.
 */
public final class MbReleaseGroupQuery extends MbQuery<MbEntity.MbReleaseGroup> {

    /**
     * (part of) any alias attached to the release group (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * The MBID of any of the release group artists.
     */
    public static final String ARID = "arid";

    /**
     * (part of) the combined credited artist name for the release group, including join phrases (e.g. "Artist X
     * feat.").
     */
    public static final String ARTIST = "artist";

    /**
     * (part of) the name of any of the release group artists.
     */
    public static final String ARTIST_NAME = "artistname";

    /**
     * (part of) the release group's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * (part of) the credited name of any of the release group artists on this particular release group.
     */
    public static final String CREDIT_NAME = "creditname";

    /**
     * The release date of the earliest release in this release group (e.g. "1980-01-22").
     */
    public static final String FIRST_RELEASE_DATE = "firstreleasedate";

    /**
     * The primary type of the release group.
     */
    public static final String PRIMARY_TYPE = "primarytype";

    /**
     * The MBID of any of the releases in the release group.
     */
    public static final String REID = "reid";

    /**
     * (part of) the title of any of the releases in the release group.
     */
    public static final String RELEASE = "release";

    /**
     * (part of) the release group's title (diacritics are ignored).
     */
    public static final String RELEASE_GROUP = "releasegroup";

    /**
     * (part of) the release group's title (with the specified diacritics).
     */
    public static final String RELEASE_GROUP_ACCENT = "releasegroupaccent";

    /**
     * The number of releases in the release group.
     */
    public static final String RELEASES = "releases";

    /**
     * The release group's MBID.
     */
    public static final String RGID = "rgid";

    /**
     * Any of the secondary types of the release group.
     */
    public static final String SECONDARY_TYPE = "secondarytype";

    /**
     * The status of any of the releases in the release group.
     */
    public static final String STATUS = "status";

    /**
     * (part of) a tag attached to the release group.
     */
    public static final String TAG = "tag";

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
                COMMENT,
                CREDIT_NAME,
                FIRST_RELEASE_DATE,
                PRIMARY_TYPE,
                REID,
                RELEASE,
                RELEASE_GROUP,
                RELEASE_GROUP_ACCENT,
                RELEASES,
                RGID,
                SECONDARY_TYPE,
                STATUS,
                TAG,
                TYPE
        );
    }
}
