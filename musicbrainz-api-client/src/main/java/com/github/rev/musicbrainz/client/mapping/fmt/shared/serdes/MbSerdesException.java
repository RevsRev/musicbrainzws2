package com.github.rev.musicbrainz.client.mapping.fmt.shared.serdes;

/**
 * Encapsulate exceptions during parsing.
 */
public final class MbSerdesException extends Exception {

    /**
     * Constructor.
     */
    public MbSerdesException() {
        super();
    }

    /**
     * Constructor.
     * @param message A helpful message.
     */
    public MbSerdesException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message A helpful message.
     * @param cause The cause of this exception.
     */
    public MbSerdesException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     * @param cause The cause of this exception.
     */
    public MbSerdesException(final Throwable cause) {
        super(cause);
    }
}
