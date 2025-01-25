package com.github.rev.musicbrainz.client.http;

/**
 * Default implementation of MbParam with a String name and value.
 */
public final class MbDefaultParam implements MbParam {

    private final String name;
    private final String value;

    /**
     * @param name Name of the parameter.
     * @param value Value of the parameter.
     */
    public MbDefaultParam(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
