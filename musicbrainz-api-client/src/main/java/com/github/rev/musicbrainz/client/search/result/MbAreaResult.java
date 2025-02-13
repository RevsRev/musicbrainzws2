package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.AreaList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Area.
 */
@Getter @Setter
public final class MbAreaResult extends MbResult<MbEntity.MbArea> {
    private Date created;
    private AreaList areaList;
}
