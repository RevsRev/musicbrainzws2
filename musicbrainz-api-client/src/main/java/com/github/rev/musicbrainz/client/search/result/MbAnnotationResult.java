package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.AnnotationList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Annotation.
 */
@Getter @Setter
public final class MbAnnotationResult extends MbResult<MbEntity.MbAnnotation> {
    private Date created;
    private AnnotationList annotationList;
}
