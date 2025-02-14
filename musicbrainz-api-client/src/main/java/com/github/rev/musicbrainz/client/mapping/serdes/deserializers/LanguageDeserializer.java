package com.github.rev.musicbrainz.client.mapping.serdes.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.musicbrainz.ns.mmd_2.LanguageList;
import org.musicbrainz.ns.mmd_2.Target;

import java.io.IOException;

/**
 * JsonDeserializer implementation for Language.
 */
public final class LanguageDeserializer extends JsonDeserializer<LanguageList.Language> {

    private final ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public LanguageList.Language deserialize(final JsonParser p,
                                             final DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        try {
            return om.convertValue(node, LanguageList.Language.class);
        } catch (Exception e) {
            if (!node.isTextual()) {
                throw e;
            }
            String value = node.textValue();
            LanguageList.Language language = new LanguageList.Language();
            language.setValue(value);
            return language;
        }
    }
}
