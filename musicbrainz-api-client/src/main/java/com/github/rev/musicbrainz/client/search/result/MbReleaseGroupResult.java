package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.ReleaseGroupList;

import java.util.Date;

@Getter @Setter
public final class MbReleaseGroupResult extends MbResult<MbEntity.MbReleaseGroup> {
    private Date created;
    private ReleaseGroupList releaseGroupList;
}
