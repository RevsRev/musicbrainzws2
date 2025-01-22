/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package github.com.rev.musicbrainz.coverart;

import github.com.rev.fm.last.musicbrainz.coverart.CoverArt;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArtImage;
import github.com.rev.fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author marco
 */
public class ImageGetter {

    private static ImageGetter _instance;
    private final CoverArtArchiveClient client;

    private ImageGetter() {
        client = new DefaultCoverArtArchiveClient();
    }

    public static ImageGetter getInstance() {

        if (_instance == null) {
            _instance = new ImageGetter();
        }

        return _instance;
    }

    public List<Image> getImageListByMbID(String id) {

        UUID mbid = UUID.fromString(id);

        CoverArt coverArt = null;
        List<Image> ImageList = new ArrayList<Image>(0);

        coverArt = client.getByMbid(mbid);
        if (coverArt != null) {
            for (CoverArtImage coverArtImage : coverArt.getImages()) {

                Image image = new Image(coverArtImage);
                ImageList.add(image);

            }
        }
        return ImageList;
    }
}
