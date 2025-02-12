package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.query.MbArtistQuery;
import com.github.rev.musicbrainz.client.search.query.MbQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class MbControllerTest {

    private static final MbClient CLIENT = new MbClient();
    private static final MbController CONTROLLER = MbController.factory(CLIENT);

    @ParameterizedTest
    @MethodSource("getEndpointParams")
    public <T extends MbEntity, R extends MbResult<T>> void testWithLiveEndpoint(final EndpointParams<T,R> params)
            throws MbQuery.InvalidQueryFieldException, MbBuilder.MbBuildException {

        MbSearchRequest.Builder<T> builder = new MbSearchRequest.Builder<>();
        builder.setEntity(params.entity);
        builder.setQuery(params.query);
        MbSearchRequest<T> searchRequest = builder.build();
        params.controller.doSearch(searchRequest);
    }

    public static List<EndpointParams<?,?>> getEndpointParams() {
        List<EndpointParams<?,?>> params = new ArrayList<>();

        try {
            params.add(new EndpointParams<>(new MbEntity.MbArtist(), artistQuery(), CONTROLLER.getArtist()));
        } catch (MbQuery.InvalidQueryFieldException e) {
            Assertions.fail(e);
        }
        return params;
    }

    private static MbQuery<MbEntity.MbArtist> artistQuery() throws MbQuery.InvalidQueryFieldException {
        MbArtistQuery artistQuery = new MbArtistQuery();
        artistQuery.add(MbArtistQuery.ARTIST, "Fleetwood");
        return artistQuery;
    }

    public static final class EndpointParams<T extends MbEntity, R extends MbResult<T>> {
        private final T entity;
        private final MbQuery<T> query;
        private final MbEntityController<T,R> controller;

        private EndpointParams(T entity, MbQuery<T> query, MbEntityController<T, R> controller) {
            this.entity = entity;
            this.query = query;
            this.controller = controller;
        }
    }

}
