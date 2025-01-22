package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.AreaSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.AreaResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.AreaSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;


public class AreaSearchWs2 extends SearchWs2 {

    private AreaSearchResultsWs2 areaSearchResults = null;

    public AreaSearchWs2(AreaSearchFilterWs2 filter) {
        super(filter);
    }

    public AreaSearchWs2(WebService ws, AreaSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<AreaResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return areaSearchResults.getAreaResults();

    }

    public List<AreaResultWs2> getFirstPage() {

        areaSearchResults = new AreaSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return areaSearchResults.getAreaResults();
    }

    public List<AreaResultWs2> getNextPage() {

        List<AreaResultWs2> results = getOnePage();

        areaSearchResults.getAreaResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<AreaResultWs2> getResults() {

        if (areaSearchResults.getAreaResults() == null) {
            return getFirstPage();
        }

        return areaSearchResults.getAreaResults();

    }

    private List<AreaResultWs2> getOnePage() {

        List<AreaResultWs2> results
                = new ArrayList<AreaResultWs2>();

        try {
            AreaSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getAreaResults());


        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }

    private AreaSearchResultsWs2 execQuery() throws MBWS2Exception {

        AreaSearchResultsWs2 le = getMetadata(AREA).getAreaResultsWs2();
        setListElement(le);

        int sz = le.getAreaResults().size();
        if (sz > 0) {
            setLastScore(le.getAreaResults().get(sz - 1).getScore());
        }
        return le;
    }
}
