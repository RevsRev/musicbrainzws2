package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.WorkSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.WorkResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.WorkSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class WorkSearchWs2 extends SearchWs2 {

    WorkSearchResultsWs2 workSearchResults = null;

    public WorkSearchWs2(WorkSearchFilterWs2 filter) {
        super(filter);
    }

    public WorkSearchWs2(WebService ws, WorkSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<WorkResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return workSearchResults.getWorkResults();

    }

    public List<WorkResultWs2> getFirstPage() {

        workSearchResults = new WorkSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return workSearchResults.getWorkResults();
    }

    public List<WorkResultWs2> getNextPage() {

        List<WorkResultWs2> results = getOnePage();

        workSearchResults.getWorkResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<WorkResultWs2> getResults() {

        if (workSearchResults.getWorkResults() == null) {
            return getFirstPage();
        }

        return workSearchResults.getWorkResults();

    }

    private List<WorkResultWs2> getOnePage() {

        List<WorkResultWs2> results
                = new ArrayList<WorkResultWs2>();

        try {
            WorkSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getWorkResults());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();

        }
        return results;
    }

    private WorkSearchResultsWs2 execQuery() throws MBWS2Exception {

        WorkSearchResultsWs2 le = getMetadata(WORK).getWorkResultsWs2();
        setListElement(le);

        int sz = le.getWorkResults().size();
        if (sz > 0) {
            setLastScore(le.getWorkResults().get(sz - 1).getScore());
        }
        return le;
    }
}
