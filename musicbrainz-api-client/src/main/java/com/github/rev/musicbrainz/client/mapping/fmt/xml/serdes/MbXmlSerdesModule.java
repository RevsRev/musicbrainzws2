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
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MismatchFieldNameHandler;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MissingFieldNameHandler;
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

import java.util.List;

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

        simpleDeserializers.addDeserializer(Gender.class, MbDeserializer.factory(Gender.class,
                MismatchFieldNameHandler.factoryString(
                Gender.class,
                "",
                "setContent")
        ));

        simpleDeserializers.addDeserializer(Alias.class, MbDeserializer.factory(Alias.class,
                MismatchFieldNameHandler.factoryString(
                Alias.class,
                "",
                "setContent")
        ));

        simpleDeserializers.addDeserializer(Status.class, MbDeserializer.factory(Status.class,
                MismatchFieldNameHandler.factoryString(
                Status.class,
                "",
                "setContent")
        ));

//        simpleDeserializers.addDeserializer(PrimaryType.class, MbDeserializer.factory(PrimaryType.class,
//                MismatchFieldNameHandler.factoryString(
//                PrimaryType.class,
//                "",
//                "setContent")
//        ));

        //TODO - This needs fixing
        simpleDeserializers.addDeserializer(SecondaryType.class, MbDeserializer.factory(SecondaryType.class,
                MismatchFieldNameHandler.factoryString(
                SecondaryType.class,
                "secondary-type",
                "setContent")
        ));
        simpleDeserializers.addDeserializer(ReleaseGroup.class, MbDeserializer.factory(ReleaseGroup.class,
                List.of(
                        MbDeserializer.factory(PrimaryType.class,
                                MismatchFieldNameHandler.factoryString(
                                        PrimaryType.class,
                                        "",
                                        "setContent")
                        ),
                        MbDeserializer.factory(Alias.class,
                                MismatchFieldNameHandler.factoryString(
                                        Alias.class,
                                        "",
                                        "setContent")
                        ),
                        MbDeserializer.factory(Status.class,
                                MismatchFieldNameHandler.factoryString(
                                        Status.class,
                                        "",
                                        "setContent")
                        )/*,
                        MbDeserializer.factory(SecondaryType.class,
                                MismatchFieldNameHandler.factoryString(
                                        SecondaryType.class,
                                        "secondary-type",
                                        "setContent")
                        )*/),
                MissingFieldNameHandler.factory(ReleaseGroup.class, "score"),
                MissingFieldNameHandler.factory(ReleaseGroup.class, "secondary-type-list") //TODO - Fix me
        ));

        simpleDeserializers.addDeserializer(Packaging.class, MbDeserializer.factory(Packaging.class,
                MismatchFieldNameHandler.factoryString(Packaging.class, "", "setContent")));

        return simpleDeserializers;
    }

    private Serializers getSerializers() {
        return new SimpleSerializers();
    }
}
