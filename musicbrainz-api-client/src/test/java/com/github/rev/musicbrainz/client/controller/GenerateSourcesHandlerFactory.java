package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.mapping.InputStreamMapper;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public final class GenerateSourcesHandlerFactory extends HandlerFactory {

    private final String resouceLocation;
    private final Set<Class<?>> classesToWriteSources;

    public GenerateSourcesHandlerFactory(String resouceLocation) {
        this(resouceLocation, new HashSet<>());
    }

    public GenerateSourcesHandlerFactory(String resouceLocation, Set<Class<?>> classesToWriteSources) {
        this.resouceLocation = resouceLocation;
        this.classesToWriteSources = classesToWriteSources;
    }


    @Override
    public <R> HttpClientResponseHandler<R> getXmlHandler(final Class<R> clazz) {
        return getResponseHandler(clazz, MbFormat.XML);
    }

    @Override
    public <R> HttpClientResponseHandler<R> getJsonHandler(final Class<R> clazz) {
        return getResponseHandler(clazz, MbFormat.JSON);
    }

    private <R> String buildPath(final Class<R> clazz, final MbFormat format) {
        String fileExtension = format == MbFormat.XML ? "xml" : "json";
        return String.format("%s/%s.%s", resouceLocation, clazz.getSimpleName(), fileExtension);
    }

    private <R> HttpClientResponseHandler<R> getResponseHandler(final Class<R> clazz, final MbFormat format) {
        final InputStreamMapper<R> om = InputStreamMapper.factory(clazz, format);
        final String path = buildPath(clazz, format);

        if (classesToWriteSources.isEmpty() || classesToWriteSources.contains(clazz)) {
            return writeAndReadResponseHandler(path, om);
        }
        return readOnlyHandler(path, om);
    }

    private static <R> HttpClientResponseHandler<R> writeAndReadResponseHandler(final String path,
                                                                                final InputStreamMapper<R> om) {
        return response ->
        {
            InputStream is = response.getEntity().getContent();
            FileOutputStream fos = new FileOutputStream(path);
            is.transferTo(fos);

            FileInputStream fis = new FileInputStream(path);
            return om.parse(fis);
        };
    }

    private static <R> HttpClientResponseHandler<R> readOnlyHandler(final String path,
                                                                    final InputStreamMapper<R> om) {
        return response ->
        {
            FileInputStream fis = new FileInputStream(path);
            return om.parse(fis);
        };
    }
}
