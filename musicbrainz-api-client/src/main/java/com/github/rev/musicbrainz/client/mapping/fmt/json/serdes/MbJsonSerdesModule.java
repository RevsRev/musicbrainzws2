package com.github.rev.musicbrainz.client.mapping.fmt.json.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MbDeserializer;
import com.github.rev.musicbrainz.client.search.result.MbAnnotationResult;
import com.github.rev.musicbrainz.client.search.result.MbAreaResult;
import com.github.rev.musicbrainz.client.search.result.MbArtistResult;
import com.github.rev.musicbrainz.client.search.result.MbCdStubResult;
import com.github.rev.musicbrainz.client.search.result.MbEventResult;
import org.apache.commons.lang3.tuple.Pair;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;
import org.musicbrainz.ns.mmd_2.Annotation;
import org.musicbrainz.ns.mmd_2.AnnotationList;
import org.musicbrainz.ns.mmd_2.AreaList;
import org.musicbrainz.ns.mmd_2.Artist;
import org.musicbrainz.ns.mmd_2.ArtistList;
import org.musicbrainz.ns.mmd_2.Cdstub;
import org.musicbrainz.ns.mmd_2.CdstubList;
import org.musicbrainz.ns.mmd_2.DefAreaElementInner;
import org.musicbrainz.ns.mmd_2.Event;
import org.musicbrainz.ns.mmd_2.EventList;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.Relation;
import org.musicbrainz.ns.mmd_2.RelationList;
import org.musicbrainz.ns.mmd_2.TagList;
import org.musicbrainz.ns.mmd_2.Target;

import java.util.Map;

/**
 * Module for MusicBrainz entities specific to JSON.
 */
public final class MbJsonSerdesModule extends Module {

    @Override
    public String getModuleName() {
        return "MB_JSON_SERDES";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addDeserializers(getDeserializers());
    }

    private Deserializers getDeserializers() {
        SimpleDeserializers deserializers = new SimpleDeserializers();

        deserializers.addDeserializer(MbAnnotationResult.class,
                new JsonMbResultDeserializer<>(MbAnnotationResult.class));
        new MbDeserializer.Builder<>(AnnotationList.class)
                .withMappedKey("annotations", "annotation")
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Annotation.class)
                                .ignoringField("score")
                )
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbAreaResult.class, new JsonMbResultDeserializer<>(MbAreaResult.class));
        new MbDeserializer.Builder<>(AreaList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(DefAreaElementInner.class)
                                .withNestedDeserializer(new MbDeserializer.Builder<>(RelationList.class)
                                        .withNestedDeserializer(new MbDeserializer.Builder<>(Relation.class)
                                                .ignoringField("direction") //TODO - Fix me!
                                                .withFieldsToObjectHandler("setTarget", Target.class,
                                                        Map.of("target", Pair.of("setId", String.class))))
                                        .withMappedKey("relations", "relation"))
                                .ignoringField("score")
                )
                .withMappedKey("areas", "area")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbArtistResult.class, new JsonMbResultDeserializer<>(MbArtistResult.class));
        new MbDeserializer.Builder<>(ArtistList.class)
                .withMappedKey("artists", "artist")
                .withNestedDeserializer(new MbDeserializer.Builder<>(Artist.class)
                        .withNestedDeserializer(new JsonListDeserializer<>(AliasList.class))
                        .withNestedDeserializer(new JsonListDeserializer<>(TagList.class))
                        .withNestedDeserializer(
                                new MbDeserializer.Builder<>(Alias.class)
                                        .ignoringField("name")
                        )
                        .withMappedKey("aliases", "aliasList")
                        .withMappedKey("tags", "tagList")
                        .withFieldsToObjectHandler("setGender", Gender.class,
                                Map.of(
                                        "gender-id", Pair.of("setId", String.class),
                                        "gender", Pair.of("setContent", String.class)
                                )
                        )
                        .ignoringField("score")
                        .ignoringField("isnis")
                        .ignoringField("ipis"))
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbCdStubResult.class, new JsonMbResultDeserializer<>(MbCdStubResult.class));
        new MbDeserializer.Builder<>(CdstubList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Cdstub.class)
                                .ignoringField("score")
                                .ignoringField("count")
                )
                .withMappedKey("cdstubs", "cdstub")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbEventResult.class, new JsonMbResultDeserializer<>(MbEventResult.class));
        new MbDeserializer.Builder<>(EventList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Event.class)
//                                .withNestedDeserializer(new JsonListDeserializer<>(RelationList.class))
                                .withNestedDeserializer(
                                        new MbDeserializer.Builder<>(RelationList.class)
                                                .withNestedDeserializer(
                                                        new MbDeserializer.Builder<>(Relation.class)
                                                                .ignoringField("direction")
                                                )
                                                .withMappedKey("relations", "relation")
                                )
                                .withArrayHandler("relations", "setRelationList", RelationList.class)
                                .ignoringField("score")
                                .withMappedKey("relations", "relationList")
                )
                .withMappedKey("events", "event")
                .build()
                .addToDeserializers(deserializers);

        return deserializers;
    }
}
