package com.github.rev.musicbrainz.client.http;

import com.github.rev.musicbrainz.client.ThrottleStrategy;

public class ThrottleStrategyImpl implements ThrottleStrategy {

    private final int waitMillis;

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
