package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.ReleaseGroupListWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


public class ReleaseGroupSearchResultsWs2 extends ListElement {


    protected List<ReleaseGroupResultWs2> releaseGroupResults = new ArrayList<ReleaseGroupResultWs2>();
    private ReleaseGroupListWs2 releaseGroupList = new ReleaseGroupListWs2();

    /**
     * Convenience method to adds an release result to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #releaseGroupResults} is null.
     *
     * @param releaseResult The release result to add
     */

    public void addReleaseGroupResult(ReleaseGroupResultWs2 releaseGroupResult) {
        if (releaseGroupResults == null) {
            releaseGroupResults = new ArrayList<ReleaseGroupResultWs2>();
        }
        if (getReleaseGroupList() == null) {
            releaseGroupList = new ReleaseGroupListWs2();
        }
        releaseGroupResults.add(releaseGroupResult);
        releaseGroupList.addReleaseGroup(releaseGroupResult.getReleaseGroup());

        releaseGroupList.setCount(getCount());
        releaseGroupList.setOffset(getOffset());
    }

    /**
     * @return the release results
     */
    public List<ReleaseGroupResultWs2> getReleaseGroupResults() {
        return releaseGroupResults;
    }

    /**
     * @return the releaseGroupList
     */
    public ReleaseGroupListWs2 getReleaseGroupList() {
        return releaseGroupList;
    }
}
