package github.com.rev.musicbrainz.model.entity.listelement;

import github.com.rev.musicbrainz.model.entity.DiscWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * <p>A List of Disc Info (Disc & catalog number)referred by a release</p>
 */
public class DiscListWs2 extends ListElement {
    private static final Logger log = Logger.getLogger(DiscListWs2.class.getName());

    /**
     * A string containing the complete credit as join
     * of credit names in the list.
     */

    private final List<DiscWs2> discs
            = new ArrayList<DiscWs2>();

    /**
     * Minimal Constructor
     *
     * @param artistCreditElements A list of  ArtistCreditElementWs2
     *                             describing the Artist Credit.
     */
    public DiscListWs2(List<DiscWs2> discs) {
        if (discs != null) {
            for (DiscWs2 element : discs) {
                addDisc(element);
            }
        }
    }

    public List<DiscWs2> getDiscs() {
        return discs;
    }

    private void addDisc(DiscWs2 element) {

        discs.add(element);

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
}