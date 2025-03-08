package com.github.rev.musicbrainz.client.mapping.fmt.xml.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.LanguageDeserializer;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MbDeserializer;
import org.apache.commons.lang3.tuple.Pair;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.Format;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.LanguageList;
import org.musicbrainz.ns.mmd_2.Medium;
import org.musicbrainz.ns.mmd_2.Packaging;
import org.musicbrainz.ns.mmd_2.PrimaryType;
import org.musicbrainz.ns.mmd_2.Relation;
import org.musicbrainz.ns.mmd_2.ReleaseGroup;
import org.musicbrainz.ns.mmd_2.SecondaryType;
import org.musicbrainz.ns.mmd_2.Status;
import org.musicbrainz.ns.mmd_2.Target;

import java.util.Map;

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

        //TODO - Replace with an appropriate MbDeserializer!
        simpleDeserializers.addDeserializer(LanguageList.Language.class, new LanguageDeserializer());

        new MbDeserializer.Builder<>(Relation.class)
                .withFieldsToObjectHandler("setTarget", Target.class, Map.of("target", Pair.of("setId", String.class)))
                .ignoringField("direction") //TODO - Fix me
                .ignoringField("attribute-list") //TODO - Fix me
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Gender.class)
                .withMappedKey("", "content")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Alias.class)
                .withMappedKey("", "content")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Status.class)
                .withMappedKey("", "content")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(SecondaryType.class)
                .withMappedKey("secondary-type", "content")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(ReleaseGroup.class)
                .ignoringField("score")
                .ignoringField("secondary-type-list") //TODO - Fix me
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(PrimaryType.class)
                                .withMappedKey("", "content")
                )
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Alias.class)
                                .withMappedKey("", "content")
                )
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Status.class)
                                .withMappedKey("", "content")
                )
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Packaging.class)
                .withMappedKey("", "content")
                .build()
                .addToDeserializers(simpleDeserializers);

        new MbDeserializer.Builder<>(Medium.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Medium.TrackList.class)
                                .withMappedKey("track", "defTrack")
                )
                .withFieldsToObjectHandler("setFormat", Format.class,
                        Map.of("format", Pair.of("setContent", String.class)))
                .build()
                .addToDeserializers(simpleDeserializers);

        return simpleDeserializers;
    }

    private Serializers getSerializers() {
        return new SimpleSerializers();
    }
}
