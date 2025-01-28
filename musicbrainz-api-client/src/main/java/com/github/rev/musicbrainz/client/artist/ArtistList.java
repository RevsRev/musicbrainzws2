package com.github.rev.musicbrainz.client.artist;

/**
 * Class to encapsulate part of an MbArtistResult.
 */
public final class ArtistList {
    private final int count;
    private final int offset;

    /**
     * @param count The number of items in the list.
     * @param offset The start offset, in the context of paginated calls to MusicBrainz.
     */
    public ArtistList(int count, int offset) {
        this.count = count;
        this.offset = offset;
    }
}
