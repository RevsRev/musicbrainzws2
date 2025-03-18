package com.github.rev.musicbrainz.client.util;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.InvalidParameterException;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
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
import org.opentest4j.AssertionFailedError;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class EntityControllerTestParam<T extends MbEntity> {

    public abstract Collection<MbSearchRequest<T>> getSearchRequests(final MbFormat format);
    public abstract Collection<MbLookupRequest<T>> getLookupRequests(final MbFormat format);
    //TODO - Same pattern for browse

    public Collection<MbSearchRequest<T>> getAllSearchRequests() {
        Set<MbSearchRequest<T>> allSearchRequests = new HashSet<>();
        allSearchRequests.addAll(getSearchRequests(MbFormat.XML));
        allSearchRequests.addAll(getSearchRequests(MbFormat.JSON));
        return allSearchRequests;
    }


    public static abstract class EntityControllerTestParamImpl<T extends MbEntity> extends EntityControllerTestParam<T> {

        public abstract Collection<MbQuery<T>> getSearchQueries() throws InvalidParameterException;
        public abstract T getEntity();

        @Override
        public Collection<MbSearchRequest<T>> getSearchRequests(final MbFormat format) {
            Collection<MbQuery<T>> searchQueries = null;
            try {
                searchQueries = getSearchQueries();
                return searchQueries.stream().map(q -> buildRequest(q, format)).collect(Collectors.toSet());
            } catch (Exception e) {
                throw new AssertionFailedError("Failed to construct test data", e);
            }
        }

        @Override
        public Collection<MbLookupRequest<T>> getLookupRequests(MbFormat format) {
            //TODO - Implement
            return List.of();
        }

        private MbSearchRequest<T> buildRequest(final MbQuery<T> query, final MbFormat format) {
            MbSearchRequest.Builder<T> builder = new MbSearchRequest.Builder<>();
            builder.setEntity(getEntity());
            builder.setQuery(query);
            builder.setFormat(format);
            try {
                return builder.build();
            } catch (MbBuilder.MbBuildException e) {
                throw new AssertionFailedError("Failed to construct test data", e);
            }
        }
    }



    public static class AnnotationControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbAnnotation> {
        @Override
        public MbEntity.MbAnnotation getEntity() {
            return new MbEntity.MbAnnotation();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbAnnotation>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbAnnotationQuery().add(MbAnnotationQuery.NAME, "artist")
            );
        }
    }

    public static class AreaControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbArea> {
        @Override
        public MbEntity.MbArea getEntity() {
            return new MbEntity.MbArea();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbArea>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbAreaQuery().add(MbAreaQuery.AREA, "Rock")
            );
        }
    }

    public static class ArtistControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbArtist> {
        @Override
        public MbEntity.MbArtist getEntity() {
            return new MbEntity.MbArtist();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbArtist>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbArtistQuery().add(MbArtistQuery.ARTIST, "Fleetwood")
            );
        }
    }

    public static class StubControllerTestParam extends EntityControllerTestParamImpl<MbEntity.CdStub> {
        @Override
        public MbEntity.CdStub getEntity() {
            return new MbEntity.CdStub();
        }

        @Override
        public Collection<MbQuery<MbEntity.CdStub>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbCdStubQuery().add(MbCdStubQuery.TITLE, "Doo")
            );
        }
    }

    public static class EventControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbEvent> {
        @Override
        public MbEntity.MbEvent getEntity() {
            return new MbEntity.MbEvent();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbEvent>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbEventQuery().add(MbEventQuery.PLACE, "USA")
            );
        }
    }


    public static class InstrumentControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbInstrument> {
        @Override
        public MbEntity.MbInstrument getEntity() {
            return new MbEntity.MbInstrument();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbInstrument>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbInstrumentQuery().add(MbInstrumentQuery.INSTRUMENT, "Guitar")
            );
        }
    }

    public static class LabelControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbLabel> {
        @Override
        public MbEntity.MbLabel getEntity() {
            return new MbEntity.MbLabel();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbLabel>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbLabelQuery().add(MbLabelQuery.COUNTRY, "US")
            );
        }
    }

    public static class PlaceControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbPlace> {
        @Override
        public MbEntity.MbPlace getEntity() {
            return new MbEntity.MbPlace();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbPlace>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbPlaceQuery().add(MbPlaceQuery.PLACE, "USA")
            );
        }
    }

    public static class RecordingControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbRecording> {
        @Override
        public MbEntity.MbRecording getEntity() {
            return new MbEntity.MbRecording();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbRecording>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbRecordingQuery().add(MbRecordingQuery.ARTIST, "Fleetwood Mac")
            );
        }
    }

    public static class ReleaseControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbRelease> {
        @Override
        public MbEntity.MbRelease getEntity() {
            return new MbEntity.MbRelease();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbRelease>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbReleaseQuery().add(MbReleaseQuery.ARTIST, "Fleetwood Mac")
            );
        }
    }

    public static class ReleaseGroupControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbReleaseGroup> {
        @Override
        public MbEntity.MbReleaseGroup getEntity() {
            return new MbEntity.MbReleaseGroup();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbReleaseGroup>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbReleaseGroupQuery().add(MbReleaseGroupQuery.RELEASE, "Rumours")
            );
        }
    }

    public static class SeriesControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbSeries> {
        @Override
        public MbEntity.MbSeries getEntity() {
            return new MbEntity.MbSeries();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbSeries>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbSeriesQuery().add(MbSeriesQuery.SERIES, "roll")
            );
        }
    }

    public static class TagControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbTag> {
        @Override
        public MbEntity.MbTag getEntity() {
            return new MbEntity.MbTag();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbTag>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbTagQuery().add(MbTagQuery.TAG, "tag")
            );
        }
    }

    public static class WorkControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbWork> {
        @Override
        public MbEntity.MbWork getEntity() {
            return new MbEntity.MbWork();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbWork>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbWorkQuery().add(MbWorkQuery.ARTIST, "Fleetwood Mac")
            );
        }
    }

    public static class UrlControllerTestParam extends EntityControllerTestParamImpl<MbEntity.MbUrl> {
        @Override
        public MbEntity.MbUrl getEntity() {
            return new MbEntity.MbUrl();
        }

        @Override
        public Collection<MbQuery<MbEntity.MbUrl>> getSearchQueries() throws InvalidParameterException {
            return List.of(
                    new MbUrlQuery().add(MbUrlQuery.RELATION_TYPE, "wikidata")
            );
        }
    }


}
