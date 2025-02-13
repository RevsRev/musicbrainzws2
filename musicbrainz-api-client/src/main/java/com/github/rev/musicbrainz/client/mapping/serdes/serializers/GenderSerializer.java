package com.github.rev.musicbrainz.client.mapping.serdes.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.musicbrainz.ns.mmd_2.Gender;

import java.io.IOException;

/**
 * JsonSerializer implementation for Gender.
 */
public final class GenderSerializer extends StdSerializer<Gender> {

    /**
     * Constructor.
     */
    public GenderSerializer() {
        super(Gender.class);
    }

    @Override
    public void serialize(final Gender gender,
                          final JsonGenerator gen,
                          final SerializerProvider provider) throws IOException {
        if (gender == null) {
            gen.writeNull();
            return;
        }

        gen.writeStartObject();
        gen.writeStringField("id", gender.getId());  // Serialize 'id' as a field
        gen.writeStringField("", gender.getContent());  // Serialize 'content' as a field
        gen.writeEndObject();
    }
}
