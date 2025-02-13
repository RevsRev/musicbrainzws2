package com.github.rev.musicbrainz.client.mapping.xml.deserializers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.musicbrainz.ns.mmd_2.Tag;
import org.musicbrainz.ns.mmd_2.TagList;

import java.io.IOException;

/**
 * JsonDeserializer implementation for TagLists.
 */
public final class TagListDeserializer extends JsonDeserializer<TagList> {
    private final ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public TagList deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return om.convertValue(node, TagList.class);
        } catch (Exception e) {
            TagList list = new TagList();
            list.getTag().add(om.convertValue(node.get("tag"), Tag.class));
            return list;
        }
    }
}
