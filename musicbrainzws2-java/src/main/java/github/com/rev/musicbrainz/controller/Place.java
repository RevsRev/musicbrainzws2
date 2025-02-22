/*
 * A controller for the Place Entity.
 *
 */
package github.com.rev.musicbrainz.controller;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.PlaceSearchFilterWs2;
import github.com.rev.musicbrainz.includes.IncludesWs2;
import github.com.rev.musicbrainz.includes.PlaceIncludesWs2;
import github.com.rev.musicbrainz.model.RelationWs2;
import github.com.rev.musicbrainz.model.entity.PlaceWs2;
import github.com.rev.musicbrainz.model.entity.RecordingWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseGroupWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseWs2;
import github.com.rev.musicbrainz.model.searchresult.PlaceResultWs2;
import github.com.rev.musicbrainz.query.lookUp.LookUpWs2;
import github.com.rev.musicbrainz.query.search.PlaceSearchWs2;

import java.util.List;

public class Place extends Controller {

    public Place() {

        super();
        setIncluded(new PlaceIncludesWs2());
    }


// -------------- Search  -------------------------------------------------//

    @Override
    public PlaceSearchFilterWs2 getSearchFilter() {

        return (PlaceSearchFilterWs2) super.getSearchFilter();
    }

    @Override
    protected PlaceSearchWs2 getSearch() {

        return (PlaceSearchWs2) super.getSearch();
    }

    @Override
    protected PlaceSearchFilterWs2 getDefaultSearchFilter() {

        PlaceSearchFilterWs2 f = new PlaceSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        return f;

    }

    @Override
    public void search(String searchText) {

        initSearch(searchText);
        setSearch(new PlaceSearchWs2(getQueryWs(), getSearchFilter()));
    }

    public List<PlaceResultWs2> getFullSearchResultList() {

        return getSearch().getFullList();

    }

    public List<PlaceResultWs2> getFirstSearchResultPage() {

        return getSearch().getFirstPage();
    }

    public List<PlaceResultWs2> getNextSearchResultPage() {

        return getSearch().getNextPage();
    }

    // -------------- LookUp -------------------------------------------------//

    @Override
    public PlaceIncludesWs2 getIncludes() {
        return (PlaceIncludesWs2) super.getIncludes();
    }

    @Override
    protected PlaceIncludesWs2 getDefaultIncludes() {

        PlaceIncludesWs2 inc = new PlaceIncludesWs2();

        inc.setUrlRelations(true);
        inc.setLabelRelations(true);
        inc.setArtistRelations(true);
        inc.setReleaseGroupRelations(true);
        inc.setReleaseRelations(true);
        inc.setRecordingRelations(true);
        inc.setWorkRelations(false);
        inc.setAreaRelations(true);
        inc.setPlaceRelations(true);
        inc.setInstrumentRelations(true);
        inc.setSeriesRelations(true);

        inc.setTags(false);
        inc.setRatings(false);
        inc.setUserTags(false);
        inc.setUserRatings(false);

        inc.setAnnotation(true);
        inc.setAliases(false);
        inc.setArtistCredits(false);

        return inc;
    }

    /**
     * @return the Included
     */
    @Override
    protected PlaceIncludesWs2 getIncluded() {
        return (PlaceIncludesWs2) super.getIncluded();
    }

    private PlaceWs2 getPlace() {
        return (PlaceWs2) getEntity();
    }

    public PlaceWs2 getComplete(PlaceWs2 place) throws MBWS2Exception {
        if (place == null) {
            return null;
        }
        if (place.getId() == null) {
            return place;
        }

        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(place);

        return getComplete(place.getId());
    }

    public PlaceWs2 getComplete(String id) throws MBWS2Exception {

        setEntity(lookUp(id));

        return getPlace();
    }

    public PlaceWs2 lookUp(PlaceWs2 place) throws MBWS2Exception {

        if (place == null) {
            return null;
        }
        if (place.getId() == null) {
            return place;
        }

        // save some field that come from search, but is missing in
        // lookUp http://tickets.musicbrainz.org/browse/MBS-3982
        setIncoming(place);

        return lookUp(place.getId());
    }


    protected PlaceIncludesWs2 getIncrementalInc(PlaceIncludesWs2 inc) {

        inc = (PlaceIncludesWs2) super.getIncrementalInc(inc);
        if (getIncludes().isAliases() && !getIncluded().isAliases()) {
            inc.setAliases(true);
        }

        return inc;
    }

    private boolean needsLookUp(PlaceIncludesWs2 inc) {

        return (getPlace() == null ||
                super.needsLookUp(inc) ||
                inc.isAliases());
    }

    public PlaceWs2 lookUp(String id) throws MBWS2Exception {

        PlaceIncludesWs2 inc = getIncrementalInc(new PlaceIncludesWs2());

        // LookUp is limited by 25 linked entities, to be sure
        // is better perform a Browse (you could also get first 25
        // at lookUp time just hiitting placeInclude.setReleases(true), 
        // check if there could be  more releases left and in case perform 
        // the Browse... worry about sort order...).

        // Sanity check.
        inc.setArtistCredits(false); // invalid request
        inc.setRecordingLevelRelations(false);// invalid request
        inc.setWorkLevelRelations(false);// invalid request
        //
        inc.setTags(false);// invalid request
        inc.setRatings(false);// invalid request
        inc.setUserTags(false);// invalid request
        inc.setUserRatings(false);// invalid request

        if (needsLookUp(inc)) {
            setLookUp(new LookUpWs2(getQueryWs()));

            PlaceWs2 transit = null;
            transit = getLookUp().getPlaceById(id, inc);


            if (transit == null) {
                return null;
            }

            if (getPlace() == null || !getPlace().equals(transit)) // place is changed.
            {
                setEntity(transit);
                setIncluded(inc);
            } else {
                updateEntity(getPlace(), transit, inc);
                if (inc.isAliases()) {
                    getPlace().setAliases(transit.getAliases());
                    getIncluded().setAliases(true);
                }
            }
        }
        if (inc.isAnnotation()) {
            loadAnnotation(getPlace());
        }

        return getPlace();
    }

    @Override
    protected void getRelationTarget(RelationWs2 rel, IncludesWs2 inc) throws MBWS2Exception {

        super.getRelationTarget(rel, inc);

        /* Ws2 don't allow Artist Credits requests for places
         * at the moment,so we have to complete the relations.
         * with target derived informations.
         *
         * Time consuming, but no other way. To avoid it, set
         * getIncludes().setArtistCredits(false) when asking for
         * relations.
         */

        if (!getIncludes().isArtistCredits()) {
            return;
        }

        if (inc.isRecordingRelations() &&
                rel.getTargetType().equals(RelationWs2.TO_RECORDING)) {

            RecordingWs2 recWs2 = (RecordingWs2) rel.getTarget();

            if (recWs2.getArtistCredit() != null) {
                return;
            }

            Recording rec = new Recording();
            rec.setQueryWs(getQueryWs());

            rec.getIncludes().excludeAll();
            rec.getIncludes().setArtistCredits(true);

            recWs2 = rec.lookUp(recWs2);
            rel.setTarget(recWs2);
        } else if (inc.isReleaseRelations() &&
                rel.getTargetType().equals(RelationWs2.TO_RELEASE)) {

            ReleaseWs2 relWs2 = (ReleaseWs2) rel.getTarget();

            if (relWs2.getArtistCredit() != null) {
                return;
            }

            Release rls = new Release();

            rls.setQueryWs(getQueryWs());

            rls.getIncludes().excludeAll();
            rls.getIncludes().setArtistCredits(true);

        } else if (inc.isReleaseGroupRelations() &&
                rel.getTargetType().equals(RelationWs2.TO_RELEASE_GROUP)) {

            ReleaseGroupWs2 relWs2 = (ReleaseGroupWs2) rel.getTarget();

            if (relWs2.getArtistCredit() != null) {
                return;
            }

            ReleaseGroup rg = new ReleaseGroup();

            rg.setQueryWs(getQueryWs());

            rg.getIncludes().excludeAll();
            rg.getIncludes().setArtistCredits(true);
        }
    }
}
