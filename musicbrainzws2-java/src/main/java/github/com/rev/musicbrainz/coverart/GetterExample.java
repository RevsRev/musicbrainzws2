/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package github.com.rev.musicbrainz.coverart;

import github.com.rev.fm.last.musicbrainz.coverart.CoverArt;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import github.com.rev.fm.last.musicbrainz.coverart.CoverArtImage;
import github.com.rev.fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import java.io.File;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author marco
 */
public class GetterExample {
    
     public static void main(String[] args){
    
        CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();
        UUID mbid = UUID.fromString("7f6d3088-837d-495e-905f-be5c70ac2d82");

        CoverArt coverArt = null;
        
        try {
          coverArt = client.getByMbid(mbid);         
          if (coverArt != null) {
            for (CoverArtImage coverArtImage : coverArt.getImages()) {
              
                Image image = new Image(coverArtImage);
                System.out.print(image.getType()+"\n");
                
                coverArtImage.getImage().read();
                
                //File output = new File(mbid.toString() + "_" + coverArtImage.getId() + ".jpg");
                //FileUtils.copyInputStreamToFile(coverArtImage.getImage(), output);
            }
          }
        } catch (Exception e) {
          // ...
        }
    }
}
