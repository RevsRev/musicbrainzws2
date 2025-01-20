package github.com.rev.musicbrainz.query.search;

import java.util.List;
import java.util.ArrayList;
import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.model.searchresult.listelement.CollectionSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.CollectionResultWs2;
import github.com.rev.musicbrainz.webservice.AuthorizationException;
import github.com.rev.musicbrainz.webservice.WebService;


public class CollectionSearchWs2 extends SearchWs2{

    public CollectionSearchWs2(){
      super(null);
    }
    
    public CollectionSearchWs2(WebService ws){
       super(ws, null);
    }
    
    public List <CollectionResultWs2> getResults() throws AuthorizationException, Exception{

        List <CollectionResultWs2> results 
                = new ArrayList <CollectionResultWs2>();

        try {
                    CollectionSearchResultsWs2 temp = execQuery();
                    results.addAll(temp.getCollectionResults());


            } catch (AuthorizationException ex){
                throw ex;
            
            }catch (Exception ex) {
                throw ex;
            }

        return results;
    }
     private CollectionSearchResultsWs2 execQuery() throws MBWS2Exception
    {

        CollectionSearchResultsWs2 le = getMetadata(COLLECTION).getCollectionResultsWs2();
        setListElement(le);
        
        int sz  = le.getCollectionResults().size();
        if (sz>0)
        {
            setLastScore((int) le.getCollectionResults().get(sz-1).getScore());
        }
        return le;
    }
}
