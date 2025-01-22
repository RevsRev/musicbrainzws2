package github.com.rev.musicbrainz.query.browse;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.browsefilter.AreaBrowseFilterWs2;
import github.com.rev.musicbrainz.includes.AreaIncludesWs2;
import github.com.rev.musicbrainz.model.entity.AreaWs2;
import github.com.rev.musicbrainz.model.entity.listelement.AreaListWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class AreaBrowseWs2 extends BrowseWs2 {

    AreaListWs2 areaList = null;

    public AreaBrowseWs2(AreaBrowseFilterWs2 filter,
                         AreaIncludesWs2 include) {

        super(filter, include);
    }

    public AreaBrowseWs2(WebService ws,
                         AreaBrowseFilterWs2 filter,
                         AreaIncludesWs2 include) {

        super(ws, filter, include);
    }

    public List<AreaWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return areaList.getAreas();

    }

    public List<AreaWs2> getFirstPage() {

        areaList = new AreaListWs2();
        getNextPage();

        return areaList.getAreas();
    }

    public List<AreaWs2> getNextPage() {

        if (areaList == null) {
            return getFirstPage();
        }

        List<AreaWs2> results = getOnePage();

        areaList.addAllAreas(results);
        filter.setOffset(filter.getOffset() + results.size());

        return results;
    }

    public List<AreaWs2> getResults() {

        if (areaList.getAreas() == null) {
            return getFirstPage();
        }

        return areaList.getAreas();

    }

    private List<AreaWs2> getOnePage() {

        List<AreaWs2> results
                = new ArrayList<AreaWs2>(0);

        try {
            AreaListWs2 temp = execQuery();
            results.addAll(temp.getAreas());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }


    private AreaListWs2 execQuery() throws MBWS2Exception {

        AreaListWs2 le = getMetadata(AREA).getAreaListWs2();
        listElement = le;

        int sz = le.getAreas().size();
        return le;
    }
}
