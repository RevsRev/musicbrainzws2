package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Areas.
 */
public final class MbAreaQuery extends MbQuery<MbEntity.MbArea> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
