package github.com.rev.musicbrainz.model.entity;


import github.com.rev.musicbrainz.model.LifeSpanWs2;
import github.com.rev.musicbrainz.utils.MbUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

/**
 * <p>A Place definition.
 * A place is a building or outdoor area used for performing or
 * producing music.
 *
 * </p>
 */
public class PlaceWs2 extends EntityWs2 {
    private static final Logger log = Logger.getLogger(PlaceWs2.class.getName());

    private String typeUri;
    private String name;
    private String disambiguation;
    private String address;
    private String latitude;
    private String longitude;
    private AreaWs2 area;
    private LifeSpanWs2 lifespan;

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the area
     */
    public AreaWs2 getArea() {
        return area;
    }

    /**
     * @return the lifespan
     */
    public LifeSpanWs2 getLifespan() {
        return lifespan;
    }

    /**
     * @param type the typeUri to set
     */
    public void setTypeUri(String typeUri) {
        this.typeUri = typeUri;
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
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @param area the area to set
     */
    public void setArea(AreaWs2 area) {
        this.area = area;
    }

    /**
     * @param lifespan the lifespan to set
     */
    public void setLifespan(LifeSpanWs2 lifespan) {
        this.lifespan = lifespan;
    }

    public String getFullAddress() {
        String out = name;
        if (getAddress() != null && !getAddress().isEmpty()) {
            out = out + " (" + getAddress();
        }

        if (getArea() == null) {
            return out + ")";
        }

        String complete = getArea().getCompleteString();

        if (complete.isEmpty()) {
            return out + ")";
        }
        out = out + ", " + complete + ")";

        return out;
    }

    public String getUniqueName() {
        if (StringUtils.isNotBlank(disambiguation)) {
            return name + " (" + disambiguation + ")";
        }
        return name;
    }

    @Override
    public String toString() {
        return getFullAddress();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlaceWs2 other)) {
            return false;
        }
        return this.getIdUri().equals(other.getIdUri());
    }
}