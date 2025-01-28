package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Genres.
 */
public final class MbGenreQuery extends MbQuery<MbEntity.MbGenre> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
