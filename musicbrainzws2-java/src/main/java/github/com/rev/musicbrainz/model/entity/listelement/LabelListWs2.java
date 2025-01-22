package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.LabelWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Labels
 */
public class LabelListWs2 extends ListElement {

    private List<LabelWs2> labels = new ArrayList<LabelWs2>();

    /**
     * @return the labels
     */
    public List<LabelWs2> getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(List<LabelWs2> labels) {
        this.labels = labels;
    }

    /**
     * Adds a release to the list.
     * <p>
     * It will also create and set new ArrayList if
     * {@link #labels} is null.
     *
     * @param release The release to add
     */
    public void addLabel(LabelWs2 label) {
        if (labels == null) {
            labels = new ArrayList<LabelWs2>();
        }

        labels.add(label);
    }

    public void addAllLabels(List<LabelWs2> labelList) {
        if (labels == null) {
            labels = new ArrayList<LabelWs2>();
        }

        labels.addAll(labelList);
    }
}
