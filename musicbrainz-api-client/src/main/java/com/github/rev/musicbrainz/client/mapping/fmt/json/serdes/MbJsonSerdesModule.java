package com.github.rev.musicbrainz.client.mapping.fmt.json.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MbDeserializer;
import com.github.rev.musicbrainz.client.search.result.MbArtistResult;
import org.apache.commons.lang3.tuple.Pair;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;
import org.musicbrainz.ns.mmd_2.Artist;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.TagList;

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
        deserializers.addDeserializer(MbArtistResult.class, new JsonMbResultDeserializer<>(MbArtistResult.class));

        MbDeserializer<Artist> artistDeserializer = new MbDeserializer.Builder<>(Artist.class)
                .withNestedDeserializer(new JsonListDeserializer<>(AliasList.class))
                .withNestedDeserializer(new JsonListDeserializer<>(TagList.class))
                .withNestedDeserializer(
                        new MbDeserializer.Builder<>(Alias.class)
                                .ignoringField("name")
                )
                .withMappedKey("aliases", "setAliasList", AliasList.class)
                .withMappedKey("tags", "setTagList", TagList.class)
                .withFieldsToObjectHandler("setGender", Gender.class,
                        Map.of(
                                "gender-id", Pair.of("setId", String.class),
                                "gender", Pair.of("setContent", String.class)
                        )
                )
                .ignoringField("score")
                .ignoringField("isnis")
                .ignoringField("ipis")
                .build();

        artistDeserializer.addToDeserializers(deserializers);
        return deserializers;
    }
}
