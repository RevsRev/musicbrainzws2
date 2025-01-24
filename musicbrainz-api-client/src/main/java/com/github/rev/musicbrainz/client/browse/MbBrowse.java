package com.github.rev.musicbrainz.client.browse;

import com.github.rev.musicbrainz.client.MbEntity;

/**
 * Interface implemented by controllers browsing a particular entity.
 *
 * @param <T> The entity type being browsed.
 */
public interface MbBrowse<T extends MbEntity> {

    /**
     * Submit a browse request to the MusicBrainz API.
     *
     * @param request The BrowseRequest
     * @return The BrowseResult.
     */
    MbBrowseResult<T> doBrowse(MbBrowseRequest<T> request);
}
