package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.query.MbAnnotationQuery;
import com.github.rev.musicbrainz.client.search.query.MbAreaQuery;
import com.github.rev.musicbrainz.client.search.query.MbArtistQuery;
import com.github.rev.musicbrainz.client.search.query.MbCdStubQuery;
import com.github.rev.musicbrainz.client.search.query.MbEventQuery;
import com.github.rev.musicbrainz.client.search.query.MbInstrumentQuery;
import com.github.rev.musicbrainz.client.search.query.MbLabelQuery;
import com.github.rev.musicbrainz.client.search.query.MbPlaceQuery;
import com.github.rev.musicbrainz.client.search.query.MbQuery;
import com.github.rev.musicbrainz.client.search.query.MbRecordingQuery;
import com.github.rev.musicbrainz.client.search.query.MbReleaseGroupQuery;
import com.github.rev.musicbrainz.client.search.query.MbReleaseQuery;
import com.github.rev.musicbrainz.client.search.query.MbSeriesQuery;
import com.github.rev.musicbrainz.client.search.query.MbTagQuery;
import com.github.rev.musicbrainz.client.search.query.MbUrlQuery;
import com.github.rev.musicbrainz.client.search.query.MbWorkQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MbControllerTest {

    private static final MbController CONTROLLER = getController();

    @ParameterizedTest
    @MethodSource("getEndpointParams")
    public <T extends MbEntity, R extends MbResult<T>> void testXmlWithLiveEndpoint(final EndpointParams<T,R> params)
            throws MbQuery.InvalidQueryFieldException, MbBuilder.MbBuildException {
        testWithLiveEndpoint(params, MbFormat.XML);
    }

    @ParameterizedTest
    @MethodSource("getEndpointParams")
    public <T extends MbEntity, R extends MbResult<T>> void testJsonWithLiveEndpoint(final EndpointParams<T,R> params)
            throws MbQuery.InvalidQueryFieldException, MbBuilder.MbBuildException {
        //TODO - Fix me
//        testWithLiveEndpoint(params, MbFormat.JSON);
    }

    private static <T extends MbEntity, R extends MbResult<T>> void testWithLiveEndpoint(final EndpointParams<T, R> params,
                                                                                         final MbFormat format)
            throws MbBuilder.MbBuildException {
        MbSearchRequest.Builder<T> builder = new MbSearchRequest.Builder<>();
        builder.setEntity(params.entity);
        builder.setQuery(params.query);
        builder.setFormat(format);
        MbSearchRequest<T> searchRequest = builder.build();
        R r = params.controller.doSearch(searchRequest);
    }

    public static List<EndpointParams<?,?>> getEndpointParams() {
        List<EndpointParams<?,?>> params = new ArrayList<>();

        try {
            params.add(new EndpointParams<>(new MbEntity.MbAnnotation(), annotationQuery(), CONTROLLER.getAnnotation()));
            params.add(new EndpointParams<>(new MbEntity.MbArea(), areaQuery(), CONTROLLER.getArea()));
            params.add(new EndpointParams<>(new MbEntity.MbArtist(), artistQuery(), CONTROLLER.getArtist()));
            params.add(new EndpointParams<>(new MbEntity.CdStub(), stubQuery(), CONTROLLER.getCdStub()));
            params.add(new EndpointParams<>(new MbEntity.MbEvent(), eventQuery(), CONTROLLER.getEvent()));
//            params.add(new EndpointParams<>(new MbEntity.MbGenre(), genreQuery(), CONTROLLER.getGenre()));
            params.add(new EndpointParams<>(new MbEntity.MbInstrument(), instrumentQuery(), CONTROLLER.getInstrument()));
            params.add(new EndpointParams<>(new MbEntity.MbLabel(), labelQuery(), CONTROLLER.getLabel()));
            params.add(new EndpointParams<>(new MbEntity.MbPlace(), placeQuery(), CONTROLLER.getPlace()));
            params.add(new EndpointParams<>(new MbEntity.MbRecording(), recordingQuery(), CONTROLLER.getRecording()));
            params.add(new EndpointParams<>(new MbEntity.MbRelease(), releaseQuery(), CONTROLLER.getRelease()));
            params.add(new EndpointParams<>(new MbEntity.MbReleaseGroup(), releaseGroupQuery(), CONTROLLER.getReleaseGroup()));
            params.add(new EndpointParams<>(new MbEntity.MbSeries(), seriesQuery(), CONTROLLER.getSeries()));
            params.add(new EndpointParams<>(new MbEntity.MbTag(), tagQuery(), CONTROLLER.getTag()));
            params.add(new EndpointParams<>(new MbEntity.MbWork(), workQuery(), CONTROLLER.getWork()));
            params.add(new EndpointParams<>(new MbEntity.MbUrl(), urlQuery(), CONTROLLER.getUrl()));

        } catch (MbQuery.InvalidQueryFieldException e) {
            Assertions.fail(e);
        }
        return params;
    }

    private static MbQuery<MbEntity.MbAnnotation> annotationQuery() throws MbQuery.InvalidQueryFieldException {
        MbAnnotationQuery annotationQuery = new MbAnnotationQuery();
        annotationQuery.add(MbAnnotationQuery.TEXT, "Born");
        return annotationQuery;
    }
    private static MbQuery<MbEntity.MbArea> areaQuery() throws MbQuery.InvalidQueryFieldException {
        MbAreaQuery areaQuery = new MbAreaQuery();
        areaQuery.add(MbAreaQuery.AREA, "Rock");
        return areaQuery;
    }
    private static MbQuery<MbEntity.MbArtist> artistQuery() throws MbQuery.InvalidQueryFieldException {
        MbArtistQuery artistQuery = new MbArtistQuery();
        artistQuery.add(MbArtistQuery.ARTIST, "Fleetwood");
        return artistQuery;
    }
    private static MbQuery<MbEntity.CdStub> stubQuery() throws MbQuery.InvalidQueryFieldException {
        MbCdStubQuery stubQuery = new MbCdStubQuery();
        stubQuery.add(MbCdStubQuery.TITLE, "Doo");
        return stubQuery;
    }
    private static MbQuery<MbEntity.MbEvent> eventQuery() throws MbQuery.InvalidQueryFieldException {
        MbEventQuery eventQuery = new MbEventQuery();
        eventQuery.add(MbEventQuery.PLACE, "USA");
        return eventQuery;
    }

    //TODO - Check this one is unsupported?
//    private static MbQuery<MbEntity.MbGenre> genreQuery() throws MbQuery.InvalidQueryFieldException {
//        MbGenreQuery genreQuery = new MbGenreQuery();
//        genreQuery.add(MbGenreQuery);
//        return genreQuery;
//    }

    private static MbQuery<MbEntity.MbInstrument> instrumentQuery() throws MbQuery.InvalidQueryFieldException {
        MbInstrumentQuery instrumentQuery = new MbInstrumentQuery();
        instrumentQuery.add(MbInstrumentQuery.INSTRUMENT, "Guitar");
        return instrumentQuery;
    }
    private static MbQuery<MbEntity.MbLabel> labelQuery() throws MbQuery.InvalidQueryFieldException {
        MbLabelQuery labelQuery = new MbLabelQuery();
        labelQuery.add(MbLabelQuery.COUNTRY, "US");
        return labelQuery;
    }
    private static MbQuery<MbEntity.MbPlace> placeQuery() throws MbQuery.InvalidQueryFieldException {
        MbPlaceQuery placeQuery = new MbPlaceQuery();
        placeQuery.add(MbPlaceQuery.PLACE, "USA");
        return placeQuery;
    }
    private static MbQuery<MbEntity.MbRecording> recordingQuery() throws MbQuery.InvalidQueryFieldException {
        MbRecordingQuery recordingQuery = new MbRecordingQuery();
        recordingQuery.add(MbRecordingQuery.ARTIST, "Fleetwood Mac");
        return recordingQuery;
    }
    private static MbQuery<MbEntity.MbRelease> releaseQuery() throws MbQuery.InvalidQueryFieldException {
        MbReleaseQuery releaseQuery = new MbReleaseQuery();
        releaseQuery.add(MbReleaseQuery.ARTIST, "Fleetwood Mac");
        return releaseQuery;
    }
    private static MbQuery<MbEntity.MbReleaseGroup> releaseGroupQuery() throws MbQuery.InvalidQueryFieldException {
        MbReleaseGroupQuery releaseGroupQuery = new MbReleaseGroupQuery();
        releaseGroupQuery.add(MbReleaseGroupQuery.RELEASE, "Rumours");
        return releaseGroupQuery;
    }
    private static MbQuery<MbEntity.MbSeries> seriesQuery() throws MbQuery.InvalidQueryFieldException {
        MbSeriesQuery seriesQuery = new MbSeriesQuery();
        seriesQuery.add(MbSeriesQuery.SERIES, "roll");
        return seriesQuery;
    }
    private static MbQuery<MbEntity.MbTag> tagQuery() throws MbQuery.InvalidQueryFieldException {
        MbTagQuery tagQuery = new MbTagQuery();
        tagQuery.add(MbTagQuery.TAG, "tag");
        return tagQuery;
    }
    private static MbQuery<MbEntity.MbWork> workQuery() throws MbQuery.InvalidQueryFieldException {
        MbWorkQuery workQuery = new MbWorkQuery();
        workQuery.add(MbWorkQuery.ARTIST, "Fleetwood Mac");
        return workQuery;
    }
    private static MbQuery<MbEntity.MbUrl> urlQuery() throws MbQuery.InvalidQueryFieldException {
        MbUrlQuery urlQuery = new MbUrlQuery();
        urlQuery.add(MbUrlQuery.RELATION_TYPE, "wikidata");
        return urlQuery;
    }


    private static MbController getController() {
        MbClient client = new MbClient();

//        Uncomment or set appropriate property to generate sources :)
//        System.setProperty("source-location", "/home/eddie/Documents/Projects/musicbrainzws2-java-parent/musicbrainz-api-client/src/test/resources/example_data");

        String sourceLocation = System.getProperty("source-location");
        final HandlerFactory handlerFactory;
        if (sourceLocation != null) {
            Set<Class<?>> classesToGenerateSourcesFor = new HashSet<>();
            //TODO - Could extract to a property?
            //classesToGenerateSourcesFor.add(MbLabelResult.class);
            handlerFactory = new GenerateSourcesHandlerFactory(sourceLocation, classesToGenerateSourcesFor);
        } else {
            handlerFactory = new HandlerFactory.DefaultHandlerFactory();
        }

        return MbController.factory(client, handlerFactory);
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
