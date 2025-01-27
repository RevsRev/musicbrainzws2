package com.github.rev.musicbrainz.client;

import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.http.MbRequest;
import com.github.rev.musicbrainz.client.search.MbSearchResult;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;
import java.util.Collection;

/**
 * Client for the MusicBrainz API. Responsible for transforming MbRequests into http requests and transforming the
 * http responses into MbResponses.
 */
public final class MbClient {

    private static final String DEFAULT_PROTOCOL = "http";
    private static final String DEFAULT_HOST = "musicbrainz.org";
    private static final String DEFAULT_PATH_PREFIX = "ws";
    private static final String DEFAULT_VERSION = "2";
    private static final String DEFAULT_ENDPOINT = DEFAULT_PROTOCOL + "://"
            + DEFAULT_HOST + "/"
            + DEFAULT_PATH_PREFIX + "/"
            + DEFAULT_VERSION;

    private final String endpoint;

    private final HttpClient httpClient = buildHttpClient();

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

    private CloseableHttpClient buildHttpClient() {
        return HttpClientBuilder.create().build();
    }

    /**
     * Execute a search request.
     * @param request The request to be made.
     * @return The MbSearchResult for the given entity type.
     * @param <T> The type of entity being searched.
     */
    public <T extends MbEntity> MbSearchResult<T> doSearch(final MbRequest<T> request) {
        ClassicHttpRequest httpRequest = createHttpRequest(request);
        try {
            return httpClient.execute(httpRequest, testResponseHandler());
        } catch (IOException e) {
            //TODO - Error handling / retries etc...
            throw new RuntimeException(e);
        }
    }

    private <T extends MbEntity> HttpClientResponseHandler<MbSearchResult<T>> testResponseHandler() {
        return response -> {
            StringBuilder sb = new StringBuilder();
            sb.append("CODE: ").append(response.getCode()).append('\n');
            sb.append("BODY: ").append(response.getEntity());

            System.out.println(sb);

            return null;
        };
    }

    private <T extends MbEntity> ClassicHttpRequest createHttpRequest(final MbRequest<T> request) {
        String name = request.getEntity().getApiName();
        Collection<MbParam> params = request.getParams();

        StringBuilder urlSb = new StringBuilder();
        urlSb.append(endpoint)
                .append("/")
                .append(name)
                .append("/");

        boolean first = true;
        for (MbParam param : params) {
            if (first) {
                urlSb.append("?");
                first = false;
            } else {
                urlSb.append('&');
            }
            urlSb.append(param.getName()).append('=').append(param.getValue());
        }
        return new HttpGet(urlSb.toString());
    }

}
