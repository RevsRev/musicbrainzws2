package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

public final class MbRecordingQuery extends MbQuery<MbEntity.MbRecording> {

    /**
     * (part of) any alias attached to the recording (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * The MBID of any of the recording artists.
     */
    public static final String ARID = "arid";

    /**
     * (part of) the combined credited artist name for the recording, including join phrases (e.g. "Artist X feat.").
     */
    public static final String ARTIST = "artist";

    /**
     * (part of) the name of any of the recording artists.
     */
    public static final String ARTIST_NAME = "artistname";

    /**
     * (part of) the recording's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The 2-letter code (ISO 3166-1 alpha-2) for the country any release of this recording was released in.
     */
    public static final String COUNTRY = "country";

    /**
     * (part of) the credited name of any of the recording artists on this particular recording.
     */
    public static final String CREDIT_NAME = "creditname";

    /**
     * The release date of any release including this recording (e.g. "1980-01-22").
     */
    public static final String DATE = "date";

    /**
     * The recording duration in milliseconds.
     */
    public static final String DUR = "dur";

    /**
     * The release date of the earliest release including this recording (e.g. "1980-01-22").
     */
    public static final String FIRST_RELEASEDATE = "firstreleasedate";

    /**
     * The format of any medium including this recording (insensitive to case, spaces, and separators).
     */
    public static final String FORMAT = "format";

    /**
     * Any ISRC associated to the recording.
     */
    public static final String ISRC = "isrc";

    /**
     * The free-text number of the track on any medium including this recording (e.g. "A4").
     */
    public static final String NUMBER = "number";

    /**
     * The position inside its release of any medium including this recording (starts at 1).
     */
    public static final String POSITION = "position";

    /**
     * The primary type of any release group including this recording.
     */
    public static final String PRIMARY_TYPE = "primarytype";

    /**
     * The recording duration, quantized (duration in milliseconds / 2000).
     */
    public static final String QDUR = "qdur";

    /**
     * (part of) the recording's name, or the name of a track connected to this recording (diacritics are ignored).
     */
    public static final String RECORDING = "recording";

    /**
     * (part of) the recording's name, or the name of a track connected to this recording (with the specified
     * diacritics).
     */
    public static final String RECORDING_ACCENT = "recordingaccent";

    /**
     * The MBID of any release including this recording.
     */
    public static final String REID = "reid";

    /**
     * (part of) the name of any release including this recording.
     */
    public static final String RELEASE = "release";

    /**
     * The MBID of any release group including this recording.
     */
    public static final String RGID = "rgid";

    /**
     * The recording's MBID.
     */
    public static final String RID = "rid";

    /**
     * Any of the secondary types of any release group including this recording.
     */
    public static final String SECONDARY_TYPE = "secondarytype";

    /**
     * The status of any release including this recording.
     */
    public static final String STATUS = "status";

    /**
     * (part of) a tag attached to the recording.
     */
    public static final String TAG = "tag";

    /**
     * The MBID of a track connected to this recording.
     */
    public static final String TID = "tid";

    /**
     * The position of the track on any medium including this recording (starts at 1, pre-gaps at 0).
     */
    public static final String TNUM = "tnum";

    /**
     * The number of tracks on any medium including this recording.
     */
    public static final String TRACKS = "tracks";

    /**
     * The number of tracks on any release (as a whole) including this recording.
     */
    public static final String TRACKS_RELEASE = "tracksrelease";

    /**
     * Legacy release group type field that predates the ability to set multiple types (see calculation code).
     */
    public static final String TYPE = "type";

    /**
     * A boolean flag (true/false) indicating whether the recording is a video recording.
     */
    public static final String VIDEO = "video";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                ARID,
                ARTIST,
                ARTIST_NAME,
                COMMENT,
                COUNTRY,
                CREDIT_NAME,
                DATE,
                DUR,
                FIRST_RELEASEDATE,
                FORMAT,
                ISRC,
                NUMBER,
                POSITION,
                PRIMARY_TYPE,
                QDUR,
                RECORDING,
                RECORDING_ACCENT,
                REID,
                RELEASE,
                RGID,
                RID,
                SECONDARY_TYPE,
                STATUS,
                TAG,
                TID,
                TNUM,
                TRACKS,
                TRACKS_RELEASE,
                TYPE,
                VIDEO
        );
    }
}
