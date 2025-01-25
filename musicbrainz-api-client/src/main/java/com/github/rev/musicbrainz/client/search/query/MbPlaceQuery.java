package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Places.
 */
public final class MbPlaceQuery extends MbQuery<MbEntity.MbPlace> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
