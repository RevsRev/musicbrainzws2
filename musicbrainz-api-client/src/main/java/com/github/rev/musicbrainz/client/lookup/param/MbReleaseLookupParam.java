package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Releases.
 */
public final class MbReleaseLookupParam extends MbLookupParam<MbEntity.MbRelease> {

    /**
     * Includes for ARTISTS.
     */
    public static final String ARTISTS = "artists";

    /**
     * Includes for COLLECTIONS.
     */
    public static final String COLLECTIONS = "collections";

    /**
     * Includes for LABELS.
     */
    public static final String LABELS = "labels";

    /**
     * Includes for RECORDINGS.
     */
    public static final String RECORDINGS = "recordings";

    /**
     * Includes for RELEASE_GROUPS.
     */
    public static final String RELEASE_GROUPS = "release-groups";

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                ARTISTS,
                COLLECTIONS,
                LABELS,
                RECORDINGS,
                RELEASE_GROUPS,
                RELATIONSHIPS
        );
    }
}
