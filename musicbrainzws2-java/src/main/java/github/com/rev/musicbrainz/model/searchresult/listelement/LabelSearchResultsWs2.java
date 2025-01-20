package github.com.rev.musicbrainz.model.searchresult.listelement;
import java.util.ArrayList;
import java.util.List;

import github.com.rev.musicbrainz.model.entity.listelement.LabelListWs2;
import github.com.rev.musicbrainz.model.searchresult.LabelResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

public class LabelSearchResultsWs2 extends ListElement{

    private List<LabelResultWs2> labelResults = new ArrayList<LabelResultWs2>();
    private LabelListWs2 labelList = new LabelListWs2();

    public void addLabelResult(LabelResultWs2 labelResult) 
    {
            if (getLabelResults() == null) {
                    labelResults = new ArrayList<LabelResultWs2>();
            }
            if (getLabelList() == null) {
                labelList = new LabelListWs2();
            }
            
            labelResults.add(labelResult);
            labelList.addLabel(labelResult.getLabel()); 
            labelList.setCount(getCount());
            labelList.setOffset(getOffset());
    }
    
    public List<LabelResultWs2> getLabelResults() {
            return labelResults;
    }

    public LabelListWs2 getLabelList() {
        return labelList;
    }
}
