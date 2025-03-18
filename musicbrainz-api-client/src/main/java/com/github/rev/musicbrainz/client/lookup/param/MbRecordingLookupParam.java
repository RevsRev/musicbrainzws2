package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Recordings.
 */
public final class MbRecordingLookupParam extends MbLookupParam<MbEntity.MbRecording> {

    /**
     * Include for ARTISTS.
     */
    private static final String ARTISTS = "artists";

    /**
     * Include for RELEASES.
     */
    private static final String RELEASES = "releases";

    /**
     * Include for RELEASE-GROUPS.
     */
    private static final String RELEASE_GROUPS = "release-groups";

    /**
     * Include for ISRCS.
     */
    private static final String ISRCS = "isrcs";

    /**
     * Include for URL-RELS.
     */
    private static final String URL_RELS = "url-rels";

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                ARTISTS,
                RELEASES,
                RELEASE_GROUPS,
                ISRCS,
                URL_RELS,
                RELATIONSHIPS
        );
    }
}
