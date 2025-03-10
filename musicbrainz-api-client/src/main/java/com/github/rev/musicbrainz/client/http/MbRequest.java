package com.github.rev.musicbrainz.client.http;

import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.entity.MbEntity;

/**
 * Request to the MusicBrainz API.
 * @param <T> The entity associated with the request.
 */
public abstract class MbRequest<T extends MbEntity> implements MbParams {

    private final T entity;
    private final MbFormat resultFormat;

    /**
     * @param entity The entity this request is for.
     * @param resultFormat The format of the response body.
     */
    protected MbRequest(final T entity, final MbFormat resultFormat) {
        this.entity = entity;
        this.resultFormat = resultFormat;
    }

    /**
     * Get the path of the request relative to the music brainz host.
     * @return the path of this request.
     */
    public abstract String getPath();

    /**
     * @return the entity associated with this request.
     */
    public final T getEntity() {
        return entity;
    }

    /**
     * @return the result format of this request.
     */
    public final MbFormat getFormat() {
        return resultFormat;
    }
}
