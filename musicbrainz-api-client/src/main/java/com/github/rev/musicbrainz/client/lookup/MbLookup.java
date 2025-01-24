package com.github.rev.musicbrainz.client.lookup;

import com.github.rev.musicbrainz.client.MbEntity;

public interface MbLookup<T extends MbEntity> {
    MbLookupResult<T> doLookup(MbLookupRequest<T> request);
}
