package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for CDStubs.
 */
public class MbCdStubQuery extends MbQuery<MbEntity.CdStub> {
    @Override
    public final Set<String> getQueryFields() {
        return Set.of();
    }
}
