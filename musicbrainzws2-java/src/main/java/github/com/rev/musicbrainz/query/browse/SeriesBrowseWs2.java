package github.com.rev.musicbrainz.query.browse;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.browsefilter.SeriesBrowseFilterWs2;
import github.com.rev.musicbrainz.includes.SeriesIncludesWs2;
import github.com.rev.musicbrainz.model.entity.SeriesWs2;
import github.com.rev.musicbrainz.model.entity.listelement.SeriesListWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class SeriesBrowseWs2 extends BrowseWs2 {

    SeriesListWs2 seriesList = null;

    public SeriesBrowseWs2(SeriesBrowseFilterWs2 filter,
                           SeriesIncludesWs2 include) {

        super(filter, include);
    }

    public SeriesBrowseWs2(WebService ws,
                           SeriesBrowseFilterWs2 filter,
                           SeriesIncludesWs2 include) {

        super(ws, filter, include);
    }

    public List<SeriesWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return seriesList.getSeries();

    }

    public List<SeriesWs2> getFirstPage() {

        seriesList = new SeriesListWs2();
        getNextPage();

        return seriesList.getSeries();
    }

    public List<SeriesWs2> getNextPage() {

        if (seriesList == null) {
            return getFirstPage();
        }

        List<SeriesWs2> results = getOnePage();

        seriesList.addAllSeriess(results);
        filter.setOffset(filter.getOffset() + results.size());

        return results;
    }

    public List<SeriesWs2> getResults() {

        if (seriesList.getSeries() == null) {
            return getFirstPage();
        }

        return seriesList.getSeries();

    }

    private List<SeriesWs2> getOnePage() {

        List<SeriesWs2> results
                = new ArrayList<SeriesWs2>(0);

        try {
            SeriesListWs2 temp = execQuery();
            results.addAll(temp.getSeries());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }


    private SeriesListWs2 execQuery() throws MBWS2Exception {

        SeriesListWs2 le = getMetadata(SERIES).getSeriesListWs2();
        listElement = le;

        int sz = le.getSeries().size();
        return le;
    }
}
