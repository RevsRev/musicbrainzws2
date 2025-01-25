package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Urls.
 */
public final class MbUrlQuery extends MbQuery<MbEntity.MbUrl> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
