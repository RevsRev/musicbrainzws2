package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.LabelList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Label.
 */
@Getter @Setter
public final class MbLabelResult extends MbResult<MbEntity.MbLabel> {
    private Date created;
    private LabelList labelList;
}
