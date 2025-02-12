package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.LabelSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.LabelResultWs2;
import github.com.rev.musicbrainz.query.search.LabelSearchWs2;

import java.util.List;


public class LabelSearchbyName implements EntitySearchInterface {

    private LabelSearchWs2 q;
    private final LabelSearchFilterWs2 f;

    public LabelSearchbyName(String name) {

        f = new LabelSearchFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setLabelName(name);

        q = new LabelSearchWs2(f);

    }

    public List<LabelResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<LabelResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new LabelSearchWs2(f);
        return q.getFirstPage();
    }

    public List<LabelResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }

}
