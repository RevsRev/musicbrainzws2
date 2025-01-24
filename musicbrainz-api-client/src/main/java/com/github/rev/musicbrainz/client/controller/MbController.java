package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.browse.MbBrowse;
import com.github.rev.musicbrainz.client.browse.MbBrowseRequest;
import com.github.rev.musicbrainz.client.browse.MbBrowseResult;
import com.github.rev.musicbrainz.client.lookup.MbLookup;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.lookup.MbLookupResult;
import com.github.rev.musicbrainz.client.search.MbSearch;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.MbSearchResult;

public class MbController<T extends MbEntity> implements MbBrowse<T>, MbLookup<T>, MbSearch<T> {

    @Override
    public MbBrowseResult<T> doBrowse(MbBrowseRequest<T> request) {
        return null;
    }


    @Override
    public MbLookupResult<T> doLookup(MbLookupRequest<T> request) {
        return null;
    }

    @Override
    public MbSearchResult<T> doSearch(MbSearchRequest<T> request) {
        return null;
    }
}
