package github.com.rev.musicbrainz.model.entity;


import github.com.rev.musicbrainz.utils.MbUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;


/**
 * <p>A Instrument definition.
 * A series is a sequence of separate release groups, releases, recordings
 * or works with a common theme. The theme is usually prominent in the
 * branding of the entities in the series and the individual entities will
 * often have been given a number indicating the position in the series.
 * <p>
 * Examples
 * <p>
 * Now That's What I Call Music (UK edition)
 * Blue Note Commemorative 75th Anniversary Series
 * Bach-Werke-Verzeichnis
 *
 * </p>
 */
public class SeriesWs2 extends EntityWs2 {
    private static final Logger log = Logger.getLogger(SeriesWs2.class.getName());

    //Actualy valid values for Type:

    public static final String TYPE_RELEASEGROUP = "Release group";
    public static final String TYPE_RELEASE = "Release";
    public static final String TYPE_RECORDING = "Recording";
    public static final String TYPE_WORK = "Work";
    public static final String TYPE_CATALOGUE = "Catalogue";

    private String typeUri;
    private String name;
    private String disambiguation;
    private String orderingAttribute;

    /**
     * @return the typeUri
     */
    public String getTypeUri() {
        return typeUri;
    }

    public String getType() {

        if (getTypeUri() == null) {
            return "";
        }
        if (getTypeUri().isEmpty()) {
            return "";
        }
        return MbUtils.extractTypeFromURI(getTypeUri());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the disambiguation
     */
    public String getDisambiguation() {
        return disambiguation;
    }

    /**
     * @return the orderingAttribute
     */
    public String getOrderingAttribute() {
        return orderingAttribute;
    }

    /**
     * @param type the typeUri to set
     */
    public void setTypeUri(String type) {
        this.typeUri = type;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param disambiguation the disambiguation to set
     */
    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    /**
     * @param orderingAttribute the orderingAttribute to set
     */
    public void setOrderingAttribute(String orderingAttribute) {
        this.orderingAttribute = orderingAttribute;
    }

    public String getUniqueName() {
        if (StringUtils.isNotBlank(disambiguation)) {
            return name + " (" + disambiguation + ")";
        }
        return name;
    }

    @Override
    public String toString() {
        return getUniqueName();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SeriesWs2 other)) {
            return false;
        }
        return this.getIdUri().equals(other.getIdUri());
    }
}