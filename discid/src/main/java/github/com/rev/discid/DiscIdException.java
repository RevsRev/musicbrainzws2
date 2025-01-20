package github.com.rev.discid;

/* --------------------------------------------------------------------------

   GNU GENERAL PUBLIC LICENSE
   Version 3, 29 June 2007
*/

/*
 * Created on Nov 29, 2003
 */


/**
 * libshout: org.xiph.libshout.ShoutException
 *
 * @author andy
 */
public class DiscIdException extends Exception {

    private static final long serialVersionUID = 1L;

    public static final int SHOUTERR_SUCCESS=0;
    public static final int SHOUTERR_NOERROR=-1;

    /**
     * Constructor
     * @param message exception message
     */
    public DiscIdException(String message) {
        super(message);
    }

}
