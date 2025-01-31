package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.browse.MbBrowse;
import com.github.rev.musicbrainz.client.browse.MbBrowseRequest;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.lookup.MbLookup;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.mapping.MbJsonMapper;
import com.github.rev.musicbrainz.client.search.MbSearch;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;

/**
 * A Controller for the MusicBrainz API that handles a single entity.
 * @param <T> The Entity handled by this particular controller.
 * @param <R> The Return type of the controller's actions.
 */
public final class MbEntityController<T extends MbEntity, R extends MbResult<T>> implements MbBrowse<T, R>,
                                                                                            MbLookup<T, R>,
                                                                                            MbSearch<T, R> {

    private final MbClient client;
    private final MbJsonMapper<R> parser;

    /**
     * @param client The MbClient used by this controller.
     * @param parser The MbParser responsible for turning JsonNodes into the result.
     */
    public MbEntityController(final MbClient client, final MbJsonMapper<R> parser) {
        this.client = client;
        this.parser = parser;
    }

    @Override
    public R doBrowse(final MbBrowseRequest<T> request) {
        return null;
    }

    @Override
    public R doLookup(final MbLookupRequest<T> request) {
        return null;
    }

    @Override
    public R doSearch(final MbSearchRequest<T> request) {
        return parser.parse(client.doSearch(request));
    }
}
