package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.mapping.InputStreamMapper;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public final class GenerateSourcesHandlerFactory extends HandlerFactory {

    private final String resouceLocation;

    public GenerateSourcesHandlerFactory(String resouceLocation) {
        this.resouceLocation = resouceLocation;
    }

    @Override
    public <R> HttpClientResponseHandler<R> getXmlHandler(final Class<R> clazz) {
        final InputStreamMapper<R> xmlMapper = InputStreamMapper.factory(clazz, MbFormat.XML);
        final String path = buildPath(clazz, "xml");
        return getResponseHandler(xmlMapper, path);
    }

    @Override
    public <R> HttpClientResponseHandler<R> getJsonHandler(final Class<R> clazz) {
        final InputStreamMapper<R> jsonMapper = InputStreamMapper.factory(clazz, MbFormat.JSON);
        final String path = buildPath(clazz, "json");
        return getResponseHandler(jsonMapper, path);
    }

    private <R> String buildPath(Class<R> clazz, final String fileExtension) {
        return String.format("%s/%s.%s", resouceLocation, clazz.getSimpleName(), fileExtension);
    }

    private <R> HttpClientResponseHandler<R> getResponseHandler(final InputStreamMapper<R> om,
                                                                final String path) {
        return response -> {
            InputStream is = response.getEntity().getContent();
            FileOutputStream fos = new FileOutputStream(path);
            is.transferTo(fos);

            FileInputStream fis = new FileInputStream(path);
            return om.parse(fis);
        };
    }
}
