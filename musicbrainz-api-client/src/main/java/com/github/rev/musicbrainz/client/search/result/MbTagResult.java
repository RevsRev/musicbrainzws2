package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.TagList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Tag.
 */
@Getter @Setter
public final class MbTagResult extends MbResult<MbEntity.MbTag> {
    private Date created;
    private TagList tagList;
}
