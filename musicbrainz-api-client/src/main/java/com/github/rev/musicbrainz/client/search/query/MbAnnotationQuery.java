package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Annotations.
 */
public final class MbAnnotationQuery extends MbQuery<MbEntity.MbAnnotation> {

    /**
     * The associated entity's MBID of the annotation.
     */
    public static final String ENTITY = "entity";
    /**
     * The numeric ID of the annotation.
     */
    public static final String ID = "id";
    /**
     * The associated entity's name.
     */
    public static final String NAME = "name";
    /**
     * The annotation context.
     */
    public static final String TEXT = "text";
    /**
     * The annotated entity's type.
     */
    public static final String TYPE = "type";

    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                ENTITY,
                ID,
                NAME,
                TEXT,
                TYPE
        );
    }
}
