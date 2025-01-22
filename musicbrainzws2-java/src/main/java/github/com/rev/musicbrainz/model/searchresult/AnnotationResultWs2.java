package github.com.rev.musicbrainz.model.searchresult;

import github.com.rev.musicbrainz.model.AnnotationWs2;

/**
 * Represents an annotation result.
 */
public class AnnotationResultWs2 extends SearchResultWs2 {

    public AnnotationWs2 getAnnotation() {
        return (AnnotationWs2) super.getEntity();
    }

    public void setAnnotation(AnnotationWs2 annotation) {
        super.setEntity(annotation);
    }

}
