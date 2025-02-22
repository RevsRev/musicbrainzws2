package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.InstrumentSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.InstrumentResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.InstrumentSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;


public class InstrumentSearchWs2 extends SearchWs2 {

    private InstrumentSearchResultsWs2 instrumentSearchResults = null;

    public InstrumentSearchWs2(InstrumentSearchFilterWs2 filter) {
        super(filter);
    }

    public InstrumentSearchWs2(WebService ws, InstrumentSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<InstrumentResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return instrumentSearchResults.getInstrumentResults();

    }

    public List<InstrumentResultWs2> getFirstPage() {

        instrumentSearchResults = new InstrumentSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return instrumentSearchResults.getInstrumentResults();
    }

    public List<InstrumentResultWs2> getNextPage() {

        List<InstrumentResultWs2> results = getOnePage();

        instrumentSearchResults.getInstrumentResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<InstrumentResultWs2> getResults() {

        if (instrumentSearchResults.getInstrumentResults() == null) {
            return getFirstPage();
        }

        return instrumentSearchResults.getInstrumentResults();

    }

    private List<InstrumentResultWs2> getOnePage() {

        List<InstrumentResultWs2> results
                = new ArrayList<InstrumentResultWs2>();

        try {
            InstrumentSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getInstrumentResults());


        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }

    private InstrumentSearchResultsWs2 execQuery() throws MBWS2Exception {

        InstrumentSearchResultsWs2 le = getMetadata(INSTRUMENT).getInstrumentResultsWs2();
        setListElement(le);

        int sz = le.getInstrumentResults().size();
        if (sz > 0) {
            setLastScore(le.getInstrumentResults().get(sz - 1).getScore());
        }
        return le;
    }
}
