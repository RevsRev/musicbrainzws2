package com.github.rev.musicbrainz.client;

import lombok.Getter;

/**
 * Defines the format (to be) returned by the MusicBrainz API.
 */
@Getter
public enum MbFormat {
    /**
     * XML format.
     */
    XML("xml"),

    /**
     * JSON format.
     */
    JSON("json");

    private final String value;

    MbFormat(final String value) {
        this.value = value;
    }
}
