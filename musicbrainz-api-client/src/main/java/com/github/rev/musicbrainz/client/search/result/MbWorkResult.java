package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.WorkList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Work.
 */
@Getter @Setter
public final class MbWorkResult extends MbResult<MbEntity.MbWork> {
    private Date created;
    private WorkList workList;
}
