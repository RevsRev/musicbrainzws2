package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.ReleaseSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseResultWs2;
import github.com.rev.musicbrainz.query.search.ReleaseSearchWs2;

import java.util.List;

public class ReleaseSearchbyTitle {

    private ReleaseSearchWs2 q;
    private final ReleaseSearchFilterWs2 f;

    public ReleaseSearchbyTitle(String title) {

        f = new ReleaseSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setTitle(title);

        q = new ReleaseSearchWs2(f);

    }

    public List<ReleaseResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<ReleaseResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new ReleaseSearchWs2(f);
        return q.getFirstPage();
    }

    public List<ReleaseResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }
}
