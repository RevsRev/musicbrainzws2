package com.github.rev.musicbrainz.client.mapping.xml.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.musicbrainz.ns.mmd_2.Gender;

import java.io.IOException;

/**
 * JsonDeserializer implementation for Gender.
 */
public final class GenderDeserializer extends JsonDeserializer<Gender> {

    private final ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public Gender deserialize(final JsonParser p,
                              final DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Gender gender = om.convertValue(node, Gender.class);

        //For some stupid reason, the MusicBrainz API sometimes returns the content without labelling the key properly.
        JsonNode unnamedGenderDescription = node.get("");
        if (unnamedGenderDescription != null) {
            gender.setContent(unnamedGenderDescription.textValue());
        }
        return gender;
    }
}
