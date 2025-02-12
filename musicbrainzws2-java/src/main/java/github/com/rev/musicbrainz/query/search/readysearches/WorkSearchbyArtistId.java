package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.WorkSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.WorkResultWs2;
import github.com.rev.musicbrainz.query.search.WorkSearchWs2;

import java.util.List;


public class WorkSearchbyArtistId {

    private WorkSearchWs2 q;
    private final WorkSearchFilterWs2 f;

    public WorkSearchbyArtistId(String ArtistId) {

        f = new WorkSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        String query = "arid:" + ArtistId;

        f.setQuery(query);

        q = new WorkSearchWs2(f);

    }

    public List<WorkResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<WorkResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new WorkSearchWs2(f);
        return q.getFirstPage();
    }

    public List<WorkResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }
}
