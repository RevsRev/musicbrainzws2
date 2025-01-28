package com.github.rev.musicbrainz.client.search.query;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.MbParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Base class for all queries submitted to the MusicBrainz API as part of a search request.
 *
 * @param <T> The entity type being browsed.
 */
public abstract class MbQuery<T extends MbEntity> implements MbParam {

    /**
     * Map of Field to be queried to the value being queried.
     */
    private final HashMap<String, String> queryParams = new HashMap<>();
    private final Set<String> queryFields = getQueryFields();

    /**
     * Instances should return a non-null map containing the permissible fields.
     * @return Set of Field names that are permissible for this query.
     */
    public abstract Set<String> getQueryFields();

    /**
     * Add a Field and Value to the query.
     * @param field The Field name.
     * @param value The Value.
     * @throws InvalidQueryFieldException If the Field name is not permissible for this query.
     */
    public final void add(final String field, final String value) throws InvalidQueryFieldException {
        if (!queryFields.contains(field)) {
            //TODO - Move validation to the point of building a query?
            throw new InvalidQueryFieldException(field);
        }
        queryParams.put(field, value);
    }

    /**
     * @return The query String to be submitted to the API.
     */
    public final String asQueryString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> keyValue : queryParams.entrySet()) {

            if (!first) {
                sb.append(" AND ");
            } else {
                first = false;
            }

            sb.append(keyValue.getKey());
            sb.append(':');
            sb.append(keyValue.getValue());
        }
        return sb.toString();
    }

    @Override
    public final String getName() {
        return "query";
    }

    @Override
    public final String getValue() {
        //TODO - Replace with some sort of "url formatter"...
        return asQueryString().replaceAll(" ", "%20");
    }

    /**
     * Checked exception to encapsulate errors in constructing a query.
     */
    public static final class InvalidQueryFieldException extends Exception {

        /**
         * @param fieldName The field that was invalid for this query.
         */
        public InvalidQueryFieldException(final String fieldName) {
            super(String.format("Field '%s' is not valid for this query.", fieldName));
        }
    }
}
