package com.github.rev.musicbrainz.client.lookup;

import com.github.rev.musicbrainz.client.MbEntity;

/**
 * Interface implemented by controllers looking up a particular entity.
 *
 * @param <T> The entity type being looked up.
 */
public interface MbLookup<T extends MbEntity> {

    /**
     * Submit a lookup request to the MusicBrainz API.
     *
     * @param request The LookupRequest
     * @return The LookupResult.
     */
    MbLookupResult<T> doLookup(MbLookupRequest<T> request);
}
