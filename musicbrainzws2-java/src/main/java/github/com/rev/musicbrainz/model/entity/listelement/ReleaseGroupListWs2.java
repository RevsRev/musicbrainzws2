package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.ReleaseGroupWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;


/**
 * A list of Release Groups
 */
public class ReleaseGroupListWs2 extends ListElement {

    private List<ReleaseGroupWs2> releaseGroups
            = new ArrayList<ReleaseGroupWs2>();

    /**
     * @return the releaseGroups
     */
    public List<ReleaseGroupWs2> getReleaseGroups() {
        return releaseGroups;
    }

    /**
     * @param releaseGroups the releaseGroups to set
     */
    public void setReleaseGroups(List<ReleaseGroupWs2> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    /**
     * Adds a releaseGroup to the list.
     * <p>
     * It will also create and set new ArrayList if
     * {@link #releaseGroups} is null.
     *
     * @param release The releaseGroup to add
     */
    public void addReleaseGroup(ReleaseGroupWs2 releaseGroup) {
        if (releaseGroups == null) {
            releaseGroups = new ArrayList<ReleaseGroupWs2>();
        }

        releaseGroups.add(releaseGroup);
    }

    public void addAllReleaseGroups(List<ReleaseGroupWs2> releaseGroupList) {
        if (releaseGroups == null) {
            releaseGroups = new ArrayList<ReleaseGroupWs2>();
        }

        releaseGroups.addAll(releaseGroupList);
    }
}
