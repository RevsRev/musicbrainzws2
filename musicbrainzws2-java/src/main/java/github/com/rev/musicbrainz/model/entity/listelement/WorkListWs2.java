package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.WorkWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Works
 */
public class WorkListWs2 extends ListElement {

    private List<WorkWs2> works = new ArrayList<WorkWs2>();

    /**
     * @return the works
     */
    public List<WorkWs2> getWorks() {
        return works;
    }

    /**
     * @param works the works to set
     */
    public void setWorks(List<WorkWs2> works) {
        this.works = works;
    }

    public void addWork(WorkWs2 work) {
        if (works == null) {
            works = new ArrayList<WorkWs2>();
        }

        works.add(work);
    }

    public void addAllWorks(List<WorkWs2> workList) {
        if (works == null) {
            works = new ArrayList<WorkWs2>();
        }

        works.addAll(workList);
    }
}
