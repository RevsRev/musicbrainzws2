package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for CDStubs.
 */
public final class MbCdStubQuery extends MbQuery<MbEntity.CdStub> {

    /**
     * The date the CD stub was added (e.g. "2020-01-22").
     */
    public static final String ADDED = "added";
    /**
     * (part of) the artist name set on the CD stub.
     */
    public static final String ARTIST = "artist";
    /**
     * The barcode set on the CD stub.
     */
    public static final String BARCODE = "barcode";
    /**
     * (part of) the comment set on the CD stub.
     */
    public static final String COMMENT = "comment";
    /**
     * The CD stub's Disc ID.
     */
    public static final String DISCID = "discid";
    /**
     * (part of) the release title set on the CD stub.
     */
    public static final String TITLE = "title";
    /**
     * The number of tracks on the CD stub.
     */
    public static final String TRACKS = "tracks";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ADDED,
                ARTIST,
                BARCODE,
                COMMENT,
                DISCID,
                TITLE,
                TRACKS
        );
    }
}
