package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.ArtistListWs2;
import github.com.rev.musicbrainz.model.searchresult.ArtistResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

public class ArtistSearchResultsWs2 extends ListElement {

    private List<ArtistResultWs2> artistResults = new ArrayList<ArtistResultWs2>();
    private ArtistListWs2 artistList = new ArtistListWs2();

    /**
     * Convenience method to adds an {@link ArtistResultWs1} to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #artistResults} is null.
     *
     * @param artistResult The artist result to add
     */
    public void addArtistResult(ArtistResultWs2 artistResult) {
        if (getArtistResults() == null) {
            artistResults = new ArrayList<ArtistResultWs2>();
        }
        if (getArtistList() == null) {
            artistList = new ArtistListWs2();
        }

        artistResults.add(artistResult);
        artistList.addArtist(artistResult.getArtist());

        artistList.setCount(getCount());
        artistList.setOffset(getOffset());
    }

    public List<ArtistResultWs2> getArtistResults() {
        return artistResults;
    }

    public ArtistListWs2 getArtistList() {
        return artistList;
    }
}
