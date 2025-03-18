package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Annotations.
 */
public final class MbAnnotationLookupParam extends MbLookupParam<MbEntity.MbAnnotation> {

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

    /**
     * The include for RELATIONSHIPS.
     */
    public static final String RELATIONSHIPS = "relationships";

    @Override
    public Set<String> getValidIncludes() {
        return Set.of(
                ENTITY,
                ID,
                NAME,
                TEXT,
                TYPE,
                RELATIONSHIPS
        );
    }
}
