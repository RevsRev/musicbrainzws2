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
import com.github.rev.musicbrainz.client.search.result.MbInstrumentResult;
import com.github.rev.musicbrainz.client.search.result.MbLabelResult;
import com.github.rev.musicbrainz.client.search.result.MbPlaceResult;
import com.github.rev.musicbrainz.client.search.result.MbRecordingResult;
import com.github.rev.musicbrainz.client.search.result.MbReleaseResult;
import org.apache.commons.lang3.tuple.Pair;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;
import org.musicbrainz.ns.mmd_2.Annotation;
import org.musicbrainz.ns.mmd_2.AnnotationList;
import org.musicbrainz.ns.mmd_2.AreaList;
import org.musicbrainz.ns.mmd_2.Artist;
import org.musicbrainz.ns.mmd_2.ArtistCredit;
import org.musicbrainz.ns.mmd_2.ArtistList;
import org.musicbrainz.ns.mmd_2.Cdstub;
import org.musicbrainz.ns.mmd_2.CdstubList;
import org.musicbrainz.ns.mmd_2.DefAreaElementInner;
import org.musicbrainz.ns.mmd_2.Event;
import org.musicbrainz.ns.mmd_2.EventList;
import org.musicbrainz.ns.mmd_2.Format;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.Instrument;
import org.musicbrainz.ns.mmd_2.InstrumentList;
import org.musicbrainz.ns.mmd_2.Label;
import org.musicbrainz.ns.mmd_2.LabelInfoList;
import org.musicbrainz.ns.mmd_2.LabelList;
import org.musicbrainz.ns.mmd_2.Medium;
import org.musicbrainz.ns.mmd_2.MediumList;
import org.musicbrainz.ns.mmd_2.Packaging;
import org.musicbrainz.ns.mmd_2.Place;
import org.musicbrainz.ns.mmd_2.PlaceList;
import org.musicbrainz.ns.mmd_2.PrimaryType;
import org.musicbrainz.ns.mmd_2.Recording;
import org.musicbrainz.ns.mmd_2.RecordingList;
import org.musicbrainz.ns.mmd_2.Relation;
import org.musicbrainz.ns.mmd_2.RelationList;
import org.musicbrainz.ns.mmd_2.Release;
import org.musicbrainz.ns.mmd_2.ReleaseEventList;
import org.musicbrainz.ns.mmd_2.ReleaseGroup;
import org.musicbrainz.ns.mmd_2.ReleaseList;
import org.musicbrainz.ns.mmd_2.Status;
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

    @SuppressWarnings("checkstyle.MethodLength")
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
                        .withNestedDeserializer(
                                new MbDeserializer.Builder<>(Alias.class)
                                        .ignoringField("name")
                        )
                        .withArrayHandler("aliases", "alias", "setAliasList", AliasList.class)
                        .withArrayHandler("tags", "tag", "setTagList", TagList.class)
                        .withFieldsToObjectHandler("setGender", Gender.class,
                                Map.of(
                                        "gender-id", Pair.of("setId", String.class),
                                        "gender", Pair.of("setContent", String.class)
                                )
                        )
                        .ignoringField("score")
                        .withMappedKey("isnis", "isniList")
                        .withMappedKey("ipis", "ipiList")
                )
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
                                .withNestedDeserializer(
                                        new MbDeserializer.Builder<>(RelationList.class)
                                                .withNestedDeserializer(
                                                        new MbDeserializer.Builder<>(Relation.class)
                                                                .ignoringField("direction")
                                                )
                                                .withMappedKey("relations", "relation")
                                )
                                .withArrayHandler("relations", "relations", "setRelationList", RelationList.class)
                                .ignoringField("score")
                )
                .withMappedKey("events", "event")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbInstrumentResult.class,
                new JsonMbResultDeserializer<>(MbInstrumentResult.class));
        new MbDeserializer.Builder<>(InstrumentList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Instrument.class)
                                .withNestedDeserializer(
                                        new MbDeserializer.Builder<>(Alias.class)
                                                .ignoringField("name")
                                )
                                .ignoringField("score")
                                .withArrayHandler("aliases", "alias", "setAliasList", AliasList.class)
                                .withArrayHandler("tags", "tag", "setTagList", TagList.class)
                )
                .withMappedKey("instruments", "instrument")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbLabelResult.class, new JsonMbResultDeserializer<>(MbLabelResult.class));
        new MbDeserializer.Builder<>(LabelList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Label.class)
                                .withNestedDeserializer(
                                        new MbDeserializer.Builder<>(Alias.class)
                                                .ignoringField("name")
                                )
                                .ignoringField("score")
                                .withArrayHandler("aliases", "alias", "setAliasList", AliasList.class)
                                .withArrayHandler("tags", "tag", "setTagList", TagList.class)
                                .withMappedKey("isnis", "isniList")
                                .withMappedKey("ipis", "ipiList")
                )
                .withMappedKey("labels", "label")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbPlaceResult.class, new JsonMbResultDeserializer<>(MbPlaceResult.class));
        new MbDeserializer.Builder<>(PlaceList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Place.class)
                                .withNestedDeserializer(
                                        new MbDeserializer.Builder<>(Alias.class)
                                                .ignoringField("name")
                                )
                                .ignoringField("score")
                                .withArrayHandler("aliases", "alias", "setAliasList", AliasList.class)
                                .ignoringField("score")
                )
                .withMappedKey("places", "place")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbRecordingResult.class, new JsonMbResultDeserializer<>(MbRecordingResult.class));
        new MbDeserializer.Builder<>(RecordingList.class)
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Recording.class)
                                .withNestedDeserializer(new MbDeserializer.Builder<>(Artist.class)
                                        .withNestedDeserializer(new MbDeserializer.Builder<>(Alias.class)
                                                .ignoringField("name")
                                        )
                                        .withArrayHandler("aliases", "alias", "setAliasList", AliasList.class)
                                )
                                .withNestedDeserializer(new MbDeserializer.Builder<>(Release.class)
                                        .withNestedDeserializer(new MbDeserializer.Builder<>(ReleaseGroup.class)
                                                .withFieldsToObjectHandler("setPrimaryType", PrimaryType.class, Map.of(
                                                        "primary-type-id", Pair.of("setId", String.class),
                                                        "primary-type", Pair.of("setContent", String.class))
                                                )
                                                .ignoringField("secondary-types") //TODO - Fix me
                                                .ignoringField("secondary-type-ids") //TODO - Fix me
                                        )
                                        .withNestedDeserializer(new MbDeserializer.Builder<>(DefAreaElementInner.class)
                                                .ignoringField("iso-3166-1-codes") //TODO - Fix me
                                        )
                                        .withNestedDeserializer(new MbDeserializer.Builder<>(Medium.class)
                                                .withFieldsToObjectHandler("setFormat", Format.class, Map.of(
                                                        "format", Pair.of("setContent", String.class)
                                                ))
                                                .withArrayHandler("track", "def-track", "setTrackList",
                                                        Medium.TrackList.class)
                                                .ignoringField("track-count") //TODO - Fix me
                                                .ignoringField("track-offset") //TODO - Fix me
                                        )
                                        .withFieldsToObjectHandler("setStatus", Status.class,
                                                Map.of(
                                                        "status-id", Pair.of("setId", String.class),
                                                        "status", Pair.of("setContent", String.class)
                                                ))
                                        .ignoringField("count")
                                        .ignoringField("track-count")
                                        .withArrayHandler("release-events",
                                                "release-event",
                                                "setReleaseEventList",
                                                ReleaseEventList.class)
                                        .withArrayHandler("artist-credit",
                                                "name-credit",
                                                "setArtistCredit",
                                                ArtistCredit.class)
                                        .withArrayHandler("media", "medium", "setMediumList", MediumList.class)
                                )
                                .ignoringField("isrcs") //TODO - Fix me
                                .ignoringField("score")
                                .withArrayHandler("releases", "release", "setReleaseList", ReleaseList.class)
                                .withArrayHandler("artist-credit", "name-credit", "setArtistCredit", ArtistCredit.class)
                                .withArrayHandler("tags", "tag", "setTagList", TagList.class)
                )
                .withMappedKey("recordings", "recording")
                .build()
                .addToDeserializers(deserializers);

        deserializers.addDeserializer(MbReleaseResult.class, new JsonMbResultDeserializer<>(MbReleaseResult.class));
        new MbDeserializer.Builder<>(ReleaseList.class)
                .withNestedDeserializer(new MbDeserializer.Builder<>(Release.class)
                        .withNestedDeserializer(new MbDeserializer.Builder<>(ReleaseGroup.class)
                                .withFieldsToObjectHandler("setPrimaryType", PrimaryType.class, Map.of(
                                        "primary-type-id", Pair.of("setId", String.class),
                                        "primary-type", Pair.of("setContent", String.class))
                                )
                                .ignoringField("secondary-types") //TODO - Fix me
                                .ignoringField("secondary-type-ids") //TODO - Fix me
                        )
                        .withNestedDeserializer(new MbDeserializer.Builder<>(DefAreaElementInner.class)
                                .ignoringField("iso-3166-1-codes") //TODO - Fix me
                        )
                        .withNestedDeserializer(new MbDeserializer.Builder<>(Medium.class)
                                .withFieldsToObjectHandler("setFormat", Format.class, Map.of(
                                        "format", Pair.of("setContent", String.class)
                                ))
                                .withArrayHandler("track", "def-track", "setTrackList",
                                        Medium.TrackList.class)
                                .ignoringField("disc-count") //TODO - Fix me
                                .ignoringField("track-count") //TODO - Fix me
                        )
                        .withFieldsToObjectHandler("setStatus", Status.class,
                                Map.of(
                                        "status-id", Pair.of("setId", String.class),
                                        "status", Pair.of("setContent", String.class)
                                ))
                        .ignoringField("count")
                        .ignoringField("score")
                        .ignoringField("track-count")
                        .withArrayHandler("release-events",
                                "release-event",
                                "setReleaseEventList",
                                ReleaseEventList.class)
                        .withArrayHandler("artist-credit", "name-credit", "setArtistCredit", ArtistCredit.class)
                        .withArrayHandler("tags", "tag", "setTagList", TagList.class)
                        .withArrayHandler("media", "medium", "setMediumList", MediumList.class)
                        .withArrayHandler("label-info", "label-info", "setLabelInfoList", LabelInfoList.class)
                        .withFieldsToObjectHandler("setPackaging", Packaging.class, Map.of(
                                "packaging-id", Pair.of("setId", String.class),
                                "packaging", Pair.of("setContent", String.class)
                        ))
                )
                .withMappedKey("releases", "release")
                .build()
                .addToDeserializers(deserializers);

        return deserializers;
    }
}
