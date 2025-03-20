package com.github.rev.musicbrainz.client.util;

import com.github.rev.musicbrainz.client.MbBuilder;
import com.github.rev.musicbrainz.client.MbFormat;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.InvalidParameterException;
import com.github.rev.musicbrainz.client.http.MbParam;
import com.github.rev.musicbrainz.client.lookup.MbLookupRequest;
import com.github.rev.musicbrainz.client.lookup.param.MbAnnotationLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbAreaLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbArtistLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbCdStubLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbEventLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbInstrumentLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbLabelLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbPlaceLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbRecordingLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbReleaseGroupLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbReleaseLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbSeriesLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbTagLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbUrlLookupParam;
import com.github.rev.musicbrainz.client.lookup.param.MbWorkLookupParam;
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
import org.apache.commons.lang3.tuple.Pair;
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

    public Collection<MbLookupRequest<T>> getAllLookupRequests() {
        Set<MbLookupRequest<T>> allLookupRequests = new HashSet<>();
        allLookupRequests.addAll(getLookupRequests(MbFormat.XML));
        allLookupRequests.addAll(getLookupRequests(MbFormat.JSON));
        return allLookupRequests;
    }


    public static abstract class EntityControllerTestParamImpl<T extends MbEntity> extends EntityControllerTestParam<T> {

        public abstract Collection<MbQuery<T>> getSearchQueries() throws InvalidParameterException;
        public abstract Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException;
        public abstract T getEntity();

        @Override
        public Collection<MbSearchRequest<T>> getSearchRequests(final MbFormat format) {
            try {
                Collection<MbQuery<T>> searchQueries = getSearchQueries();
                return searchQueries.stream().map(q -> buildSearchRequest(q, format)).collect(Collectors.toSet());
            } catch (Exception e) {
                throw new AssertionFailedError("Failed to construct test data", e);
            }
        }

        @Override
        public Collection<MbLookupRequest<T>> getLookupRequests(MbFormat format) {
            try {
                Collection<Pair<String, MbParam>> lookupParams = getLookupParams();
                return lookupParams.stream().map(p -> buildLookupRequest(p.getLeft(), p.getRight(), format)).collect(Collectors.toSet());
            } catch (Exception e) {
                throw new AssertionFailedError("Failed to construct test data", e);
            }
        }

        private MbSearchRequest<T> buildSearchRequest(final MbQuery<T> query, final MbFormat format) {
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

        private MbLookupRequest<T> buildLookupRequest(final String mbid, final MbParam query, final MbFormat format) {
            MbLookupRequest.Builder<T> builder = new MbLookupRequest.Builder<>();
            builder.setEntity(getEntity());
            builder.setMbid(mbid);
            builder.setParam(query);
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("94216f04-4537-441f-bc8c-23ebc0afc22d", new MbAnnotationLookupParam().add(MbAnnotationLookupParam.NAME))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("f03d09b3-39dc-4083-afd6-159e3f0d462f", new MbAreaLookupParam().add(MbAreaLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("bla94a7155-c79d-4409-9fcf-220cb0e4dc3aa", new MbArtistLookupParam().add(MbArtistLookupParam.RELEASES))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("y3SYj7gTeLE_HSDixUkxilKRVQ4-", new MbCdStubLookupParam().add(MbCdStubLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("44a654e3-c442-4f30-89df-1e49b261709f", new MbEventLookupParam().add(MbEventLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("96c9c681-ee2f-42a7-894b-c50d983b9e7f", new MbInstrumentLookupParam().add(MbInstrumentLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("30235128-d661-4233-879e-7129edcee178", new MbLabelLookupParam().add(MbLabelLookupParam.RELEASES))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("b1179210-4b99-4f15-957e-41d09e993020", new MbPlaceLookupParam().add(MbPlaceLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("6b2cfcd8-42b1-445f-9f07-d8b4f4a3df73", new MbRecordingLookupParam().add(MbRecordingLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("1aec03bf-ee85-4ad9-a54b-8b286f2d5577", new MbReleaseLookupParam().add(MbReleaseLookupParam.ARTISTS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("10219fc7-1810-4609-bcb7-b539f033ea40", new MbReleaseGroupLookupParam().add(MbReleaseGroupLookupParam.ARTISTS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("2917112d-13d1-4a86-9ea3-2e7e629741e4", new MbSeriesLookupParam().add(MbSeriesLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("1352c11e-a8b1-4e8e-bd2c-c98ddab724eb", new MbTagLookupParam().add(MbTagLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("00ccedeb-55a1-42cd-81ac-52ab61f67395", new MbWorkLookupParam().add(MbWorkLookupParam.RELATIONSHIPS))
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

        @Override
        public Collection<Pair<String, MbParam>> getLookupParams() throws InvalidParameterException {
            return List.of(
                    Pair.of("hmmmmm", new MbUrlLookupParam().add(MbUrlLookupParam.RELATIONSHIPS))
            );
        }
    }


}
