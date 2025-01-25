package com.github.rev.musicbrainz.client.http;

import java.util.Collection;

/**
 * Used to encapsulate all the parameters that can be made with a particular request.
 */
public interface MbParams {

    /**
     * @return The Parameters associated with this object.
     */
    Collection<MbParam> getParams();
}
