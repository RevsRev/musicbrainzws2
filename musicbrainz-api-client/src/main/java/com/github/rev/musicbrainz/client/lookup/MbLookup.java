package com.github.rev.musicbrainz.client.lookup;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.MbResult;

/**
 * Interface implemented by controllers looking up a particular entity.
 *
 * @param <T> The entity type being looked up.
 * @param <R> The lookup result return type.
 */
public interface MbLookup<T extends MbEntity, R extends MbResult<T>> {

    /**
     * Submit a lookup request to the MusicBrainz API.
     *
     * @param request The LookupRequest
     * @return The LookupResult.
     */
    R doLookup(MbLookupRequest<T> request);
}
