package com.github.rev.musicbrainz.client.http;

import com.github.rev.musicbrainz.client.ThrottleStrategy;

/**
 * A naive implementation of a throttle strategy that blocks the calling thread for a
 * configurable amount of time.
 */
public final class ThrottleStrategyImpl implements ThrottleStrategy {

    private final int waitMillis;

    /**
     * Constructor.
     * @param waitMillis
     */
    public ThrottleStrategyImpl(int waitMillis) {
        this.waitMillis = waitMillis;
    }

    @Override
    public void throttle() {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
