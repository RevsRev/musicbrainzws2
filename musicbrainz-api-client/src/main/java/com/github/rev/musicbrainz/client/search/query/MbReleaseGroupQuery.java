package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for ReleaseGroups.
 */
public final class MbReleaseGroupQuery extends MbQuery<MbEntity.MbReleaseGroup> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
