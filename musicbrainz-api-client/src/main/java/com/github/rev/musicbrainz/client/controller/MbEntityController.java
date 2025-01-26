package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.browse.MbBrowse;
import com.github.rev.musicbrainz.client.browse.MbBrowseRequest;
import com.github.rev.musicbrainz.client.browse.MbBrowseResult;
import com.github.rev.musicbrainz.client.lookup.MbLookup;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.lookup.MbLookupResult;
import com.github.rev.musicbrainz.client.search.MbSearch;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.MbSearchResult;

/**
 * A Controller for the MusicBrainz API that handles a single entity.
 * @param <T> The Entity handled by this particular controller.
 */
public final class MbEntityController<T extends MbEntity> implements MbBrowse<T>, MbLookup<T>, MbSearch<T> {

    private final MbClient client;

    /**
     * @param client The MbClient used by this controller.
     */
    public MbEntityController(final MbClient client) {
        this.client = client;
    }

    @Override
    public MbBrowseResult<T> doBrowse(final MbBrowseRequest<T> request) {
        return null;
    }

    @Override
    public MbLookupResult<T> doLookup(final MbLookupRequest<T> request) {
        return null;
    }

    @Override
    public MbSearchResult<T> doSearch(final MbSearchRequest<T> request) {
        return null;
    }
}
