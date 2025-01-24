package com.github.rev.musicbrainz.client.browse;

import com.github.rev.musicbrainz.client.MbEntity;

public interface MbBrowse<T extends MbEntity> {
    MbBrowseResult<T> doBrowse(MbBrowseRequest<T> request);
}
