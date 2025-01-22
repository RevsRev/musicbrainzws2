package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.ReleaseGroupSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import github.com.rev.musicbrainz.query.search.ReleaseGroupSearchWs2;

import java.util.List;

public class ReleaseGroupSearchbyTitle {

    private ReleaseGroupSearchWs2 q;
    private final ReleaseGroupSearchFilterWs2 f;

    public ReleaseGroupSearchbyTitle(String title) {

        f = new ReleaseGroupSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setTitle(title);

        q = new ReleaseGroupSearchWs2(f);

    }

    public List<ReleaseGroupResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<ReleaseGroupResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new ReleaseGroupSearchWs2(f);
        return q.getFirstPage();
    }

    public List<ReleaseGroupResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }
}
