package com.github.rev.musicbrainz.client.lookup;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.http.MbRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class MbLookupRequest<T extends MbEntity> extends MbRequest<T> {

    private final String mbid;
    private final Optional<MbParam> param;

    /**
     * @param entity       The entity this request is for.
     * @param resultFormat The format of the response body.
     * @param mbid         The music brainz id of the entity being looked up
     * @param param        The (include) parameter to be submitted with the request
     */
    private MbLookupRequest(final T entity,
                            final MbFormat resultFormat,
                            final String mbid,
                            final Optional<MbParam> param) {
        super(entity, resultFormat);
        this.mbid = mbid;
        this.param = param;
    }

    @Override
    public Collection<MbParam> getParams() {
        return param.isPresent() ? List.of(param.get()) : List.of();
    }

    @Override
    public String getPath() {
        return getEntity().getApiName() + "/" + mbid;
    }

}
