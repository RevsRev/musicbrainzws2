package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.UrlList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a URL.
 */
@Getter @Setter
public final class MbUrlResult extends MbResult<MbEntity.MbUrl> {
    private Date created;
    private UrlList urlList;
}
