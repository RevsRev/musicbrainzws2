package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Labels.
 */
public final class MbLabelQuery extends MbQuery<MbEntity.MbLabel> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
