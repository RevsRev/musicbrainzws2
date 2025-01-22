package github.com.rev.musicbrainz.model.searchresult;

import github.com.rev.musicbrainz.model.entity.ReleaseGroupWs2;


/**
 * Represents an release result.
 */
public class ReleaseGroupResultWs2 extends SearchResultWs2 {

    /**
     * @return the releaseGroup
     */
    public ReleaseGroupWs2 getReleaseGroup() {
        return (ReleaseGroupWs2) super.getEntity();
    }

    /**
     * @param releaseGroup the releaseGroup to set
     */
    public void setReleaseGroup(ReleaseGroupWs2 releaseGroup) {
        super.setEntity(releaseGroup);
    }

}
