package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.mapping.InputStreamMapper;
import lombok.Getter;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

@Getter
public abstract class HandlerFactory {

    public abstract <R> HttpClientResponseHandler<R> getXmlHandler(Class<R> clazz);
    public abstract <R> HttpClientResponseHandler<R> getJsonHandler(Class<R> clazz);

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
