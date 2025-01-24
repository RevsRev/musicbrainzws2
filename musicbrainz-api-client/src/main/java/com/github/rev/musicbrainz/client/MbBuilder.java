package com.github.rev.musicbrainz.client;

/**
 * Instances of this class are used to build requests for the MusicBrainz API.
 * @param <T>
 */
public interface MbBuilder<T> {
    /**
     * @return valid.
     */
    boolean isValid();

    /**
     * @return the constructed request.
     * @throws MbBuildException if the request is considered invalid.
     */
    T build() throws MbBuildException;

    /**
     * Exception to encapsulate build errors.
     */
    final class MbBuildException extends Exception {

        /**
         * @param msg A message pertaining to the reason the build failed.
         */
        public MbBuildException(final String msg) {
            super(msg);
        }
    }
}
