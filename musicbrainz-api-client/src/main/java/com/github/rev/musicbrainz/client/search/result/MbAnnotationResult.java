package com.github.rev.musicbrainz.client.search.result;

import com.github.rev.musicbrainz.client.MbResult;
import com.github.rev.musicbrainz.client.entity.MbEntity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import org.musicbrainz.ns.mmd_2.AnnotationList;

import java.util.Date;

/**
 * Represents the result of searching/browsing/looking-up an Annotation.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MbAnnotationResult", propOrder = {
        "created",
        "annotation-list"
})
@XmlRootElement(name = "MbAnnotationResult")
@Getter @Setter
public final class MbAnnotationResult extends MbResult<MbEntity.MbAnnotation> {
    @XmlElement(name = "created")
    private Date created;

    @XmlElement(name = "annotation-list")
    private AnnotationList annotationList;
}
