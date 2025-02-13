package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.PlaceList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Place.
 */
@Getter @Setter
public final class MbPlaceResult extends MbResult<MbEntity.MbPlace> {
    private Date created;
    private PlaceList placeList;
}
