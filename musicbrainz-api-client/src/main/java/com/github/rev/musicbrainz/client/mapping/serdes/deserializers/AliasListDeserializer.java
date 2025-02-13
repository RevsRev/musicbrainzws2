package com.github.rev.musicbrainz.client.mapping.serdes.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;

import java.io.IOException;

/**
 * JsonDeserializer implementation for AliasLists.
 */
public final class AliasListDeserializer extends JsonDeserializer<AliasList> {
    private final ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public AliasList deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return om.convertValue(node, AliasList.class);
        } catch (Exception e) {
            AliasList list = new AliasList();
            list.getAlias().add(om.convertValue(node.get("alias"), Alias.class));
            return list;
        }
    }
}
