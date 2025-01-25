package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.MbEntity;

import java.util.Set;

/**
 * MbQuery for Events.
 */
public final class MbEventQuery extends MbQuery<MbEntity.MbEvent> {

    /**
     * (part of) any alias attached to the artist (diacritics are ignored).
     */
    public static final String ALIAS = "alias";

    /**
     * The MBID of an area related to the event.
     */
    public static final String AID = "aid";

    /**
     * (part of) the name of an area related to the event.
     */
    public static final String AREA = "area";

    /**
     * The MBID of an artist related to the event.
     */
    public static final String ARID = "arid";

    /**
     * (part of) the name of an artist related to the event.
     */
    public static final String ARTIST = "artist";

    /**
     * The event's begin date (e.g. "1980-01-22").
     */
    public static final String BEGIN = "begin";

    /**
     * (part of) the artist's disambiguation comment.
     */
    public static final String COMMENT = "comment";

    /**
     * The event's end date (e.g. "1980-01-22").
     */
    public static final String END = "end";

    /**
     * A boolean flag (true/false) indicating whether or not the event has an end date set.
     */
    public static final String ENDED = "ended";

    /**
     * The MBID of the event.
     */
    public static final String EID = "eid";

    /**
     * (part of) the event's name (diacritics are ignored).
     */
    public static final String EVENT = "event";

    /**
     * (part of) the event's name (with the specified diacritics).
     */
    public static final String EVENT_ACCENT = "eventaccent";

    /**
     * The MBID of a place related to the event.
     */
    public static final String PID = "pid";

    /**
     * (part of) the name of a place related to the event.
     */
    public static final String PLACE = "place";

    /**
     * (part of) a tag attached to the event.
     */
    public static final String TAG = "tag";

    /**
     * The event's type.
     */
    public static final String TYPE = "type";


    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ALIAS,
                AID,
                AREA,
                ARID,
                ARTIST,
                BEGIN,
                COMMENT,
                END,
                ENDED,
                EID,
                EVENT,
                EVENT_ACCENT,
                PID,
                PLACE,
                TAG,
                TYPE
        );
    }
}
