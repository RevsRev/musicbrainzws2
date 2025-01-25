package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Artists.
 */
public final class MbArtistQuery extends MbQuery<MbEntity.MbArtist> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
