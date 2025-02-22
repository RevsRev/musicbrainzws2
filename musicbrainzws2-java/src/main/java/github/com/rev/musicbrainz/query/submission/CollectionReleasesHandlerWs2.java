package github.com.rev.musicbrainz.query.submission;

import github.com.rev.musicbrainz.model.entity.CollectionWs2;
import github.com.rev.musicbrainz.query.QueryWs2;
import github.com.rev.musicbrainz.webservice.WebService;
import github.com.rev.musicbrainz.webservice.WebServiceException;
import github.com.rev.musicbrainz.wsxml.MbXMLException;
import github.com.rev.musicbrainz.wsxml.element.Metadata;

import java.util.ArrayList;
import java.util.List;


public class CollectionReleasesHandlerWs2 extends QueryWs2 {

    private final List<String> releases;
    private final CollectionWs2 collection;

    public CollectionReleasesHandlerWs2(CollectionWs2 collection, List<String> releases) {
        super();
        this.releases = releases;
        this.collection = collection;
    }

    public CollectionReleasesHandlerWs2(WebService ws, CollectionWs2 collection, List<String> releases) {
        super(ws);
        this.releases = releases;
        this.collection = collection;
    }

    public Metadata put() throws WebServiceException, MbXMLException {

        List<String> toAdd = new ArrayList<String>();
        int max = 0;
        Metadata md = null;

        for (String rel : releases) {
            if (max > 25) {
                md = super.putToWebService(COLLECTION, collection.getId(), toAdd);
                //Logger.getLogger(Collection.class.getName()).log(Level.INFO, md.getMessage(), md.getMessage());
                toAdd.clear();
            }
            toAdd.add(rel);
        }
        if (!toAdd.isEmpty()) {
            md = super.putToWebService(COLLECTION, collection.getId(), toAdd);
            //Logger.getLogger(Collection.class.getName()).log(Level.INFO, md.getMessage(), md.getMessage());
        }
        return md;
    }

    public Metadata delete() throws WebServiceException, MbXMLException {

        List<String> toDelete = new ArrayList<String>();
        int max = 0;
        Metadata md = null;
        for (String rel : releases) {
            if (max > 25) {
                md = super.deleteFromWebService(COLLECTION, collection.getId(), toDelete);
                //Logger.getLogger(Collection.class.getName()).log(Level.INFO, md.getMessage(), md.getMessage());
                toDelete.clear();
            }
            toDelete.add(rel);
        }
        if (!toDelete.isEmpty()) {
            md = super.deleteFromWebService(COLLECTION, collection.getId(), toDelete);
            //Logger.getLogger(Collection.class.getName()).log(Level.INFO, md.getMessage(), md.getMessage());
        }
        return md;
    }
}
