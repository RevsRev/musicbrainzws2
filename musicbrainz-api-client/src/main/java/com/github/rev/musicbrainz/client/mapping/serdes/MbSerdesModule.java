package com.github.rev.musicbrainz.client.mapping.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.rev.musicbrainz.client.mapping.serdes.deserializers.GenderDeserializer;
import com.github.rev.musicbrainz.client.mapping.serdes.serializers.GenderSerializer;
import org.musicbrainz.ns.mmd_2.Gender;

/**
 * Jackson Module implementation for handling serialisastion and deserialisation of entities returned by the
 * MusicBrainz API. In particular, lists are often formatted "badly" and extra care must be taken to handle them.
 */
public final class MbSerdesModule extends Module {

    @Override
    public String getModuleName() {
        return "MB_SERDES";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addDeserializers(getDeserializers());
        context.addSerializers(getSerializers());
    }

    private Deserializers getDeserializers() {
        SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
//        simpleDeserializers.addDeserializer(IsniList.class, new IsniListDeserializer());
//        simpleDeserializers.addDeserializer(IpiList.class, new IpiListDeserializer());
//        simpleDeserializers.addDeserializer(TagList.class, new TagListDeserializer());
//        simpleDeserializers.addDeserializer(AliasList.class, new AliasListDeserializer());
        simpleDeserializers.addDeserializer(Gender.class, new GenderDeserializer());
        return simpleDeserializers;
    }

    private Serializers getSerializers() {
        SimpleSerializers simpleSerializers = new SimpleSerializers();
//        simpleSerializers.addSerializer(Number.class, new ToStringSerializer());
        simpleSerializers.addSerializer(Gender.class, new GenderSerializer());
//        simpleSerializers.addSerializer(IsniList.class, new IsniListSerializer());
//        simpleSerializers.addSerializer(IpiList.class, new IpiListSerializer());
//        simpleSerializers.addSerializer(AliasList.class, new AliasListSerializer());
        return simpleSerializers;
    }
}
