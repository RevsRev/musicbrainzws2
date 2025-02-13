package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.GenreList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Genre.
 */
@Getter @Setter
public final class MbGenreResult extends MbResult<MbEntity.MbGenre> {
    private Date created;
    private GenreList genreList;
}
