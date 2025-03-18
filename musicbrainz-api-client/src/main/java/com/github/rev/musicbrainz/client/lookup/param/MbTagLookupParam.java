package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

public final class MbTagLookupParam extends MbLookupParam<MbEntity.MbTag> {

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                RELATIONSHIPS
        );
    }
}
