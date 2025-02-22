package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.CollectionWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Collections
 */
public class CollectionListWs2 extends ListElement {

    protected List<CollectionWs2> collections = new ArrayList<CollectionWs2>();

    /**
     * @return the collections
     */
    public List<CollectionWs2> getCollections() {
        return collections;
    }

    /**
     * @param collections the collections to set
     */
    public void setCollections(List<CollectionWs2> collections) {
        this.collections = collections;
    }

    /**
     * Convenience method to adds an collection to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #collections} is null.
     *
     * @param collection
     */
    public void addCollection(CollectionWs2 collection) {
        if (collections == null) {
            collections = new ArrayList<CollectionWs2>();
        }
        collections.add(collection);
    }

    public void addAllCollections(List<CollectionWs2> collectionList) {
        if (collections == null) {
            collections = new ArrayList<CollectionWs2>();
        }

        collections.addAll(collectionList);
    }
}
