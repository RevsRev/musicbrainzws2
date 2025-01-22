package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.WorkListWs2;
import github.com.rev.musicbrainz.model.searchresult.WorkResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

public class WorkSearchResultsWs2 extends ListElement {

    protected List<WorkResultWs2> workResults = new ArrayList<WorkResultWs2>();
    private WorkListWs2 workList = new WorkListWs2();

    public void addWorkResult(WorkResultWs2 workResult) {
        if (workResults == null) {
            workResults = new ArrayList<WorkResultWs2>();
        }
        if (getWorkList() == null) {
            workList = new WorkListWs2();
        }
        workResults.add(workResult);
        workList.addWork(workResult.getWork());

        workList.setCount(getCount());
        workList.setOffset(getOffset());
    }

    public List<WorkResultWs2> getWorkResults() {
        return workResults;
    }

    public WorkListWs2 getWorkList() {
        return workList;
    }
}
