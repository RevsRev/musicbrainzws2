package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

public final class MbTagQuery extends MbQuery<MbEntity.MbTag> {

    /**
     * (part of) the tag's name.
     */
    public static final String TAG = "tag";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                TAG
        );
    }
}
