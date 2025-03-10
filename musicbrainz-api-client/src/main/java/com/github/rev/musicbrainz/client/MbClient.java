package com.github.rev.musicbrainz.client;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.http.MbRequest;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Client for the MusicBrainz API. Responsible for transforming MbRequests into http requests and transforming the
 * http responses into MbResponses.
 */
public final class MbClient {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "musicbrainz.org";
    private static final int DEFAULT_PORT = -1;
    private static final String DEFAULT_PATH_PREFIX = "ws";
    private static final String DEFAULT_VERSION = "2";
    private static final String DEFAULT_PATH = "/" + DEFAULT_PATH_PREFIX + "/" + DEFAULT_VERSION;

    private final HttpClient httpClient = buildHttpClient();
    private final String protocol;
    private final String host;
    private final int port;
    private final String path;

    /**
     * Construct a client for the default MusicBrainz endpoint.
     */
    public MbClient() {
        this(DEFAULT_PROTOCOL, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_PATH);
    }

    /**
     * Construct a client for a specific endpoint hosting the MusicBrainz API.
     *
     * @param protocol        The endpoint hosting the MusicBrainz API.
     * @param host
     * @param port
     * @param path
     */
    public MbClient(final String protocol, final String host, final int port, final String path) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    private CloseableHttpClient buildHttpClient() {
        return HttpClientBuilder.create().build();
    }

    /**
     * Execute a search request.
     * @param request The request to be executed.
     * @param responseHandler The handler for the result returned by MusicBrainz.
     * @return The MbSearchResult for the given entity type.
     * @param <T> The type of entity being searched.
     * @param <R> The type being returned.
     */
    public <T extends MbEntity, R> R doGet(final MbRequest<T> request,
                                           final HttpClientResponseHandler<R> responseHandler) {
        try {
            ClassicHttpRequest httpRequest = createHttpRequest(request);
            return httpClient.execute(httpRequest, responseHandler);
        } catch (Exception e) {
            //TODO - Error handling / retries etc...
            throw new RuntimeException(e);
        }
    }

    private <T extends MbEntity> ClassicHttpRequest createHttpRequest(final MbRequest<T> request)
            throws URISyntaxException {

        StringBuilder paramSb = new StringBuilder();
        Collection<MbParam> params = request.getParams();
        boolean first = true;
        for (MbParam param : params) {
            if (first) {
                paramSb.append("?");
                first = false;
            } else {
                paramSb.append('&');
            }
            paramSb.append(param.getName()).append('=').append(param.getValue());
        }


        String query = paramSb.toString();

        return new HttpGet(new URI(protocol, host, path + "/" + request.getPath(), query));
    }

}
