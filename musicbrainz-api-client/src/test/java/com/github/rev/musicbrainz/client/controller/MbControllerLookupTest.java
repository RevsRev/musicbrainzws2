package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.MbClient;
import com.github.rev.musicbrainz.client.http.ThrottleStrategyImpl;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.search.result.MbEventResult;
import com.github.rev.musicbrainz.client.util.ControllerTestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MbControllerLookupTest {

    private static final int THROTTLE_MILLIS = 1000;
    private static final MbController CONTROLLER = getController();
    private static final ControllerTestData TEST_DATA = ControllerTestData.constructAllTestData();

    @ParameterizedTest
    @MethodSource("getAnnotationTestsParams")
    public void testAnnotationTests(MbLookupRequest<MbEntity.MbAnnotation> testParam) {
        CONTROLLER.getAnnotation().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbAnnotation>> getAnnotationTestsParams() {
        return TEST_DATA.getAnnotationTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getAreaTestsParams")
    public void testAreaTests(MbLookupRequest<MbEntity.MbArea> testParam) {
        CONTROLLER.getArea().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbArea>> getAreaTestsParams() {
        return TEST_DATA.getAreaTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getArtistTestsParams")
    public void testArtistTests(MbLookupRequest<MbEntity.MbArtist> testParam) {
        CONTROLLER.getArtist().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbArtist>> getArtistTestsParams() {
        return TEST_DATA.getArtistTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getStubTestsParams")
    public void testStubTests(MbLookupRequest<MbEntity.CdStub> testParam) {
        CONTROLLER.getCdStub().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.CdStub>> getStubTestsParams() {
        return TEST_DATA.getStubTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getEventTestsParams")
    public void testEventTests(MbLookupRequest<MbEntity.MbEvent> testParam) {
        CONTROLLER.getEvent().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbEvent>> getEventTestsParams() {
        return TEST_DATA.getEventTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getInstrumentTestsParams")
    public void testInstrumentTests(MbLookupRequest<MbEntity.MbInstrument> testParam) {
        CONTROLLER.getInstrument().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbInstrument>> getInstrumentTestsParams() {
        return TEST_DATA.getInstrumentTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getLabelTestsParams")
    public void testLabelTests(MbLookupRequest<MbEntity.MbLabel> testParam) {
        CONTROLLER.getLabel().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbLabel>> getLabelTestsParams() {
        return TEST_DATA.getLabelTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getPlaceTestsParams")
    public void testPlaceTests(MbLookupRequest<MbEntity.MbPlace> testParam) {
        CONTROLLER.getPlace().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbPlace>> getPlaceTestsParams() {
        return TEST_DATA.getPlaceTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getRecordingTestsParams")
    public void testRecordingTests(MbLookupRequest<MbEntity.MbRecording> testParam) {
        CONTROLLER.getRecording().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbRecording>> getRecordingTestsParams() {
        return TEST_DATA.getRecordingTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getReleaseTestsParams")
    public void testReleaseTests(MbLookupRequest<MbEntity.MbRelease> testParam) {
        CONTROLLER.getRelease().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbRelease>> getReleaseTestsParams() {
        return TEST_DATA.getReleaseTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getReleaseGroupTestsParams")
    public void testReleaseGroupTests(MbLookupRequest<MbEntity.MbReleaseGroup> testParam) {
        CONTROLLER.getReleaseGroup().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbReleaseGroup>> getReleaseGroupTestsParams() {
        return TEST_DATA.getReleaseGroupTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getSeriesTestsParams")
    public void testSeriesTests(MbLookupRequest<MbEntity.MbSeries> testParam) {
        CONTROLLER.getSeries().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbSeries>> getSeriesTestsParams() {
        return TEST_DATA.getSeriesTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getTagTestsParams")
    public void testTagTests(MbLookupRequest<MbEntity.MbTag> testParam) {
        CONTROLLER.getTag().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbTag>> getTagTestsParams() {
        return TEST_DATA.getTagTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getWorkTestsParams")
    public void testWorkTests(MbLookupRequest<MbEntity.MbWork> testParam) {
        CONTROLLER.getWork().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbWork>> getWorkTestsParams() {
        return TEST_DATA.getWorkTests().getAllLookupRequests();
    }

    @ParameterizedTest
    @MethodSource("getUrlTestsParams")
    public void testUrlTests(MbLookupRequest<MbEntity.MbUrl> testParam) {
        CONTROLLER.getUrl().doLookup(testParam);
    }
    public static Collection<MbLookupRequest<MbEntity.MbUrl>> getUrlTestsParams() {
        return TEST_DATA.getUrlTests().getAllLookupRequests();
    }


    private static MbController getController() {
        MbClient client = new MbClient(new ThrottleStrategyImpl(THROTTLE_MILLIS));

//        Uncomment or set appropriate property to generate sources :)
        System.setProperty("source-location", "/home/eddie/Documents/Projects/musicbrainzws2-java-parent/musicbrainz-api-client/src/test/resources/example_data/lookup");

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
