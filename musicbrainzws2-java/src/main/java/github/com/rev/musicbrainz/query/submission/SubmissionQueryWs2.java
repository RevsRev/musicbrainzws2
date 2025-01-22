package github.com.rev.musicbrainz.query.submission;

import github.com.rev.musicbrainz.query.QueryWs2;
import github.com.rev.musicbrainz.webservice.WebService;
import github.com.rev.musicbrainz.webservice.WebServiceException;
import github.com.rev.musicbrainz.wsxml.MbXMLException;
import github.com.rev.musicbrainz.wsxml.element.Metadata;


public class SubmissionQueryWs2 extends QueryWs2 {


    public SubmissionQueryWs2() {
        super();
    }

    public SubmissionQueryWs2(WebService ws) {
        super(ws);
    }

    public Metadata post(Metadata md) throws WebServiceException, MbXMLException {

        return super.postToWebService(md);
    }

}
