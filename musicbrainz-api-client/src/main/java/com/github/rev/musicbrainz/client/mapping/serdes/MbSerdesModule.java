package com.github.rev.musicbrainz.client.mapping.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.rev.musicbrainz.client.mapping.serdes.deserializers.BadKeyDeserializer;
import com.github.rev.musicbrainz.client.mapping.serdes.deserializers.FormatDeserializer;
import com.github.rev.musicbrainz.client.mapping.serdes.deserializers.LanguageDeserializer;
import com.github.rev.musicbrainz.client.mapping.serdes.deserializers.TargetDeserializer;
import com.github.rev.musicbrainz.client.mapping.serdes.handler.MbValueInstantiators;
import com.github.rev.musicbrainz.client.mapping.serdes.serializers.GenderSerializer;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.Format;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.LanguageList;
import org.musicbrainz.ns.mmd_2.PrimaryType;
import org.musicbrainz.ns.mmd_2.SecondaryType;
import org.musicbrainz.ns.mmd_2.Status;
import org.musicbrainz.ns.mmd_2.Target;

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
        context.addValueInstantiators(new MbValueInstantiators());
    }

    private Deserializers getDeserializers() {
        SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
        simpleDeserializers.addDeserializer(Target.class, new TargetDeserializer());
        simpleDeserializers.addDeserializer(LanguageList.Language.class, new LanguageDeserializer());
        simpleDeserializers.addDeserializer(Format.class, new FormatDeserializer());

        simpleDeserializers.addDeserializer(Gender.class, BadKeyDeserializer.factory(
                Gender.class,
                "",
                "setContent"
        ));

        simpleDeserializers.addDeserializer(Alias.class, BadKeyDeserializer.factory(
                Alias.class,
                "",
                "setContent"));

        simpleDeserializers.addDeserializer(Status.class, BadKeyDeserializer.factory(
                Status.class,
                "",
                "setContent"
        ));

        simpleDeserializers.addDeserializer(PrimaryType.class, BadKeyDeserializer.factory(
                PrimaryType.class,
                "",
                "setContent"
        ));

        simpleDeserializers.addDeserializer(SecondaryType.class, BadKeyDeserializer.factory(
                SecondaryType.class,
                "secondary-type",
                "setContent"
        ));

        return simpleDeserializers;
    }

    private Serializers getSerializers() {
        SimpleSerializers simpleSerializers = new SimpleSerializers();
        simpleSerializers.addSerializer(Gender.class, new GenderSerializer());
        return simpleSerializers;
    }
}
