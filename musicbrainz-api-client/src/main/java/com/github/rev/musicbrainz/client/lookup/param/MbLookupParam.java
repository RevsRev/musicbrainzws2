package com.github.rev.musicbrainz.client.lookup.param;

import com.github.rev.musicbrainz.client.entity.MbEntity;
import com.github.rev.musicbrainz.client.http.InvalidParameterException;
import com.github.rev.musicbrainz.client.http.MbParam;

import java.util.HashSet;
import java.util.Set;

public abstract class MbLookupParam<T extends MbEntity> implements MbParam {

    /**
     * Set of includes for this lookup.
     */
    private final Set<String> includes = new HashSet<>();
    private final Set<String> validIncludes = getValidIncludes();

    /**
     * Instances should return a non-null map containing the permissible fields.
     * @return Set of Field names that are permissible for this query.
     */
    public abstract Set<String> getValidIncludes();

    /**
     * Add a Field and Value to the query.
     * @param include The include name.
     * @throws InvalidParameterException If the Field name is not permissible for this query.
     */
    public final void add(final String include) throws InvalidParameterException {
        if (!validIncludes.contains(include)) {
            //TODO - Move validation to the point of building a query?
            throw new InvalidParameterException(include);
        }
        includes.add(include);
    }

    /**
     * @return The query String to be submitted to the API.
     */
    public final String asQueryString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String include : includes) {

            if (!first) {
                sb.append("+");
            } else {
                first = false;
            }
            sb.append(include);
        }
        return sb.toString();
    }

    @Override
    public final String getName() {
        return "lookup";
    }

    @Override
    public final String getValue() {
        //TODO - Replace with some sort of "url formatter"...
        return asQueryString().replaceAll(" ", "%20");
    }
}
