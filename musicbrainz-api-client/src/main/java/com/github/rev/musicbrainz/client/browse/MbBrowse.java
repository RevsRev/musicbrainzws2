package com.github.rev.musicbrainz.client.browse;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;

/**
 * Interface implemented by controllers browsing a particular entity.
 *
 * @param <T> The entity type being browsed.
 * @param <R> The browse result return type.
 */
public interface MbBrowse<T extends MbEntity, R extends MbResult<T>> {

    /**
     * Submit a browse request to the MusicBrainz API.
     *
     * @param request The BrowseRequest
     * @return The BrowseResult.
     */
    R doBrowse(MbBrowseRequest<T> request);
}
