package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Releases.
 */
public final class MbReleaseQuery extends MbQuery<MbEntity.MbRelease> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
