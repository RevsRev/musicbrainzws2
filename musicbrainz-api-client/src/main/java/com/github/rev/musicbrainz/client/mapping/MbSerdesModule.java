package com.github.rev.musicbrainz.client.mapping;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.github.rev.musicbrainz.client.mapping.deserializers.AliasListDeserializer;
import com.github.rev.musicbrainz.client.mapping.deserializers.IpiiListDeserializer;
import com.github.rev.musicbrainz.client.mapping.deserializers.IsniListDeserializer;
import com.github.rev.musicbrainz.client.mapping.deserializers.TagListDeserializer;
import org.musicbrainz.ns.mmd_2.AliasList;
import org.musicbrainz.ns.mmd_2.IpiList;
import org.musicbrainz.ns.mmd_2.IsniList;
import org.musicbrainz.ns.mmd_2.TagList;

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
    }

    private Deserializers getDeserializers() {
        SimpleDeserializers simpleDeserializers = new SimpleDeserializers();
        simpleDeserializers.addDeserializer(IsniList.class, new IsniListDeserializer());
        simpleDeserializers.addDeserializer(IpiList.class, new IpiiListDeserializer());
        simpleDeserializers.addDeserializer(TagList.class, new TagListDeserializer());
        simpleDeserializers.addDeserializer(AliasList.class, new AliasListDeserializer());
        return simpleDeserializers;
    }
}
