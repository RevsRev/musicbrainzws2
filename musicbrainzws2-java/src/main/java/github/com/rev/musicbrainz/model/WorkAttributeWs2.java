package github.com.rev.musicbrainz.model;


import java.util.logging.Logger;

/**
 * <p>A Work Attribute definition.
 * Added 15-9-2014
 * Actualy not defined in any documentation, but present in some work as an
 * attribute "type" with a value that seems to be the attribute's type and a
 * value.
 * e.g. <attribute type="Key">C major</attribute>
 *
 * </p>
 */
public class WorkAttributeWs2 {
    private static final Logger log = Logger.getLogger(WorkAttributeWs2.class.getName());

    private final String type;
    private final String value;

    /**
     * Minimal Constructor
     */
    public WorkAttributeWs2(String type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}