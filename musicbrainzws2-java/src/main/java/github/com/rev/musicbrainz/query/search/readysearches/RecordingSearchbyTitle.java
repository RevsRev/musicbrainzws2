package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.RecordingSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.RecordingResultWs2;
import github.com.rev.musicbrainz.query.search.RecordingSearchWs2;

import java.util.List;

public class RecordingSearchbyTitle {

    private RecordingSearchWs2 q;
    private final RecordingSearchFilterWs2 f;

    public RecordingSearchbyTitle(String title) {

        f = new RecordingSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setTitle(title);

        q = new RecordingSearchWs2(f);

    }

    public List<RecordingResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<RecordingResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new RecordingSearchWs2(f);
        return q.getFirstPage();
    }

    public List<RecordingResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }
}
