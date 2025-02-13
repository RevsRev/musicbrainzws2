package com.github.rev.musicbrainz.client.mapping.xml.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.musicbrainz.ns.mmd_2.IpiList;

import java.io.IOException;

/**
 * JsonSerializer implementation for IpiLists.
 */
public final class IpiListSerializer extends StdSerializer<IpiList> {

    /**
     * Constructor.
     */
    public IpiListSerializer() {
        super(IpiList.class);
    }

    @Override
    public void serialize(final IpiList isniList,
                          final JsonGenerator gen,
                          final SerializerProvider provider) throws IOException {
        if (isniList == null || isniList.getIpi() == null || isniList.getIpi().isEmpty()) {
            gen.writeNull();
            return;
        }

        gen.writeStartObject();
        gen.writeFieldName("ipi");
        if (isniList.getIpi().size() == 1) {
            gen.writeString(isniList.getIpi().get(0));
        } else {
            gen.writeStartArray();
            for (String isni : isniList.getIpi()) {
                gen.writeString(isni);
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }
}
