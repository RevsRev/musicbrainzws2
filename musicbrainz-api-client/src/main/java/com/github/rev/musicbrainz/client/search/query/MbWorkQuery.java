package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Works.
 */
public final class MbWorkQuery extends MbQuery<MbEntity.MbWork> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
