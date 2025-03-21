package com.github.rev.musicbrainz.client;

/**
 * Interface for throttling requests to the musicbrainz api so we don't get banned.
 */
public interface ThrottleStrategy {
    /**
     * Method that should block before returning to the caller.
     */
    void throttle();
}
