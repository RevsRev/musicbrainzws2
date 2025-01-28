package com.github.rev.musicbrainz.client.artist;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Artist.
 */
public class MbArtistResult extends MbResult<MbEntity.MbArtist> {
    private final Date created;
    private final ArtistList artistList;

    /**
     * @param created The time this result was created.
     * @param artistList The artist list of the result.
     */
    public MbArtistResult(final Date created, final ArtistList artistList) {
        this.created = created;
        this.artistList = artistList;
    }
}
