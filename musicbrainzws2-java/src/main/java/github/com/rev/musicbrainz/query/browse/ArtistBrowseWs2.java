package github.com.rev.musicbrainz.query.browse;

import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.browsefilter.ArtistBrowseFilterWs2;
import github.com.rev.musicbrainz.includes.ArtistIncludesWs2;
import github.com.rev.musicbrainz.model.entity.ArtistWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ArtistListWs2;
import github.com.rev.musicbrainz.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

public class ArtistBrowseWs2 extends BrowseWs2 {

    ArtistListWs2 artistList = null;

    public ArtistBrowseWs2(ArtistBrowseFilterWs2 filter,
                           ArtistIncludesWs2 include) {

        super(filter, include);
    }

    public ArtistBrowseWs2(WebService ws,
                           ArtistBrowseFilterWs2 filter,
                           ArtistIncludesWs2 include) {

        super(ws, filter, include);
    }

    public List<ArtistWs2> getFullList() {

        getFirstPage();
        while (hasMore()) {
            getNextPage();
        }
        return artistList.getArtists();

    }

    public List<ArtistWs2> getFirstPage() {

        artistList = new ArtistListWs2();
        getNextPage();

        return artistList.getArtists();
    }

    public List<ArtistWs2> getNextPage() {

        if (artistList == null) {
            return getFirstPage();
        }

        List<ArtistWs2> results = getOnePage();

        artistList.addAllArtists(results);
        filter.setOffset(filter.getOffset() + results.size());

        return results;
    }

    public List<ArtistWs2> getResults() {

        if (artistList.getArtists() == null) {
            return getFirstPage();
        }

        return artistList.getArtists();

    }

    private List<ArtistWs2> getOnePage() {

        List<ArtistWs2> results
                = new ArrayList<ArtistWs2>(0);

        try {
            ArtistListWs2 temp = execQuery();
            results.addAll(temp.getArtists());

        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {

            ex.printStackTrace();
        }

        return results;
    }


    private ArtistListWs2 execQuery() throws MBWS2Exception {

        ArtistListWs2 le = getMetadata(ARTIST).getArtistListWs2();
        listElement = le;

        int sz = le.getArtists().size();
        return le;
    }
}
