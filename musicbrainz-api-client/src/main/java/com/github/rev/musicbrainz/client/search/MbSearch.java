package com.github.rev.musicbrainz.client.search;

import com.github.rev.musicbrainz.client.MbEntity;

public interface MbSearch<T extends MbEntity> {
    MbSearchResult<T> doSearch(MbSearchRequest<T> request);
}
