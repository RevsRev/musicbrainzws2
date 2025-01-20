package github.com.rev.musicbrainz.query.search;

import java.util.List;
import java.util.ArrayList;
import github.com.rev.musicbrainz.exception.MBWS2Exception;
import github.com.rev.musicbrainz.filter.searchfilter.LabelSearchFilterWs2;
import github.com.rev.musicbrainz.model.searchresult.LabelResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.LabelSearchResultsWs2;
import github.com.rev.musicbrainz.webservice.WebService;

public class LabelSearchWs2 extends SearchWs2{

    LabelSearchResultsWs2 labelSearchResults = null;
  
    public LabelSearchWs2(LabelSearchFilterWs2 filter){
       super(filter);
    }
    public LabelSearchWs2(WebService ws, LabelSearchFilterWs2 filter){
       super(ws, filter);
    }
    public List <LabelResultWs2> getFullList() {

        getFirstPage();
        while (hasMore())
        {
           getNextPage();
        }
        return labelSearchResults.getLabelResults();

    }
    public List <LabelResultWs2> getFirstPage() {

        labelSearchResults = new LabelSearchResultsWs2(); 
        setLastScore(100);
        getNextPage();

        return labelSearchResults.getLabelResults();
    }

    public List <LabelResultWs2> getNextPage() {
       
        List<LabelResultWs2> results  = getOnePage();
        
        labelSearchResults.getLabelResults().addAll(results); 
        getFilter().setOffset(getFilter().getOffset()+results.size());

        return results;
    }
    public List <LabelResultWs2> getResults(){
        
        if (labelSearchResults.getLabelResults() == null)
        return getFirstPage();
            
        return labelSearchResults.getLabelResults();
        
    }
     private List <LabelResultWs2> getOnePage(){

        List <LabelResultWs2> results 
                = new ArrayList <LabelResultWs2>();

        try {
                    LabelSearchResultsWs2 temp = execQuery();
                    results.addAll(temp.getLabelResults());
                    return results;
          
        } catch (github.com.rev.musicbrainz.exception.MBWS2Exception ex) {
                    ex.printStackTrace();
                    return results;
        }
    }
    
    private LabelSearchResultsWs2 execQuery() throws MBWS2Exception
    {

        LabelSearchResultsWs2 le = getMetadata(LABEL).getLabelResultsWs2();
        setListElement(le);
        
        int sz  = le.getLabelResults().size();
        if (sz>0)
        {
            setLastScore((int) le.getLabelResults().get(sz-1).getScore());
        }
        return le;
    }
}
