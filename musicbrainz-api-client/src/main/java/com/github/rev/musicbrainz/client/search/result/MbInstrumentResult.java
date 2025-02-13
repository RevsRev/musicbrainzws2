package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.InstrumentList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Instrument.
 */
@Getter @Setter
public final class MbInstrumentResult extends MbResult<MbEntity.MbInstrument> {
    private Date created;
    private InstrumentList instrumentList;
}
