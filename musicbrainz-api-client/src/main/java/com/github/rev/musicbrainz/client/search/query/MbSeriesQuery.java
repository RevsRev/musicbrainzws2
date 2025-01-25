package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Series.
 */
public final class MbSeriesQuery extends MbQuery<MbEntity.MbSeries> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
