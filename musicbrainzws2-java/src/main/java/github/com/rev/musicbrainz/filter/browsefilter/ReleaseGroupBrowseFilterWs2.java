package github.com.rev.musicbrainz.filter.browsefilter;

import github.com.rev.musicbrainz.filter.ReleaseTypeFilterWs2;

import java.util.Map;

public class ReleaseGroupBrowseFilterWs2 extends BrowseFilterWs2 {
    private ReleaseTypeFilterWs2 releaseTypeFilter = new ReleaseTypeFilterWs2();

    public ReleaseGroupBrowseFilterWs2() {
        super();
    }

    @Override
    public Map<String, String> createParameters() {
        Map<String, String> map = super.createParameters();

        //Combine default filter's parameters with release filter's
        map.putAll(getReleaseTypeFilter().createParameters());

        return map;
    }

    /**
     * @return the releaseTypeFilter
     */
    public ReleaseTypeFilterWs2 getReleaseTypeFilter() {
        return releaseTypeFilter;
    }

    /**
     * @param releaseTypeFilter the releaseTypeFilter to set
     */
    public void setReleaseTypeFilter(ReleaseTypeFilterWs2 releaseTypeFilter) {
        this.releaseTypeFilter = releaseTypeFilter;
    }

}