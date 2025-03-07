package com.github.rev.musicbrainz.client.mapping.fmt.json.serdes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.FieldsToObjectHandler;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MbDeserializer;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MismatchFieldNameHandler;
import com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes.deserializers.MissingFieldNameHandler;
import com.github.rev.musicbrainz.client.search.result.MbArtistResult;
import org.apache.commons.lang3.tuple.Pair;
import org.musicbrainz.ns.mmd_2.Alias;
import org.musicbrainz.ns.mmd_2.AliasList;
import org.musicbrainz.ns.mmd_2.Artist;
import org.musicbrainz.ns.mmd_2.Gender;
import org.musicbrainz.ns.mmd_2.TagList;

import java.util.List;
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
        deserializers.addDeserializer(AliasList.class, new JsonListDeserializer<>(AliasList.class));
        deserializers.addDeserializer(TagList.class, new JsonListDeserializer<>(TagList.class));
        deserializers.addDeserializer(Alias.class, MbDeserializer.factory(Alias.class,
                MissingFieldNameHandler.factory(Alias.class, "name")));
        deserializers.addDeserializer(Artist.class, MbDeserializer.factory(Artist.class,
                List.of(
                        new JsonListDeserializer<>(AliasList.class),
                        MbDeserializer.factory(Alias.class, MissingFieldNameHandler.factory(Alias.class, "name")),
                        new JsonListDeserializer<>(TagList.class)
                ),
                FieldsToObjectHandler.factory(
                        Artist.class, "setGender", Gender.class,
                        Map.of(
                                "gender-id", Pair.of("setId", String.class),
                                "gender", Pair.of("setContent", String.class)
                        )),
                MismatchFieldNameHandler.factory(Artist.class, "aliases", "setAliasList", AliasList.class),
                MismatchFieldNameHandler.factory(Artist.class, "tags", "setTagList", TagList.class),
                MissingFieldNameHandler.factory(Artist.class, "score"),
                MissingFieldNameHandler.factory(Artist.class, "isnis"),
                MissingFieldNameHandler.factory(Artist.class, "ipis")
                )
        );
        return deserializers;
    }
}
