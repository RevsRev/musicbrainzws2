package com.github.rev.musicbrainz.client.search;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.MbResultFormat;
import com.github.rev.musicbrainz.client.http.MbDefaultParam;
import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.http.MbRequest;
import com.github.rev.musicbrainz.client.search.query.MbQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class MbSearchRequest<T extends MbEntity> extends MbRequest<T> {

    private static final String TYPE = "type";
    private static final String FMT = "fmt";
    private static final String QUERY = "query";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String DISMAX = "dismax";
    private static final String VERSION = "version";

    private static final int DEFAULT_SEARCH_LIMIT = 100;
    private static final int DEFAULT_SEARCH_OFFSET = 0;

    private final Optional<MbResultFormat> format;
    private final Optional<String> type;
    private final MbQuery<T> query;
    private final int limit;
    private final int offset;
    private final boolean dismax;

    private MbSearchRequest(final T entity,
                            final Optional<MbResultFormat> format,
                            final Optional<String> type,
                            final MbQuery<T> query,
                            final int limit,
                            final int offset,
                            final boolean dismax) {
        super(entity);
        this.format = format;
        this.type = type;
        this.query = query;
        this.limit = limit;
        this.offset = offset;
        this.dismax = dismax;
    }

    @Override
    public Collection<MbParam> getParams() {
        List<MbParam> params = new ArrayList<>();

        if (type.isPresent()) {
            params.add(new MbDefaultParam(TYPE, getEntity().getApiName()));
        }

        if (format.isPresent()) {
            params.add(new MbDefaultParam(FMT, format.toString()));
        }

        params.add(query);
        params.add(new MbDefaultParam(LIMIT, "" + limit));
        params.add(new MbDefaultParam(OFFSET, "" + offset));

        return params;
    }

    @Getter @Setter
    public static final class Builder<T extends MbEntity> implements MbBuilder<MbSearchRequest<T>> {
        private T entity = null;
        private Optional<String> type = Optional.empty();
        private Optional<MbResultFormat> format = Optional.empty();
        private MbQuery<T> query;
        private int limit = DEFAULT_SEARCH_LIMIT;
        private int offset = DEFAULT_SEARCH_OFFSET;
        private boolean dismax = false;

        @Override
        public boolean isValid() {
            return !(entity == null || format == null || query == null || limit <= 0 || offset < 0);
        }

        @Override
        public MbSearchRequest<T> build() throws MbBuildException {
            if (!isValid()) {
                throw new MbBuildException("Could not build request");
            }
            return new MbSearchRequest<>(entity, format, type, query, limit, offset, dismax);
        }
    }
}
