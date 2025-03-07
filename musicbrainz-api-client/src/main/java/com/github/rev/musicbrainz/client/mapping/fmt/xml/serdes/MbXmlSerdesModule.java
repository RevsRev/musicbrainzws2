package com.github.rev.musicbrainz.client.mapping.fmt.xml.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.FormatDeserializer;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.LanguageDeserializer;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MbDeserializer;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.TargetDeserializer;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.Format;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.LanguageList;
import org.musicbrainz.ns.mmd_2.Packaging;
import org.musicbrainz.ns.mmd_2.PrimaryType;
import org.musicbrainz.ns.mmd_2.ReleaseGroup;
import org.musicbrainz.ns.mmd_2.SecondaryType;
import org.musicbrainz.ns.mmd_2.Status;
import org.musicbrainz.ns.mmd_2.Target;

/**
 * Jackson Module implementation for handling serialisastion and deserialisation of entities returned by the
 * MusicBrainz API. In particular, lists are often formatted "badly" and extra care must be taken to handle them.
 */
public final class MbXmlSerdesModule extends Module {

    @Override
    public String getModuleName() {
        return "MB_SHARED_SERDES";
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
        simpleDeserializers.addDeserializer(Target.class, new TargetDeserializer());
        simpleDeserializers.addDeserializer(LanguageList.Language.class, new LanguageDeserializer());
        simpleDeserializers.addDeserializer(Format.class, new FormatDeserializer());

        new MbDeserializer.Builder<>(Gender.class)
                .withMappedKey("", "setContent")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Alias.class)
                .withMappedKey("", "setContent")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Status.class)
                .withMappedKey("", "setContent")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(SecondaryType.class)
                .withMappedKey("secondary-type", "setContent")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(ReleaseGroup.class)
                .ignoringField("score")
                .ignoringField("secondary-type-list") //TODO - Fix me
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(PrimaryType.class)
                                .withMappedKey("", "setContent")
                )
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Alias.class)
                                .withMappedKey("", "setContent")
                )
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Status.class)
                                .withMappedKey("", "setContent")
                )
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Packaging.class)
                .withMappedKey("", "setContent")
                .build()
                .addToDeserializers(simpleDeserializers);

        return simpleDeserializers;
    }

    private Serializers getSerializers() {
        return new SimpleSerializers();
    }
}
