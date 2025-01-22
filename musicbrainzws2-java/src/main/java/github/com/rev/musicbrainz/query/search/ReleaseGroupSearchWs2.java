package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.ReleaseGroupSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseGroupSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class ReleaseGroupSearchWs2 extends SearchWs2 {

    ReleaseGroupSearchResultsWs2 releaseGroupSearchResults = null;

    public ReleaseGroupSearchWs2(ReleaseGroupSearchFilterWs2 filter) {
        super(filter);
    }

    public ReleaseGroupSearchWs2(WebService ws, ReleaseGroupSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<ReleaseGroupResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return releaseGroupSearchResults.getReleaseGroupResults();

    }

    public List<ReleaseGroupResultWs2> getFirstPage() {

        releaseGroupSearchResults = new ReleaseGroupSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return releaseGroupSearchResults.getReleaseGroupResults();
    }

    public List<ReleaseGroupResultWs2> getNextPage() {

        List<ReleaseGroupResultWs2> results = getOnePage();

        releaseGroupSearchResults.getReleaseGroupResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<ReleaseGroupResultWs2> getResults() {

        if (releaseGroupSearchResults.getReleaseGroupResults() == null) {
            return getFirstPage();
        }

        return releaseGroupSearchResults.getReleaseGroupResults();

    }

    private List<ReleaseGroupResultWs2> getOnePage() {

        List<ReleaseGroupResultWs2> results
                = new ArrayList<ReleaseGroupResultWs2>(0);

        try {
            ReleaseGroupSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getReleaseGroupResults());


        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();

        }
        return results;
    }


    private ReleaseGroupSearchResultsWs2 execQuery() throws MBWS2Exception {

        ReleaseGroupSearchResultsWs2 le = getMetadata(RELEASEGROUP).getReleaseGroupResultsWs2();
        setListElement(le);

        int sz = le.getReleaseGroupResults().size();
        if (sz > 0) {
            setLastScore(le.getReleaseGroupResults().get(sz - 1).getScore());
        }
        return le;
    }
}
