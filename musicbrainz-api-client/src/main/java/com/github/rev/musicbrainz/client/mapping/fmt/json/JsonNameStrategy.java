package com.github.rev.musicbrainz.client.mapping.fmt.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.util.Map;

/**
 * Naming strategy that deals with plural field names from the music brainz api.
 */
public final class JsonNameStrategy extends PropertyNamingStrategies.KebabCaseStrategy {

    private final Map<String, String> pluralNamesMap;

    /**
     * Constructor.
     * @param pluralNamesMap
     */
    public JsonNameStrategy(final Map<String, String> pluralNamesMap) {
        this.pluralNamesMap = pluralNamesMap;
    }

    @Override
    public String translate(final String propertyName) {
        return super.translate(pluralNamesMap.getOrDefault(propertyName, propertyName));
    }
}
