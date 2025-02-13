package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.RecordingList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up a Recording.
 */
@Getter @Setter
public final class MbRecordingResult extends MbResult<MbEntity.MbRecording> {
    private Date created;
    private RecordingList recordingList;
}
