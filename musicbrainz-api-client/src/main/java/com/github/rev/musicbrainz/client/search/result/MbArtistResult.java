package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.ArtistList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Artist.
 */
@Getter @Setter
public class MbArtistResult extends MbResult<MbEntity.MbArtist> {
    private Date created;
    private ArtistList artistList;
}
