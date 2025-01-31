package com.github.rev.musicbrainz.client.artist;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.rev.musicbrainz.client.mapping.MbJsonMapper;
import com.github.rev.musicbrainz.client.mapping.AbstractMapperTest;
import org.junit.jupiter.api.Test;

public class ArtistMapperTest extends AbstractMapperTest {

    @Test
    public void testMapToObject() {
        JsonNode jsonNode = loadJson("example-data/artist_result.json");
        MbArtistResult parse = MbJsonMapper.ARTIST_PARSER.parse(jsonNode);
    }

}
