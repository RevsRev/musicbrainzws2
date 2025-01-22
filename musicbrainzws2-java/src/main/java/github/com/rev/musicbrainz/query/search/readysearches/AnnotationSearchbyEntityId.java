package github.com.rev.musicbrainz.query.search.readysearches;

import github.com.rev.musicbrainz.filter.searchfilter.AnnotationSearchtFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.AnnotationResultWs2;
import github.com.rev.musicbrainz.query.search.AnnotationSearchWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.List;

public class AnnotationSearchbyEntityId {

    private AnnotationSearchWs2 q;
    private final AnnotationSearchtFilterWs2 f;

    public AnnotationSearchbyEntityId(String Entity) {

        f = new AnnotationSearchtFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setEntity(Entity);

        q = new AnnotationSearchWs2(f);

    }

    public AnnotationSearchbyEntityId(WebService ws, String Entity) {

        f = new AnnotationSearchtFilterWs2();
        f.setLimit((long) 100);
        f.setOffset((long) 0);
        f.setMinScore((long) 20);

        f.setEntity(Entity);

        q = new AnnotationSearchWs2(ws, f);

    }

    public List<AnnotationResultWs2> getFullList() {

        return q.getFullList();

    }

    public List<AnnotationResultWs2> getFirstPage() {

        f.setOffset((long) 0);
        q = new AnnotationSearchWs2(f);
        return q.getFirstPage();
    }

    public List<AnnotationResultWs2> getNextPage() {
        return q.getNextPage();
    }

    public boolean hasMore() {
        return q.hasMore();
    }
}
