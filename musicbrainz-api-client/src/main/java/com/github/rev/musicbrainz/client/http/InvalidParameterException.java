package com.github.rev.musicbrainz.client.http;

/**
 * Checked exception to encapsulate errors in constructing a query.
 */
public final class InvalidParameterException extends Exception {

    /**
     * @param fieldName The field that was invalid for this query.
     */
    public InvalidParameterException(final String fieldName) {
        super(String.format("Field '%s' is not valid for this query.", fieldName));
    }
}
