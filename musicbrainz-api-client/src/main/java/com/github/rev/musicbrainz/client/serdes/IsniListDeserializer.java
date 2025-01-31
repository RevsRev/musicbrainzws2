package com.github.rev.musicbrainz.client.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.musicbrainz.ns.mmd_2.IsniList;

import java.io.IOException;

/**
 * JsonDeserializer implementation for IsniLists.
 */
public final class IsniListDeserializer extends JsonDeserializer<IsniList> {
    private final ObjectMapper om = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public IsniList deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return om.convertValue(node, IsniList.class);
        } catch (Exception e) {
            IsniList list = new IsniList();
            list.getIsni().add(node.get("isni").asText());
            return list;
        }
    }
}
