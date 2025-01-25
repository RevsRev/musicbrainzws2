package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Events.
 */
public final class MbEventQuery extends MbQuery<MbEntity.MbEvent> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
