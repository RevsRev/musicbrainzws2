package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.ReleaseSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class ReleaseSearchWs2 extends SearchWs2 {

    ReleaseSearchResultsWs2 releaseSearchResults = null;

    public ReleaseSearchWs2(ReleaseSearchFilterWs2 filter) {
        super(filter);
    }

    public ReleaseSearchWs2(WebService ws, ReleaseSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<ReleaseResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return releaseSearchResults.getReleaseResults();

    }

    public List<ReleaseResultWs2> getFirstPage() {

        releaseSearchResults = new ReleaseSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return releaseSearchResults.getReleaseResults();
    }

    public List<ReleaseResultWs2> getNextPage() {

        List<ReleaseResultWs2> results = getOnePage();

        releaseSearchResults.getReleaseResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<ReleaseResultWs2> getResults() {

        if (releaseSearchResults.getReleaseResults() == null) {
            return getFirstPage();
        }

        return releaseSearchResults.getReleaseResults();

    }

    private List<ReleaseResultWs2> getOnePage() {

        List<ReleaseResultWs2> results
                = new ArrayList<ReleaseResultWs2>(0);

        try {
            ReleaseSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getReleaseResults());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();

        }
        return results;
    }


    private ReleaseSearchResultsWs2 execQuery() throws MBWS2Exception {

        ReleaseSearchResultsWs2 le = getMetadata(RELEASE).getReleaseResultsWs2();
        setListElement(le);

        int sz = le.getReleaseResults().size();
        if (sz > 0) {
            setLastScore(le.getReleaseResults().get(sz - 1).getScore());
        }
        return le;
    }
}
