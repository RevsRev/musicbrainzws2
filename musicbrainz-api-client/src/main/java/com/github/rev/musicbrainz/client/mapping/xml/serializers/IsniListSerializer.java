package com.github.rev.musicbrainz.client.mapping.xml.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.musicbrainz.ns.mmd_2.IsniList;

import java.io.IOException;

/**
 * JsonSerializer implementation for IsniLists.
 */
public final class IsniListSerializer extends StdSerializer<IsniList> {

    /**
     * Constructor.
     */
    public IsniListSerializer() {
        super(IsniList.class);
    }

    @Override
    public void serialize(final IsniList isniList,
                          final JsonGenerator gen,
                          final SerializerProvider provider) throws IOException {
        if (isniList == null || isniList.getIsni() == null || isniList.getIsni().isEmpty()) {
            gen.writeNull();
            return;
        }

        gen.writeStartObject();
        gen.writeFieldName("isni");
        if (isniList.getIsni().size() == 1) {
            gen.writeString(isniList.getIsni().get(0));
        } else {
            gen.writeStartArray();
            for (String isni : isniList.getIsni()) {
                gen.writeString(isni);
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }
}
