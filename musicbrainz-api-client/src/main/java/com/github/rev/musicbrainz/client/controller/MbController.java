package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.MbEntity;
import lombok.Getter;

/**
 * The controller for accessing the MusicBrainz API.
 */
@Getter
public final class MbController {

    private final MbEntityController<MbEntity.MbAnnotation> annotation;
    private final MbEntityController<MbEntity.MbArea> area;
    private final MbEntityController<MbEntity.MbArtist> artist;
    private final MbEntityController<MbEntity.CdStub> cdStub;
    private final MbEntityController<MbEntity.MbEvent> event;
    private final MbEntityController<MbEntity.MbGenre> genre;
    private final MbEntityController<MbEntity.MbInstrument> instrument;
    private final MbEntityController<MbEntity.MbLabel> label;
    private final MbEntityController<MbEntity.MbPlace> place;
    private final MbEntityController<MbEntity.MbRecording> recording;
    private final MbEntityController<MbEntity.MbRelease> release;
    private final MbEntityController<MbEntity.MbReleaseGroup> releaseGroup;
    private final MbEntityController<MbEntity.MbSeries> series;
    private final MbEntityController<MbEntity.MbTag> tag;
    private final MbEntityController<MbEntity.MbWork> work;
    private final MbEntityController<MbEntity.MbUrl> url;

    @SuppressWarnings("checkstyle:ParameterNumber")
    private MbController(final MbEntityController<MbEntity.MbAnnotation> annotation,
                        final MbEntityController<MbEntity.MbArea> area,
                        final MbEntityController<MbEntity.MbArtist> artist,
                        final MbEntityController<MbEntity.CdStub> cdStub,
                        final MbEntityController<MbEntity.MbEvent> event,
                        final MbEntityController<MbEntity.MbGenre> genre,
                        final MbEntityController<MbEntity.MbInstrument> instrument,
                        final MbEntityController<MbEntity.MbLabel> label,
                        final MbEntityController<MbEntity.MbPlace> place,
                        final MbEntityController<MbEntity.MbRecording> recording,
                        final MbEntityController<MbEntity.MbRelease> release,
                        final MbEntityController<MbEntity.MbReleaseGroup> releaseGroup,
                        final MbEntityController<MbEntity.MbSeries> series,
                        final MbEntityController<MbEntity.MbTag> tag,
                        final MbEntityController<MbEntity.MbWork> work,
                        final MbEntityController<MbEntity.MbUrl> url) {

        this.annotation = annotation;
        this.area = area;
        this.artist = artist;
        this.cdStub = cdStub;
        this.event = event;
        this.genre = genre;
        this.instrument = instrument;
        this.label = label;
        this.place = place;
        this.recording = recording;
        this.release = release;
        this.releaseGroup = releaseGroup;
        this.series = series;
        this.tag = tag;
        this.work = work;
        this.url = url;
    }

    /**
     * Factory method for an MbController. Deals with constructing the composed entity controllers.
     * @param client The MbClient instance shared by all entity controllers.
     * @return The constructed MbController instance.
     */
    public static MbController factory(final MbClient client) {
        return new MbController(
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client),
                new MbEntityController<>(client)
        );
    }
}
