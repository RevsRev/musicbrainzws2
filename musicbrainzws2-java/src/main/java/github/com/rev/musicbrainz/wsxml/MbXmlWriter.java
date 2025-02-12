package github.com.rev.musicbrainz.wsxml;

import github.com.rev.musicbrainz.webservice.DefaultWebServiceWs2;
import github.com.rev.musicbrainz.wsxml.element.Metadata;
import github.com.rev.musicbrainz.wsxml.impl.JDOMWriterWs2;


/**
 * A simple writer interface to built XML body of the MusicBrainz's
 * web service post reqests. The {@link DefaultWebServiceWs2} class uses
 * {@link JDOMWriterWs2} as a default {@link MbXmlWriter} implementation.
 * However, you can write your own implementation and inject it to the
 * {@link DefaultWebServiceWs2} using particolar constructor.
 */
public interface MbXmlWriter {

    /**
     * Parses a populated {@link Metadata} object and bulid
     * the XML string to use as the body of a POST request.
     *
     * @param metadata the Metadata Object.
     * @return an xml String.
     * @throws MbXMLException A {@link MbXMLWriteException} is thrown
     *                        if the xml could not be writed. In all other exceptional cases
     *                        a general {@link MbXMLException} is thrown.
     */
    String getXmlString(Metadata metadata) throws MbXMLException;
}
