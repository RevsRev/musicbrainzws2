package com.github.rev.musicbrainz.client.util;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;

@Getter
public final class ControllerTestData {

    private final EntityControllerTestParam<MbEntity.MbAnnotation> annotationTests;
    private final EntityControllerTestParam<MbEntity.MbArea> areaTests;
    private final EntityControllerTestParam<MbEntity.MbArtist> artistTests;
    private final EntityControllerTestParam<MbEntity.CdStub> stubTests;
    private final EntityControllerTestParam<MbEntity.MbEvent> eventTests;
    private final EntityControllerTestParam<MbEntity.MbInstrument> instrumentTests;
    private final EntityControllerTestParam<MbEntity.MbLabel> labelTests;
    private final EntityControllerTestParam<MbEntity.MbPlace> placeTests;
    private final EntityControllerTestParam<MbEntity.MbRecording> recordingTests;
    private final EntityControllerTestParam<MbEntity.MbRelease> releaseTests;
    private final EntityControllerTestParam<MbEntity.MbReleaseGroup> releaseGroupTests;
    private final EntityControllerTestParam<MbEntity.MbSeries> seriesTests;
    private final EntityControllerTestParam<MbEntity.MbTag> tagTests;
    private final EntityControllerTestParam<MbEntity.MbWork> workTests;
    private final EntityControllerTestParam<MbEntity.MbUrl> urlTests;

    public ControllerTestData(EntityControllerTestParam<MbEntity.MbAnnotation> annotationTests,
                              EntityControllerTestParam<MbEntity.MbArea> areaTests,
                              EntityControllerTestParam<MbEntity.MbArtist> artistTests,
                              EntityControllerTestParam<MbEntity.CdStub> stubTests,
                              EntityControllerTestParam<MbEntity.MbEvent> eventTests,
                              EntityControllerTestParam<MbEntity.MbInstrument> instrumentTests,
                              EntityControllerTestParam<MbEntity.MbLabel> labelTests,
                              EntityControllerTestParam<MbEntity.MbPlace> placeTests,
                              EntityControllerTestParam<MbEntity.MbRecording> recordingTests,
                              EntityControllerTestParam<MbEntity.MbRelease> releaseTests,
                              EntityControllerTestParam<MbEntity.MbReleaseGroup> releaseGroupTests,
                              EntityControllerTestParam<MbEntity.MbSeries> seriesTests,
                              EntityControllerTestParam<MbEntity.MbTag> tagTests,
                              EntityControllerTestParam<MbEntity.MbWork> workTests,
                              EntityControllerTestParam<MbEntity.MbUrl> urlTests) {
        this.annotationTests = annotationTests;
        this.areaTests = areaTests;
        this.artistTests = artistTests;
        this.stubTests = stubTests;
        this.eventTests = eventTests;
        this.instrumentTests = instrumentTests;
        this.labelTests = labelTests;
        this.placeTests = placeTests;
        this.recordingTests = recordingTests;
        this.releaseTests = releaseTests;
        this.releaseGroupTests = releaseGroupTests;
        this.seriesTests = seriesTests;
        this.tagTests = tagTests;
        this.workTests = workTests;
        this.urlTests = urlTests;
    }

    public static ControllerTestData constructAllTestData() {
        return new ControllerTestData(
                new EntityControllerTestParam.AnnotationControllerTestParam(),
                new EntityControllerTestParam.AreaControllerTestParam(),
                new EntityControllerTestParam.ArtistControllerTestParam(),
                new EntityControllerTestParam.StubControllerTestParam(),
                new EntityControllerTestParam.EventControllerTestParam(),
                new EntityControllerTestParam.InstrumentControllerTestParam(),
                new EntityControllerTestParam.LabelControllerTestParam(),
                new EntityControllerTestParam.PlaceControllerTestParam(),
                new EntityControllerTestParam.RecordingControllerTestParam(),
                new EntityControllerTestParam.ReleaseControllerTestParam(),
                new EntityControllerTestParam.ReleaseGroupControllerTestParam(),
                new EntityControllerTestParam.SeriesControllerTestParam(),
                new EntityControllerTestParam.TagControllerTestParam(),
                new EntityControllerTestParam.WorkControllerTestParam(),
                new EntityControllerTestParam.UrlControllerTestParam()
        );
    }
}
