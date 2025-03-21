package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.MbClient;
import com.github.rev.musicbrainz.client.http.ThrottleStrategyImpl;
import com.github.rev.musicbrainz.client.search.MbSearchRequest;
import com.github.rev.musicbrainz.client.search.result.MbEventResult;
import com.github.rev.musicbrainz.client.util.ControllerTestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MbControllerSearchTest {

    private static final int THROTTLE_MILLIS = 1000;
    private static final MbController CONTROLLER = getController();
    private static final ControllerTestData TEST_DATA = ControllerTestData.constructAllTestData();

    @ParameterizedTest
    @MethodSource("getAnnotationTestsParams")
    public void testAnnotationTests(MbSearchRequest<MbEntity.MbAnnotation> testParam) {
        CONTROLLER.getAnnotation().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbAnnotation>> getAnnotationTestsParams() {
        return TEST_DATA.getAnnotationTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getAreaTestsParams")
    public void testAreaTests(MbSearchRequest<MbEntity.MbArea> testParam) {
        CONTROLLER.getArea().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbArea>> getAreaTestsParams() {
        return TEST_DATA.getAreaTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getArtistTestsParams")
    public void testArtistTests(MbSearchRequest<MbEntity.MbArtist> testParam) {
        CONTROLLER.getArtist().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbArtist>> getArtistTestsParams() {
        return TEST_DATA.getArtistTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getStubTestsParams")
    public void testStubTests(MbSearchRequest<MbEntity.CdStub> testParam) {
        CONTROLLER.getCdStub().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.CdStub>> getStubTestsParams() {
        return TEST_DATA.getStubTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getEventTestsParams")
    public void testEventTests(MbSearchRequest<MbEntity.MbEvent> testParam) {
        CONTROLLER.getEvent().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbEvent>> getEventTestsParams() {
        return TEST_DATA.getEventTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getInstrumentTestsParams")
    public void testInstrumentTests(MbSearchRequest<MbEntity.MbInstrument> testParam) {
        CONTROLLER.getInstrument().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbInstrument>> getInstrumentTestsParams() {
        return TEST_DATA.getInstrumentTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getLabelTestsParams")
    public void testLabelTests(MbSearchRequest<MbEntity.MbLabel> testParam) {
        CONTROLLER.getLabel().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbLabel>> getLabelTestsParams() {
        return TEST_DATA.getLabelTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getPlaceTestsParams")
    public void testPlaceTests(MbSearchRequest<MbEntity.MbPlace> testParam) {
        CONTROLLER.getPlace().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbPlace>> getPlaceTestsParams() {
        return TEST_DATA.getPlaceTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getRecordingTestsParams")
    public void testRecordingTests(MbSearchRequest<MbEntity.MbRecording> testParam) {
        CONTROLLER.getRecording().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbRecording>> getRecordingTestsParams() {
        return TEST_DATA.getRecordingTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getReleaseTestsParams")
    public void testReleaseTests(MbSearchRequest<MbEntity.MbRelease> testParam) {
        CONTROLLER.getRelease().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbRelease>> getReleaseTestsParams() {
        return TEST_DATA.getReleaseTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getReleaseGroupTestsParams")
    public void testReleaseGroupTests(MbSearchRequest<MbEntity.MbReleaseGroup> testParam) {
        CONTROLLER.getReleaseGroup().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbReleaseGroup>> getReleaseGroupTestsParams() {
        return TEST_DATA.getReleaseGroupTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getSeriesTestsParams")
    public void testSeriesTests(MbSearchRequest<MbEntity.MbSeries> testParam) {
        CONTROLLER.getSeries().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbSeries>> getSeriesTestsParams() {
        return TEST_DATA.getSeriesTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getTagTestsParams")
    public void testTagTests(MbSearchRequest<MbEntity.MbTag> testParam) {
        CONTROLLER.getTag().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbTag>> getTagTestsParams() {
        return TEST_DATA.getTagTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getWorkTestsParams")
    public void testWorkTests(MbSearchRequest<MbEntity.MbWork> testParam) {
        CONTROLLER.getWork().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbWork>> getWorkTestsParams() {
        return TEST_DATA.getWorkTests().getAllSearchRequests();
    }

    @ParameterizedTest
    @MethodSource("getUrlTestsParams")
    public void testUrlTests(MbSearchRequest<MbEntity.MbUrl> testParam) {
        CONTROLLER.getUrl().doSearch(testParam);
    }
    public static Collection<MbSearchRequest<MbEntity.MbUrl>> getUrlTestsParams() {
        return TEST_DATA.getUrlTests().getAllSearchRequests();
    }


    private static MbController getController() {
        MbClient client = new MbClient(new ThrottleStrategyImpl(THROTTLE_MILLIS));

//        Uncomment or set appropriate property to generate sources :)
//        System.setProperty("source-location", "/home/eddie/Documents/Projects/musicbrainzws2-java-parent/musicbrainz-api-client/src/test/resources/example_data/search");

        String sourceLocation = System.getProperty("source-location");
        final HandlerFactory handlerFactory;
        if (sourceLocation != null) {
            Set<Class<?>> classesToGenerateSourcesFor = new HashSet<>();
            //TODO - Could extract to a property?
//            classesToGenerateSourcesFor.add(MbEventResult.class);
            handlerFactory = new GenerateSourcesHandlerFactory(sourceLocation, classesToGenerateSourcesFor);
        } else {
            handlerFactory = new HandlerFactory.DefaultHandlerFactory();
        }

        return MbController.factory(client, handlerFactory);
    }

}
