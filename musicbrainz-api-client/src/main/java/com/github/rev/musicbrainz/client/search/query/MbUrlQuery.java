package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;

import java.util.Set;

/**
 * MbQuery for Urls.
 */
public final class MbUrlQuery extends MbQuery<MbEntity.MbUrl> {

    /**
     * The type of a relationship the URL is in (e.g. "wikidata").
     */
    public static final String RELATION_TYPE = "relationtype";

    /**
     * The MBID of an entity related to the URL.
     */
    public static final String TARGET_ID = "targetid";

    /**
     * An entity type related to the URL (e.g. "artist").
     */
    public static final String TARGET_TYPE = "targettype";

    /**
     * The URL's MBID.
     */
    public static final String UID = "uid";

    /**
     * The actual URL string.
     */
    public static final String URL = "url";

    /**
     * The actual URL string, but also returns any ancestor paths (e.g. "https://example.org/some/stuff" will match
     * "https://example.org/some").
     */
    public static final String URL_ANCESTOR = "url_ancestor";

    /**
     * The actual URL string, but also returns any descendant paths (e.g. "https://example.org/some/stuff" will match
     * "https://example.org/some/stuff/here").
     */
    public static final String URL_DESCENDENT = "url_descendent";


    @Override
    public Set<String> getQueryFields() {
        return Set.of(
                RELATION_TYPE,
                TARGET_ID,
                TARGET_TYPE,
                UID,
                URL,
                URL_ANCESTOR,
                URL_DESCENDENT
        );
    }
}
