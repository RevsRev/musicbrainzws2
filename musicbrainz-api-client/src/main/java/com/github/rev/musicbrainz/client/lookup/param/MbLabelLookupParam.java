package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Labels.
 */
public final class MbLabelLookupParam extends MbLookupParam<MbEntity.MbLabel> {

    /**
     * Include for RELEASES.
     */
    public static final String RELEASES = "releases";

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                RELEASES,
                RELATIONSHIPS
        );
    }
}
