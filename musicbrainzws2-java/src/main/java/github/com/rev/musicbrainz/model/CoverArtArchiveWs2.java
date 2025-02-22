package github.com.rev.musicbrainz.model;


import github.com.rev.musicbrainz.coverart.Image;
import github.com.rev.musicbrainz.coverart.ImageGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>A  Cover Art Archive definition.
 * <p>
 * ... nothing found in MB documentation.
 * see http://tickets.musicbrainz.org/browse/MBS-4536
 *
 * </p>
 */
public class CoverArtArchiveWs2 {
    private static final Logger log = Logger.getLogger(CoverArtArchiveWs2.class.getName());

    /**
     * @return the log
     */
    public static Logger getLog() {
        return log;
    }


    private boolean artwork = false;
    private boolean front = false;
    private boolean back = false;
    private int count = 0;
    private final String mbId;
    private List<Image> imageList = new ArrayList<Image>();

    public CoverArtArchiveWs2(boolean artwork, int count, boolean front, boolean back, String mbId) {
        this.artwork = artwork;
        this.front = front;
        this.back = back;
        this.count = count;
        this.mbId = mbId;

    }

    /**
     * @return the imageList
     */
    public List<Image> getImageList() {
        if (!imageList.isEmpty()) {
            return imageList;
        }
        if (artwork) {
            imageList = ImageGetter.getInstance().getImageListByMbID(mbId);
        }
        return imageList;
    }

    /**
     * @return the artwork
     */
    public boolean hasArtwork() {
        return artwork;
    }

    /**
     * @return the front
     */
    public boolean hasFront() {
        return front;
    }

    /**
     * @return the back
     */
    public boolean hasBack() {
        return back;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
}