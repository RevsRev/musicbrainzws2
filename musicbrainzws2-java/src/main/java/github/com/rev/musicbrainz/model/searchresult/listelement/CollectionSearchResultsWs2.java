package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.CollectionListWs2;
import github.com.rev.musicbrainz.model.searchresult.CollectionResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

public class CollectionSearchResultsWs2 extends ListElement {

    private List<CollectionResultWs2> collectionResults = new ArrayList<CollectionResultWs2>();
    private CollectionListWs2 collectionList = new CollectionListWs2();

    /**
     * Convenience method to adds an {@link CollectionResultWs1} to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #collectionResults} is null.
     *
     * @param collectionResult The collection result to add
     */
    public void addCollectionResult(CollectionResultWs2 collectionResult) {
        if (getCollectionResults() == null) {
            collectionResults = new ArrayList<CollectionResultWs2>();
        }
        if (getCollectionList() == null) {
            collectionList = new CollectionListWs2();
        }

        collectionResults.add(collectionResult);
        collectionList.addCollection(collectionResult.getCollection());

        collectionList.setCount(getCount());
        collectionList.setOffset(getOffset());
    }

    public List<CollectionResultWs2> getCollectionResults() {
        return collectionResults;
    }

    public CollectionListWs2 getCollectionList() {
        return collectionList;
    }
}
