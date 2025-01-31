package com.github.rev.musicbrainz.client.artist;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.rev.musicbrainz.client.parse.MbJsonParser;
import com.github.rev.musicbrainz.client.parser.AbstractParserTest;
import org.junit.jupiter.api.Test;

public class ArtistParserTest extends AbstractParserTest {

    @Test
    public void testParse() {
        JsonNode jsonNode = loadJson("example-data/artist_result.json");
        MbArtistResult parse = MbJsonParser.ARTIST_PARSER.parse(jsonNode);
    }

}
