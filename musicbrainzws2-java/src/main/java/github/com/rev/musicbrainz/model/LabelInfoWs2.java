package github.com.rev.musicbrainz.model;

import github.com.rev.musicbrainz.model.entity.LabelWs2;

import java.util.logging.Logger;

/**
 * <p>A single Element (label and catalog)
 * of an LabelInfoList
 */
public class LabelInfoWs2 {
    private static final Logger log = Logger.getLogger(LabelInfoWs2.class.getName());

    private String catalogNumber;
    private LabelWs2 label;

    public LabelInfoWs2() {
    }

    /**
     * Minimal Constructor
     *
     * @param label         the Label
     * @param catalogNumber the catalogNumber
     */
    public LabelInfoWs2(LabelWs2 label, String catalogNumber) {
        this.label = label;
        this.catalogNumber = catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public LabelWs2 getLabel() {
        return label;
    }

    public void setLabel(LabelWs2 label) {
        this.label = label;
    }

    public String getLabelName() {
        if (label == null) {
            return "";
        }
        return label.getName();
    }

    public String getLabelInfoString() {

        String labelInfoString = "";

        if (label != null) {
            labelInfoString = label.getName();
        }

        if (catalogNumber != null && !catalogNumber.equals("")) {
            if (!(labelInfoString == null) && !(labelInfoString.equals(""))) {
                labelInfoString = labelInfoString + " ";
            }

            labelInfoString = labelInfoString + "cat.No: " + catalogNumber;
        }
        return labelInfoString;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return getLabelInfoString();
    }


}