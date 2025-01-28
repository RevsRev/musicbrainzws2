package com.github.rev.musicbrainz.client.parse;

/**
 * Interface for converting objects returned by the MusicBrainz API.
 * @param <T> The type to convert (source type).
 * @param <R> The type converted to (target type).
 */
public interface MbParser<T, R> {

    /**
     * Do the conversion.
     * @param input the input to be parsed.
     * @return the result of type R.
     */
    R parse(T input);
}
