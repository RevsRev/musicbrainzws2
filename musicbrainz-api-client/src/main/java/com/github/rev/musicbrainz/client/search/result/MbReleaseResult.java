package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.ReleaseList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Release.
 */
@Getter @Setter
public final class MbReleaseResult extends MbResult<MbEntity.MbRelease> {
    private Date created;
    private ReleaseList releaseList;
}
