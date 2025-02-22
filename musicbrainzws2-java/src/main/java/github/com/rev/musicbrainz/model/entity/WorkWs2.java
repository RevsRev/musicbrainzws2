package github.com.rev.musicbrainz.model.entity;

import github.com.rev.musicbrainz.model.ArtistCreditWs2;
import github.com.rev.musicbrainz.model.RelationWs2;
import github.com.rev.musicbrainz.model.WorkAttributeWs2;
import github.com.rev.musicbrainz.utils.MbUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Rapresents a Work
 */
public class WorkWs2 extends EntityWs2 {
    // TODO: Should we add the typeUri URI definitions?
    // Seems they are work in progress...

    //public static final String TYPE_ARIA = NS_MMD_2 + "Aria";
    //public static final String TYPE_BALLET = NS_MMD_2 + "Ballet";
    //public static final String TYPE_CANTATA = NS_MMD_2 + "Cantata";
    //...

    private String typeUri;
    private String title;
    private ArtistCreditWs2 artistCredit;
    private String iswc;
    private List<String> iswcList = new ArrayList<String>();
    private String disambiguation;
    private String textLanguage;
    //15/09/2014
    private List<WorkAttributeWs2> attributes = new ArrayList<WorkAttributeWs2>();

    public String getTypeUri() {
        return typeUri;
    }

    public void setTypeUri(String typeUri) {
        this.typeUri = typeUri;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextLanguage() {
        return textLanguage;
    }

    public void setTextLanguage(String textLanguage) {
        this.textLanguage = textLanguage;
    }

    public ArtistCreditWs2 getArtistCredit() {
        return artistCredit;
    }

    public void setArtistCredit(ArtistCreditWs2 artistCredit) {
        this.artistCredit = artistCredit;
    }

    public String getIswc() {
        return iswc;
    }

    public void setIswc(String iswc) {
        this.iswc = iswc;
    }

    /**
     * @return the iswcList
     */
    public List<String> getIswcList() {
        return iswcList;
    }

    /**
     * @param iswcList the iswcList to set
     */
    public void setIswcList(List<String> iswcList) {
        this.iswcList = iswcList;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public String getUniqueTitle() {
        if (StringUtils.isNotBlank(disambiguation)) {
            return title + " (" + disambiguation + ")";
        }
        return title;
    }

    public String getWritersString() {
        if (getRelationList() == null) {
            return "";
        }
        if (getRelationList().getRelations().isEmpty()) {
            return "";
        }

        List<String> writers = new ArrayList<String>();
        for (Iterator<RelationWs2> i = getRelationList().getRelations().iterator(); i.hasNext(); ) {
            RelationWs2 r = i.next();
            if (!r.getTargetType().equals(RelationWs2.TO_ARTIST)) {
                continue;
            }

            if (((ArtistWs2) r.getTarget()).getUniqueName().isEmpty()) {
                continue;
            }
            if (writers.contains(((ArtistWs2) r.getTarget()).getUniqueName())) {
                continue;
            }

            writers.add(((ArtistWs2) r.getTarget()).getUniqueName());

        }
        String out = Arrays.toString(writers.toArray()).trim();
        out = out.substring(1);
        out = out.substring(0, out.length() - 1).trim();

        return out;
    }

    public String getArtistCreditString() {
        if (artistCredit != null) {
            return artistCredit.getArtistCreditString();
        }
        return "";
    }

    public String getBy() {
        if (!getArtistCreditString().isEmpty()) {
            return getArtistCreditString();
        }
        return getWritersString();

    }

    /**
     * @return the attributeList
     */
    public List<WorkAttributeWs2> getAttributes() {
        return attributes;
    }

    /**
     * @param attributeList the attributeList to set
     */
    public void setAttributes(List<WorkAttributeWs2> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return getUniqueTitle() + " by " + getBy();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WorkWs2 other)) {
            return false;
        }
        return this.getIdUri().equals(other.getIdUri());
    }
}
