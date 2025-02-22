package github.com.rev.musicbrainz.model;

import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Annotations
 */
public class AnnotationListWs2 extends ListElement {

    private List<AnnotationWs2> annotations = new ArrayList<AnnotationWs2>();

    /**
     * @return the works
     */
    public List<AnnotationWs2> getAnnotations() {
        return annotations;
    }

    /**
     * @param works the works to set
     */
    public void setAnnotations(List<AnnotationWs2> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotations(AnnotationWs2 annotation) {
        if (annotations == null) {
            annotations = new ArrayList<AnnotationWs2>();
        }

        annotations.add(annotation);
    }

    public void addAllAnnotations(List<AnnotationWs2> annotationList) {
        if (annotations == null) {
            annotations = new ArrayList<AnnotationWs2>();
        }

        annotations.addAll(annotationList);
    }
}
