package com.github.rev.musicbrainz.client.controller;

import com.github.rev.musicbrainz.client.MbClient;
import com.github.rev.musicbrainz.client.artist.MbArtistResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.entity.result.MbAnnotationResult;
import com.github.rev.musicbrainz.client.entity.result.MbAreaResult;
import com.github.rev.musicbrainz.client.entity.result.MbCdStubResult;
import com.github.rev.musicbrainz.client.entity.result.MbEventResult;
import com.github.rev.musicbrainz.client.entity.result.MbGenreResult;
import com.github.rev.musicbrainz.client.entity.result.MbInstrumentResult;
import com.github.rev.musicbrainz.client.entity.result.MbLabelResult;
import com.github.rev.musicbrainz.client.entity.result.MbPlaceResult;
import com.github.rev.musicbrainz.client.entity.result.MbRecordingResult;
import com.github.rev.musicbrainz.client.entity.result.MbReleaseGroupResult;
import com.github.rev.musicbrainz.client.entity.result.MbReleaseResult;
import com.github.rev.musicbrainz.client.entity.result.MbSeriesResult;
import com.github.rev.musicbrainz.client.entity.result.MbTagResult;
import com.github.rev.musicbrainz.client.entity.result.MbUrlResult;
import com.github.rev.musicbrainz.client.entity.result.MbWorkResult;
import com.github.rev.musicbrainz.client.mapping.MbJsonMapper;
import lombok.Getter;

/**
 * The controller for accessing the MusicBrainz API.
 */
@Getter
public final class MbController {

    private final MbEntityController<MbEntity.MbAnnotation, MbAnnotationResult> annotation;
    private final MbEntityController<MbEntity.MbArea, MbAreaResult> area;
    private final MbEntityController<MbEntity.MbArtist, MbArtistResult> artist;
    private final MbEntityController<MbEntity.CdStub, MbCdStubResult> cdStub;
    private final MbEntityController<MbEntity.MbEvent, MbEventResult> event;
    private final MbEntityController<MbEntity.MbGenre, MbGenreResult> genre;
    private final MbEntityController<MbEntity.MbInstrument, MbInstrumentResult> instrument;
    private final MbEntityController<MbEntity.MbLabel, MbLabelResult> label;
    private final MbEntityController<MbEntity.MbPlace, MbPlaceResult> place;
    private final MbEntityController<MbEntity.MbRecording, MbRecordingResult> recording;
    private final MbEntityController<MbEntity.MbRelease, MbReleaseResult> release;
    private final MbEntityController<MbEntity.MbReleaseGroup, MbReleaseGroupResult> releaseGroup;
    private final MbEntityController<MbEntity.MbSeries, MbSeriesResult> series;
    private final MbEntityController<MbEntity.MbTag, MbTagResult> tag;
    private final MbEntityController<MbEntity.MbUrl, MbUrlResult> url;
    private final MbEntityController<MbEntity.MbWork, MbWorkResult> work;

    @SuppressWarnings("checkstyle:ParameterNumber")
    private MbController(final MbEntityController<MbEntity.MbAnnotation, MbAnnotationResult> annotation,
                         final MbEntityController<MbEntity.MbArea, MbAreaResult> area,
                         final MbEntityController<MbEntity.MbArtist, MbArtistResult> artist,
                         final MbEntityController<MbEntity.CdStub, MbCdStubResult> cdStub,
                         final MbEntityController<MbEntity.MbEvent, MbEventResult> event,
                         final MbEntityController<MbEntity.MbGenre, MbGenreResult> genre,
                         final MbEntityController<MbEntity.MbInstrument, MbInstrumentResult> instrument,
                         final MbEntityController<MbEntity.MbLabel, MbLabelResult> label,
                         final MbEntityController<MbEntity.MbPlace, MbPlaceResult> place,
                         final MbEntityController<MbEntity.MbRecording, MbRecordingResult> recording,
                         final MbEntityController<MbEntity.MbRelease, MbReleaseResult> release,
                         final MbEntityController<MbEntity.MbReleaseGroup, MbReleaseGroupResult> releaseGroup,
                         final MbEntityController<MbEntity.MbSeries, MbSeriesResult> series,
                         final MbEntityController<MbEntity.MbTag, MbTagResult> tag,
                         final MbEntityController<MbEntity.MbUrl, MbUrlResult> url,
                         final MbEntityController<MbEntity.MbWork, MbWorkResult> work) {

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
        this.url = url;
        this.work = work;
    }

    /**
     * Factory method for an MbController. Deals with constructing the composed entity controllers.
     * @param client The MbClient instance shared by all entity controllers.
     * @return The constructed MbController instance.
     */
    public static MbController factory(final MbClient client) {
        return new MbController(
                new MbEntityController<>(client, MbJsonMapper.ANNOTATION_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.AREA_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.ARTIST_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.STUB_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.EVENT_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.GENRE_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.INSTRUMENT_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.LABEL_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.PLACE_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.RECORDING_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.RELEASE_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.RELEASEGROUP_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.SERIES_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.TAG_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.URL_MAPPER),
                new MbEntityController<>(client, MbJsonMapper.WORK_MAPPER)
        );
    }
}
