package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.RecordingSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.RecordingResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.RecordingSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class RecordingSearchWs2 extends SearchWs2 {

    RecordingSearchResultsWs2 recordingSearchResults = null;

    public RecordingSearchWs2(RecordingSearchFilterWs2 filter) {
        super(filter);
    }

    public RecordingSearchWs2(WebService ws, RecordingSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<RecordingResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return recordingSearchResults.getRecordingResults();

    }

    public List<RecordingResultWs2> getFirstPage() {

        recordingSearchResults = new RecordingSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return recordingSearchResults.getRecordingResults();
    }

    public List<RecordingResultWs2> getNextPage() {

        List<RecordingResultWs2> results = getOnePage();

        recordingSearchResults.getRecordingResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<RecordingResultWs2> getResults() {

        if (recordingSearchResults.getRecordingResults() == null) {
            return getFirstPage();
        }

        return recordingSearchResults.getRecordingResults();

    }

    private List<RecordingResultWs2> getOnePage() {

        List<RecordingResultWs2> results
                = new ArrayList<RecordingResultWs2>(0);

        try {
            RecordingSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getRecordingResults());


        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }
        return results;
    }


    private RecordingSearchResultsWs2 execQuery() throws MBWS2Exception {

        RecordingSearchResultsWs2 le = getMetadata(RECORDING).getRecordingResultsWs2();
        setListElement(le);

        int sz = le.getRecordingResults().size();
        if (sz > 0) {
            setLastScore(le.getRecordingResults().get(sz - 1).getScore());
        }
        return le;
    }
}
