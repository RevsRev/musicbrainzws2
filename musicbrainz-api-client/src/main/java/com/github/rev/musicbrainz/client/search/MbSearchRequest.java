package com.github.rev.musicbrainz.client.search;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.MbResultFormat;
import com.github.rev.musicbrainz.client.http.MbDefaultParam;
import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.http.MbParams;
import com.github.rev.musicbrainz.client.search.query.MbQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

public final class MbSearchRequest<T extends MbEntity> implements MbParams {

    private static final String TYPE = "type";
    private static final String FMT = "fmt";
    private static final String QUERY = "query";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String DISMAX = "dismax";
    private static final String VERSION = "version";

    private static final int DEFAULT_SEARCH_LIMIT = 100;
    private static final int DEFAULT_SEARCH_OFFSET = 0;

    private final T entity;
    private final MbResultFormat format;
    private final MbQuery<T> query;
    private final int limit;
    private final int offset;
    private final boolean dismax;

    private MbSearchRequest(final T entity,
                            final MbResultFormat format,
                            final MbQuery<T> query,
                            final int limit,
                            final int offset,
                            final boolean dismax) {
        this.entity = entity;
        this.format = format;
        this.query = query;
        this.limit = limit;
        this.offset = offset;
        this.dismax = dismax;
    }

    @Override
    public Collection<MbParam> getParams() {
        return List.of(
                new MbDefaultParam("type", entity.toString()),
                new MbDefaultParam("fmt", format.toString()),
                query,
                new MbDefaultParam("limit", "" + limit),
                new MbDefaultParam("offset", "" + offset)
        );
    }

    @Getter @Setter
    public static final class Builder<T extends MbEntity> implements MbBuilder<MbSearchRequest<T>> {
        private T entity = null;
        private MbResultFormat format = MbResultFormat.XML;
        private MbQuery<T> query;
        private int limit = DEFAULT_SEARCH_LIMIT;
        private int offset = DEFAULT_SEARCH_OFFSET;
        private boolean dismax = false;

        @Override
        public boolean isValid() {
            return !(entity == null || format == null || query == null || limit <= 0 || offset <= 0);
        }

        @Override
        public MbSearchRequest<T> build() throws MbBuildException {
            if (!isValid()) {
                throw new MbBuildException("Could not build request");
            }
            return new MbSearchRequest<>(entity, format, query, limit, offset, dismax);
        }
    }
}
