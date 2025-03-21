package github.com.rev.musicbrainz.webservice;

import github.com.rev.musicbrainz.exception.MBWS2Exception;

/**
 * A general web service exception
 */
public class WebServiceException extends MBWS2Exception {
    public WebServiceException() {
        super();
    }

    public WebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebServiceException(String message) {
        super(message);
    }

    public WebServiceException(Throwable cause) {
        super(cause);
    }
}
