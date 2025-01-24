package com.github.rev.musicbrainz.client.search;

import com.github.rev.musicbrainz.client.MbEntity;

/**
 * Interface implemented by controllers searching a particular entity.
 *
 * @param <T> The entity type being searched.
 */
public interface MbSearch<T extends MbEntity> {

    /**
     * Submit a search request to the MusicBrainz API.
     *
     * @param request The SearchRequest
     * @return The SearchResult.
     */
    MbSearchResult<T> doSearch(MbSearchRequest<T> request);
}
