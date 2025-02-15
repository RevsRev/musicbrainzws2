package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.mapping.InputStreamMapper;
import lombok.Getter;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

/**
 * Factory class for constructing handlers for results returned by the MusicBrainz API.
 */
@Getter
public abstract class HandlerFactory {

    /**
     * Get an XML handler for the desired return class.
     * @param clazz The return type of the handler.
     * @return A new XML handler instance.
     * @param <R> The type of object to be returned by the handler.
     */
    public abstract <R> HttpClientResponseHandler<R> getXmlHandler(Class<R> clazz);

    /**
     * Get a JSON handler for the desired return class.
     * @param clazz The return type of the handler.
     * @return A new XML handler instance.
     * @param <R> The type of object to be returned by the handler.
     */
    public abstract <R> HttpClientResponseHandler<R> getJsonHandler(Class<R> clazz);

    /**
     * Default factory which uses Jackson under the hood.
     */
    public static final class DefaultHandlerFactory extends HandlerFactory {

        @Override
        public <R> HttpClientResponseHandler<R> getXmlHandler(final Class<R> clazz) {
            final InputStreamMapper<R> xmlMapper = InputStreamMapper.factory(clazz, MbFormat.XML);
            return response -> xmlMapper.parse(response.getEntity().getContent());
        }

        @Override
        public <R> HttpClientResponseHandler<R> getJsonHandler(final Class<R> clazz) {
            final InputStreamMapper<R> jsonMapper = InputStreamMapper.factory(clazz, MbFormat.JSON);
            return response -> jsonMapper.parse(response.getEntity().getContent());
        }
    }
}
