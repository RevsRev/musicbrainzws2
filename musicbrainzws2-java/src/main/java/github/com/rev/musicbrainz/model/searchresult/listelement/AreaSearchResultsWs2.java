package github.com.rev.musicbrainz.model.searchresult.listelement;

import java.util.ArrayList;
import java.util.List;

import github.com.rev.musicbrainz.model.entity.listelement.AreaListWs2;
import github.com.rev.musicbrainz.model.searchresult.AreaResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

public class AreaSearchResultsWs2 extends ListElement{

    protected List<AreaResultWs2> areaResults = new ArrayList<AreaResultWs2>();
    private AreaListWs2 areaList = new AreaListWs2();

    public void addAreaResult(AreaResultWs2 areaResult) 
    {
        if (areaResults == null) {
                areaResults = new ArrayList<AreaResultWs2>();
        }
        if (getAreaList() == null) {
                areaList = new AreaListWs2();
        }
        areaResults.add(areaResult);
        areaList.addArea(areaResult.getArea());
        
        areaList.setCount(getCount());
        areaList.setOffset(getOffset());
    }

    public List<AreaResultWs2> getAreaResults() {
        return areaResults;
    }

    public AreaListWs2 getAreaList() {
        return areaList;
    }
}
