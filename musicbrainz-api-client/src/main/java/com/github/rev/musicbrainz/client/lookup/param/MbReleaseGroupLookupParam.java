package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for ReleaseGroups.
 */
public final class MbReleaseGroupLookupParam extends MbLookupParam<MbEntity.MbReleaseGroup> {

    /**
     * Includes for ARTISTS.
     */
    public static final String ARTISTS = "artists";

    /**
     * Includes for RELEASES.
     */
    public static final String RELEASES = "releases";

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                ARTISTS,
                RELEASES,
                RELATIONSHIPS
        );
    }
}
