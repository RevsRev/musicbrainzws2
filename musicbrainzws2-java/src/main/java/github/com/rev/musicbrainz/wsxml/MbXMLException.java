package github.com.rev.musicbrainz.wsxml;

import github.com.rev.musicbrainz.exception.MBWS2Exception;

/**
 * This exception and its sublcasses are thrown by
 * {@link MbXmlParser} implementations.
 *
 * @author Patrick Ruhkopf
 */
public class MbXMLException extends MBWS2Exception {

    public MbXMLException() {
        super();
    }

    public MbXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public MbXMLException(String message) {
        super(message);
    }

    public MbXMLException(Throwable cause) {
        super(cause);
    }
}
