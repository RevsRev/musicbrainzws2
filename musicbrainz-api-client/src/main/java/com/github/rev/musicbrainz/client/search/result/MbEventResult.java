package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.EventList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Event.
 */
@Getter @Setter
public final class MbEventResult extends MbResult<MbEntity.MbEvent> {
    private Date created;
    private EventList eventList;
}
