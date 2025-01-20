package github.com.rev.musicbrainz.model.searchresult;

import github.com.rev.musicbrainz.model.entity.SeriesWs2;

/**
 * Represents a Series result.
 */
public class SeriesResultWs2 extends SearchResultWs2 {
	
    /**
     * @return the Series
     */
    public SeriesWs2 getSeries() {
            return (SeriesWs2)super.getEntity();
    }

    /**
     * @param series the Series to set
     */
    public void setSeries(SeriesWs2 series) {
            super.setEntity(series);
    }
}
