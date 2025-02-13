package com.github.rev.musicbrainz.client.mapping;

/**
 * Interface for converting objects returned by the MusicBrainz API.
 * @param <T> The type to convert (source type).
 * @param <R> The type converted to (target type).
 */
public interface MbMapper<T, R> {

    /**
     * Do the conversion.
     * @param input the input to be parsed.
     * @return the result of type R.
     */
    R parse(T input);

    /**
     * Do the inverse of the conversion.
     * @param input the input to be written.
     * @return the result of type T.
     */
    T write(R input);
}
