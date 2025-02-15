package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.browse.MbBrowse;
import com.github.rev.musicbrainz.client.browse.MbBrowseRequest;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.lookup.MbLookup;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.search.MbSearch;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

/**
 * A Controller for the MusicBrainz API that handles a single entity.
 * @param <T> The Entity handled by this particular controller.
 * @param <R> The Return type of the controller's actions.
 */
public final class MbEntityController<T extends MbEntity, R extends MbResult<T>> implements MbBrowse<T, R>,
                                                                                            MbLookup<T, R>,
                                                                                            MbSearch<T, R> {

    private final MbClient client;
    private final HttpClientResponseHandler<R> xmlHandler;
    private final HttpClientResponseHandler<R> jsonHandler;

    /**
     * A controller to handle all requests associated with a specific entity.
     * @param client The client used by the controller to query the MusicBrainz API.
     * @param xmlHandler A handler for XML responses.
     * @param jsonHandler A handler for JSON responses.
     */
    public MbEntityController(final MbClient client,
                              final HttpClientResponseHandler<R> xmlHandler,
                              final HttpClientResponseHandler<R> jsonHandler) {
        this.client = client;
        this.xmlHandler = xmlHandler;
        this.jsonHandler = jsonHandler;
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
        return client.doSearch(request, getHandler(request.getFormat()));
    }

    /**
     * Factory method for constructing an EntityController.
     * @param client The client to be used by the Controller.
     * @param clazz The return type of the Controller's actions.
     * @param handlerFactory Factory for new handlers used to parse responses from the MusicBrainz API.
     * @return A new MbEntityController instance
     * @param <T> The Entity handled by this particular controller.
     * @param <R> The Return type of the controller's actions.
     */
    public static <T extends MbEntity, R extends MbResult<T>> MbEntityController<T, R> factory(
            final MbClient client,
            final Class<R> clazz,
            final HandlerFactory handlerFactory) {

        return new MbEntityController<>(client,
                handlerFactory.getXmlHandler(clazz),
                handlerFactory.getJsonHandler(clazz));

    }

    private HttpClientResponseHandler<R> getHandler(final MbFormat format) {
        return format == MbFormat.XML ? xmlHandler : jsonHandler;
    }
}
