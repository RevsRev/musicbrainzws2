package com.github.rev.musicbrainz.client.mapping.xml.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;

import java.io.IOException;

/**
 * JsonSerializer implementation for AliasLists.
 */
public final class AliasListSerializer extends StdSerializer<AliasList> {

    /**
     * Constructor.
     */
    public AliasListSerializer() {
        super(AliasList.class);
    }

    @Override
    public void serialize(final AliasList aliasList,
                          final JsonGenerator gen,
                          final SerializerProvider provider) throws IOException {
        if (aliasList == null || aliasList.getAlias() == null || aliasList.getAlias().isEmpty()) {
            gen.writeNull();
            return;
        }

        gen.writeStartObject();

        if (aliasList.getAlias().size() == 1) {
            gen.writeFieldName("alias");
            gen.writeObject(aliasList.getAlias().get(0));
        } else {
            if (aliasList.getCount() != null) {
                gen.writeNumberField("count", aliasList.getCount());
            }
            if (aliasList.getOffset() != null) {
                gen.writeNumberField("offset", aliasList.getOffset());
            }
            gen.writeFieldName("alias");
            gen.writeStartArray();
            for (Alias alias : aliasList.getAlias()) {
                gen.writeObject(alias);
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }
}
