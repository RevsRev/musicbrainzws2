package com.github.rev.musicbrainz.client.search;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.MbResult;

/**
 * Interface implemented by controllers searching a particular entity.
 *
 * @param <T> The entity type being searched.
 * @param <R> The search result return type.
 */
public interface MbSearch<T extends MbEntity, R extends MbResult<T>> {

    /**
     * Submit a search request to the MusicBrainz API.
     *
     * @param request The SearchRequest
     * @return The SearchResult.
     */
    R doSearch(MbSearchRequest<T> request);
}
