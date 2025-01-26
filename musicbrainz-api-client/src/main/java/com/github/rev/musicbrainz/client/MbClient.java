package com.github.rev.musicbrainz.client;

/**
 * Client for the MusicBrainz API. Responsible for transforming MbRequests into http requests and transforming the
 * http responses into MbResponses.
 */
public final class MbClient {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "musicbrainz.org";
    private static final String DEFAULT_PATH_PREFIX = "ws";
    private static final String DEFAULT_VERSION = "2";
    private static final String DEFAULT_ENDPOINT = DEFAULT_PROTOCOL + "/"
            + DEFAULT_HOST + "/"
            + DEFAULT_PATH_PREFIX + "/"
            + DEFAULT_VERSION;

    private final String endpoint;

    /**
     * Construct a client for the default MusicBrainz endpoint.
     */
    public MbClient() {
        this(DEFAULT_ENDPOINT);
    }

    /**
     * Construct a client for a specific endpoint hosting the MusicBrainz API.
     * @param endpoint The endpoint hosting the MusicBrainz API.
     */
    public MbClient(final String endpoint) {
        this.endpoint = endpoint;
    }

}
