package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Instruments.
 */
public final class MbInstrumentQuery extends MbQuery<MbEntity.MbInstrument> {
    @Override
    public Set<String> getQueryFields() {
        return Set.of();
    }
}
