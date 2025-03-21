package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.ReleaseListWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


public class ReleaseSearchResultsWs2 extends ListElement {


    protected List<ReleaseResultWs2> releaseResults = new ArrayList<ReleaseResultWs2>();
    private ReleaseListWs2 releaseList = new ReleaseListWs2();

    /**
     * Convenience method to adds an release result to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #releaseResults} is null.
     *
     * @param releaseResult The release result to add
     */

    public void addReleaseResult(ReleaseResultWs2 releaseResult) {
        if (releaseResults == null) {
            releaseResults = new ArrayList<ReleaseResultWs2>();
        }
        if (getReleaseList() == null) {
            releaseList = new ReleaseListWs2();
        }
        releaseResults.add(releaseResult);
        releaseList.addRelease(releaseResult.getRelease());

        releaseList.setCount(getCount());
        releaseList.setOffset(getOffset());
    }

    /**
     * @return the release results
     */
    public List<ReleaseResultWs2> getReleaseResults() {
        return releaseResults;
    }

    /**
     * @return the releaseList
     */
    public ReleaseListWs2 getReleaseList() {
        return releaseList;
    }
}
