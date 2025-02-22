package github.com.rev.musicbrainz.wsxml.element;

import github.com.rev.musicbrainz.model.AnnotationListWs2;
import github.com.rev.musicbrainz.model.AnnotationWs2;
import github.com.rev.musicbrainz.model.PuidWs2;
import github.com.rev.musicbrainz.model.entity.AreaWs2;
import github.com.rev.musicbrainz.model.entity.ArtistWs2;
import github.com.rev.musicbrainz.model.entity.CollectionWs2;
import github.com.rev.musicbrainz.model.entity.DiscWs2;
import github.com.rev.musicbrainz.model.entity.InstrumentWs2;
import github.com.rev.musicbrainz.model.entity.LabelWs2;
import github.com.rev.musicbrainz.model.entity.PlaceWs2;
import github.com.rev.musicbrainz.model.entity.RecordingWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseGroupWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseWs2;
import github.com.rev.musicbrainz.model.entity.SeriesWs2;
import github.com.rev.musicbrainz.model.entity.WorkWs2;
import github.com.rev.musicbrainz.model.entity.listelement.AreaListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ArtistListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.CollectionListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.InstrumentListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.LabelListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.PlaceListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.RecordingListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ReleaseGroupListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ReleaseListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.SeriesListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.WorkListWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.AnnotationSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.AreaSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ArtistSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.CollectionSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.InstrumentSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.LabelSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.PlaceSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.RecordingSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseGroupSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.SeriesSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.WorkSearchResultsWs2;
import github.com.rev.musicbrainz.query.submission.SubmissionWs2;
import github.com.rev.musicbrainz.wsxml.MbXmlParser;

import javax.management.Query;

/**
 * <p>Represents a parsed Music Metadata XML document.</p>
 *
 * <p>The Music Metadata XML format is very flexible and may contain a
 * diverse set of data (e.g. an artist, a release and a list of tracks),
 * but usually only a small subset is used (either an artist, a release
 * or a track, or a lists of objects from one class).</p>
 *
 * @see Query
 * @see MbXmlParser
 */
public class Metadata {

    private LabelSearchResultsWs2 labelResultsWs2 = new LabelSearchResultsWs2();
    private ArtistSearchResultsWs2 artistResultsWs2 = new ArtistSearchResultsWs2();
    private ReleaseGroupSearchResultsWs2 releaseGroupResultsWs2 = new ReleaseGroupSearchResultsWs2();
    private ReleaseSearchResultsWs2 releaseResultsWs2 = new ReleaseSearchResultsWs2();
    private WorkSearchResultsWs2 workResultsWs2 = new WorkSearchResultsWs2();
    private RecordingSearchResultsWs2 recordingResultsWs2 = new RecordingSearchResultsWs2();
    private AreaSearchResultsWs2 areaResultsWs2 = new AreaSearchResultsWs2();
    private PlaceSearchResultsWs2 placeResultsWs2 = new PlaceSearchResultsWs2();
    private InstrumentSearchResultsWs2 instrumentResultsWs2 = new InstrumentSearchResultsWs2();
    private SeriesSearchResultsWs2 seriesResultsWs2 = new SeriesSearchResultsWs2();
    private AnnotationSearchResultsWs2 annotationResultsWs2 = new AnnotationSearchResultsWs2();
    private CollectionSearchResultsWs2 collectionResultsWs2 = new CollectionSearchResultsWs2();


    private LabelWs2 labelWs2 = null;
    private ArtistWs2 artistWs2 = null;
    private ReleaseGroupWs2 releaseGroupWs2 = null;
    private ReleaseWs2 releaseWs2 = null;
    private WorkWs2 workWs2 = null;
    private RecordingWs2 recordingWs2 = null;
    private AreaWs2 areaWs2 = null;
    private PlaceWs2 placeWs2 = null;
    private InstrumentWs2 instrumentWs2 = null;
    private SeriesWs2 seriesWs2 = null;

    private AnnotationWs2 annotationWs2 = null;
    private CollectionWs2 collectionWs2 = null;
    private DiscWs2 discWs2 = null;
    private PuidWs2 puidWs2 = null;

    private String message;

    private SubmissionWs2 submissionWs2 = null;

    // Entities.

    public LabelWs2 getLabelWs2() {
        return labelWs2;
    }

    public void setLabelWs2(LabelWs2 labelWs2) {
        this.labelWs2 = labelWs2;
    }

    public ArtistWs2 getArtistWs2() {
        return artistWs2;
    }

    public void setArtistWs2(ArtistWs2 artistWs2) {
        this.artistWs2 = artistWs2;
    }

    public ReleaseGroupWs2 getReleaseGroupWs2() {
        return releaseGroupWs2;
    }

    public void setReleaseGroupWs2(ReleaseGroupWs2 releaseGroupWs2) {
        this.releaseGroupWs2 = releaseGroupWs2;
    }

    public ReleaseWs2 getReleaseWs2() {
        return releaseWs2;
    }

    public void setReleaseWs2(ReleaseWs2 releaseWs2) {
        this.releaseWs2 = releaseWs2;
    }

    public WorkWs2 getWorkWs2() {
        return workWs2;
    }

    public void setWorkWs2(WorkWs2 workWs2) {
        this.workWs2 = workWs2;
    }

    public RecordingWs2 getRecordingWs2() {
        return recordingWs2;
    }

    public void setRecordingWs2(RecordingWs2 recordingWs2) {
        this.recordingWs2 = recordingWs2;
    }

    public AnnotationWs2 getAnnotationWs2() {
        return annotationWs2;
    }

    public void setAnnotationWs2(AnnotationWs2 annotationWs2) {
        this.annotationWs2 = annotationWs2;
    }

    public CollectionWs2 getCollectionWs2() {
        return collectionWs2;
    }

    public void setCollectionWs2(CollectionWs2 collectionWs2) {
        this.collectionWs2 = collectionWs2;
    }

    public DiscWs2 getDiscWs2() {
        return discWs2;
    }

    public void setDiscWs2(DiscWs2 discWs2) {
        this.discWs2 = discWs2;
    }

    public PuidWs2 getPuidWs2() {
        return puidWs2;
    }

    public void setPuidWs2(PuidWs2 puidWs2) {
        this.puidWs2 = puidWs2;
    }
    // Search Results.

    public LabelSearchResultsWs2 getLabelResultsWs2() {
        return labelResultsWs2;
    }

    public void setLabelResultsWs2(LabelSearchResultsWs2 labelResultsWs2) {
        this.labelResultsWs2 = labelResultsWs2;
    }

    public LabelListWs2 getLabelListWs2() {
        return getLabelResultsWs2().getLabelList();
    }

    public ArtistSearchResultsWs2 getArtistResultsWs2() {
        return artistResultsWs2;
    }

    public void setArtistResultsWs2(ArtistSearchResultsWs2 artistResultsWs2) {
        this.artistResultsWs2 = artistResultsWs2;
    }

    public ArtistListWs2 getArtistListWs2() {
        return getArtistResultsWs2().getArtistList();
    }

    public ReleaseGroupSearchResultsWs2 getReleaseGroupResultsWs2() {
        return releaseGroupResultsWs2;
    }

    public void setReleaseGroupResultsWs2(ReleaseGroupSearchResultsWs2 releaseGroupResultWs2) {
        this.releaseGroupResultsWs2 = releaseGroupResultWs2;
    }

    public ReleaseGroupListWs2 getReleaseGroupListWs2() {
        return getReleaseGroupResultsWs2().getReleaseGroupList();
    }

    public ReleaseSearchResultsWs2 getReleaseResultsWs2() {
        return releaseResultsWs2;
    }

    public void setReleaseResultsWs2(ReleaseSearchResultsWs2 releaseResultsWs2) {
        this.releaseResultsWs2 = releaseResultsWs2;
    }

    public ReleaseListWs2 getReleaseListWs2() {
        return getReleaseResultsWs2().getReleaseList();
    }

    public WorkSearchResultsWs2 getWorkResultsWs2() {
        return workResultsWs2;
    }

    public void setWorkResultsWs2(WorkSearchResultsWs2 workResultsWs2) {
        this.workResultsWs2 = workResultsWs2;
    }

    public WorkListWs2 getWorkListWs2() {
        return getWorkResultsWs2().getWorkList();
    }

    public RecordingSearchResultsWs2 getRecordingResultsWs2() {
        return recordingResultsWs2;
    }

    public void setRecordingResultsWs2(RecordingSearchResultsWs2 recordingResultsWs2) {
        this.recordingResultsWs2 = recordingResultsWs2;
    }

    public RecordingListWs2 getRecordingListWs2() {
        return getRecordingResultsWs2().getRecordingList();
    }

    public AnnotationSearchResultsWs2 getAnnotationResultsWs2() {
        return annotationResultsWs2;
    }

    public void setAnnotationResultsWs2(AnnotationSearchResultsWs2 annotationResultsWs2) {
        this.annotationResultsWs2 = annotationResultsWs2;
    }

    public AnnotationListWs2 getAnnotationListWs2() {
        return getAnnotationResultsWs2().getAnnotationList();
    }

    public CollectionSearchResultsWs2 getCollectionResultsWs2() {
        return collectionResultsWs2;
    }

    public void setCollectionResultsWs2(CollectionSearchResultsWs2 collectionSearchResultsWs2) {
        this.collectionResultsWs2 = collectionSearchResultsWs2;
    }

    public CollectionListWs2 getCollectionListWs2() {
        return getCollectionResultsWs2().getCollectionList();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the submissionWs2
     */
    public SubmissionWs2 getSubmissionWs2() {
        return submissionWs2;
    }

    /**
     * @param submissionWs2 the submissionWs2 to set
     */
    public void setSubmissionWs2(SubmissionWs2 submissionWs2) {
        this.submissionWs2 = submissionWs2;
    }

    /**
     * @return the areaWs2
     */
    public AreaWs2 getAreaWs2() {
        return areaWs2;
    }

    /**
     * @return the placeWs2
     */
    public PlaceWs2 getPlaceWs2() {
        return placeWs2;
    }

    /**
     * @return the instrumentWs2
     */
    public InstrumentWs2 getInstrumentWs2() {
        return instrumentWs2;
    }

    /**
     * @return the seriesWs2
     */
    public SeriesWs2 getSeriesWs2() {
        return seriesWs2;
    }

    /**
     * @param areaWs2 the areaWs2 to set
     */
    public void setAreaWs2(AreaWs2 areaWs2) {
        this.areaWs2 = areaWs2;
    }

    /**
     * @param placeWs2 the placeWs2 to set
     */
    public void setPlaceWs2(PlaceWs2 placeWs2) {
        this.placeWs2 = placeWs2;
    }

    /**
     * @param instrumentWs2 the instrumentWs2 to set
     */
    public void setInstrumentWs2(InstrumentWs2 instrumentWs2) {
        this.instrumentWs2 = instrumentWs2;
    }

    /**
     * @param seriesWs2 the seriesWs2 to set
     */
    public void setSeriesWs2(SeriesWs2 seriesWs2) {
        this.seriesWs2 = seriesWs2;
    }

    /**
     * @return the areaResultsWs2
     */
    public AreaSearchResultsWs2 getAreaResultsWs2() {
        return areaResultsWs2;
    }

    public AreaListWs2 getAreaListWs2() {
        return getAreaResultsWs2().getAreaList();
    }

    /**
     * @return the placeResultsWs2
     */
    public PlaceSearchResultsWs2 getPlaceResultsWs2() {
        return placeResultsWs2;
    }

    public PlaceListWs2 getPlaceListWs2() {
        return getPlaceResultsWs2().getPlaceList();
    }

    /**
     * @return the instrumentResultsWs2
     */
    public InstrumentSearchResultsWs2 getInstrumentResultsWs2() {
        return instrumentResultsWs2;
    }

    public InstrumentListWs2 getInstrumentListWs2() {
        return getInstrumentResultsWs2().getInstrumentList();
    }

    /**
     * @return the seriesResultsWs2
     */
    public SeriesSearchResultsWs2 getSeriesResultsWs2() {
        return seriesResultsWs2;
    }

    public SeriesListWs2 getSeriesListWs2() {
        return getSeriesResultsWs2().getSeriesList();
    }

    /**
     * @param areaResultsWs2 the areaResultsWs2 to set
     */
    public void setAreaResultsWs2(AreaSearchResultsWs2 areaResultsWs2) {
        this.areaResultsWs2 = areaResultsWs2;
    }

    /**
     * @param placeResultsWs2 the placeResultsWs2 to set
     */
    public void setPlaceResultsWs2(PlaceSearchResultsWs2 placeResultsWs2) {
        this.placeResultsWs2 = placeResultsWs2;
    }

    /**
     * @param instrumentResultsWs2 the instrumentResultsWs2 to set
     */
    public void setInstrumentResultsWs2(InstrumentSearchResultsWs2 instrumentResultsWs2) {
        this.instrumentResultsWs2 = instrumentResultsWs2;
    }

    /**
     * @param seriesResultsWs2 the seriesResultsWs2 to set
     */
    public void setSeriesResultsWs2(SeriesSearchResultsWs2 seriesResultsWs2) {
        this.seriesResultsWs2 = seriesResultsWs2;
    }

}
