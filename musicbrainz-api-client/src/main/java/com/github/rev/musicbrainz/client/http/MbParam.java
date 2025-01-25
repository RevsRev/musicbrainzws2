package com.github.rev.musicbrainz.client.http;

/**
 * Interface for API parameters.
 */
public interface MbParam {
    /**
     * @return The name of the parameter, as required by the API.
     */
    String getName();

    /**
     * @return The value of the parameter.
     */
    String getValue();
}
