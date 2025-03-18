package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Artists.
 */
public final class MbArtistLookupParam extends MbLookupParam<MbEntity.MbArtist> {

    /**
     * Include for RECORDINGS.
     */
    public static final String RECORDINGS = "recordings";

    /**
     * Include for RELEASES.
     */
    public static final String RELEASES = "releases";

    /**
     * Include for RELEASE_GROUPS.
     */
    public static final String RELEASE_GROUPS = "release-groups";

    /**
     * Include for WORKS.
     */
    public static final String WORKS = "works";

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                RECORDINGS,
                RELEASES,
                RELEASE_GROUPS,
                WORKS,
                RELATIONSHIPS
        );
    }
}
