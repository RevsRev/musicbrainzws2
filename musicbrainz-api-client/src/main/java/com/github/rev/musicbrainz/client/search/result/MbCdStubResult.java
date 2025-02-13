package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.CdstubList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a CD Stub.
 */
@Getter
@Setter
public final class MbCdStubResult extends MbResult<MbEntity.CdStub> {
    private Date created;
    private CdstubList cdstubList;
}
