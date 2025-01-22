package github.com.rev.musicbrainz.query.browse;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.browsefilter.ReleaseBrowseFilterWs2;
import github.com.rev.musicbrainz.includes.ReleaseIncludesWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ReleaseListWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class ReleaseBrowseWs2 extends BrowseWs2 {

    ReleaseListWs2 releaseList = null;

    public ReleaseBrowseWs2(ReleaseBrowseFilterWs2 filter,
                            ReleaseIncludesWs2 include) {

        super(filter, include);
    }

    public ReleaseBrowseWs2(WebService ws,
                            ReleaseBrowseFilterWs2 filter,
                            ReleaseIncludesWs2 include) {

        super(ws, filter, include);
    }

    public List<ReleaseWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return releaseList.getReleases();

    }

    public List<ReleaseWs2> getFirstPage() {

        releaseList = new ReleaseListWs2();
        getNextPage();

        return releaseList.getReleases();
    }

    public List<ReleaseWs2> getNextPage() {

        if (releaseList == null) {
            return getFirstPage();
        }

        List<ReleaseWs2> results = getOnePage();

        releaseList.addAllReleases(results);
        filter.setOffset(filter.getOffset() + results.size());

        return results;
    }

    public List<ReleaseWs2> getResults() {

        if (releaseList.getReleases() == null) {
            return getFirstPage();
        }

        return releaseList.getReleases();

    }

    private List<ReleaseWs2> getOnePage() {

        List<ReleaseWs2> results
                = new ArrayList<ReleaseWs2>(0);

        try {
            ReleaseListWs2 temp = execQuery();
            results.addAll(temp.getReleases());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }
        return results;
    }


    private ReleaseListWs2 execQuery() throws MBWS2Exception {

        ReleaseListWs2 le = getMetadata(RELEASE).getReleaseListWs2();
        listElement = le;

        int sz = le.getReleases().size();
        return le;
    }
}
