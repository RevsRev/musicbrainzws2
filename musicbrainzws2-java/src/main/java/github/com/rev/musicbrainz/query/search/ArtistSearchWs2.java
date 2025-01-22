package github.com.rev.musicbrainz.query.search;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.ArtistSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.ArtistResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ArtistSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;


public class ArtistSearchWs2 extends SearchWs2 {

    private ArtistSearchResultsWs2 artistSearchResults = null;

    public ArtistSearchWs2(ArtistSearchFilterWs2 filter) {
        super(filter);
    }

    public ArtistSearchWs2(WebService ws, ArtistSearchFilterWs2 filter) {
        super(ws, filter);
    }

    public List<ArtistResultWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return artistSearchResults.getArtistResults();

    }

    public List<ArtistResultWs2> getFirstPage() {

        artistSearchResults = new ArtistSearchResultsWs2();
        setLastScore(100);
        getNextPage();

        return artistSearchResults.getArtistResults();
    }

    public List<ArtistResultWs2> getNextPage() {

        List<ArtistResultWs2> results = getOnePage();

        artistSearchResults.getArtistResults().addAll(results);
        getFilter().setOffset(getFilter().getOffset() + results.size());

        return results;
    }

    public List<ArtistResultWs2> getResults() {

        if (artistSearchResults.getArtistResults() == null) {
            return getFirstPage();
        }

        return artistSearchResults.getArtistResults();

    }

    private List<ArtistResultWs2> getOnePage() {

        List<ArtistResultWs2> results
                = new ArrayList<ArtistResultWs2>();

        try {
            ArtistSearchResultsWs2 temp = execQuery();
            results.addAll(temp.getArtistResults());


        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }

    private ArtistSearchResultsWs2 execQuery() throws MBWS2Exception {

        ArtistSearchResultsWs2 le = getMetadata(ARTIST).getArtistResultsWs2();
        setListElement(le);

        int sz = le.getArtistResults().size();
        if (sz > 0) {
            setLastScore(le.getArtistResults().get(sz - 1).getScore());
        }
        return le;
    }
}
