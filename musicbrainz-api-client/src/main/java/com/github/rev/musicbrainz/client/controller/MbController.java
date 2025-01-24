package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.browse.MbBrowse;
import com.github.rev.musicbrainz.client.lookup.MbLookup;
import com.github.rev.musicbrainz.client.search.MbSearch;

public class MbController<T extends MbEntity> implements MbBrowse, MbLookup, MbSearch {
}
