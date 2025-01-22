package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.AnnotationListWs2;
import github.com.rev.musicbrainz.model.searchresult.AnnotationResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

public class AnnotationSearchResultsWs2 extends ListElement {

    protected List<AnnotationResultWs2> annotationResults = new ArrayList<AnnotationResultWs2>();
    private AnnotationListWs2 annotationList = new AnnotationListWs2();

    public void addAnnotationResult(AnnotationResultWs2 annotationResult) {
        if (annotationResults == null) {
            annotationResults = new ArrayList<AnnotationResultWs2>();
        }
        if (getAnnotationList() == null) {
            annotationList = new AnnotationListWs2();
        }
        annotationResults.add(annotationResult);
        annotationList.addAnnotations(annotationResult.getAnnotation());

        annotationList.setCount(getCount());
        annotationList.setOffset(getOffset());
    }

    public List<AnnotationResultWs2> getAnnotationResults() {
        return annotationResults;
    }

    public AnnotationListWs2 getAnnotationList() {
        return annotationList;
    }
}
