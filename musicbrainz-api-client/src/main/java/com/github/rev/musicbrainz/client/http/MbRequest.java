package com.github.rev.musicbrainz.client.http;

import com.github.rev.musicbrainz.client.entity.MbEntity;

/**
 * Request to the MusicBrainz API.
 * @param <T> The entity associated with the request.
 */
public abstract class MbRequest<T extends MbEntity> implements MbParams {

    private final T entity;

    /**
     * @param entity The entity this request is for.
     */
    protected MbRequest(final T entity) {
        this.entity = entity;
    }

    /**
     * @return the entity associated with this request.
     */
    public final T getEntity() {
        return entity;
    }
}
