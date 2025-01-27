package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbEntity;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.query.MbArtistQuery;
import com.github.rev.musicbrainz.client.search.query.MbQuery;
import org.junit.jupiter.api.Test;

public class MbControllerTest {

    @Test
    public void testWithLiveEndpoint() throws MbQuery.InvalidQueryFieldException, MbBuilder.MbBuildException {
        MbClient client = new MbClient();
        MbController controller = MbController.factory(client);

        MbArtistQuery artistQuery = new MbArtistQuery();
        artistQuery.add(MbArtistQuery.ARTIST, "Fleetwood");

        MbSearchRequest.Builder<MbEntity.MbArtist> builder = new MbSearchRequest.Builder<>();
        builder.setEntity(new MbEntity.MbArtist());
        builder.setQuery(artistQuery);
        MbSearchRequest<MbEntity.MbArtist> searchRequest = builder.build();

        controller.getArtist().doSearch(searchRequest);
    }

}
