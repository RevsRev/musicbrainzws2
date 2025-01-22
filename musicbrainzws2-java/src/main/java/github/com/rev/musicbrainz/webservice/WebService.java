package github.com.rev.musicbrainz.webservice;

import github.com.rev.musicbrainz.wsxml.MbXMLException;
import github.com.rev.musicbrainz.wsxml.element.Metadata;

import java.util.List;
import java.util.Map;

/**
 * An interface all concrete web service classes have to implement.
 */
public interface WebService {

    /**
     * <p>Queries the web service.</p>
     *
     * <p>Using this method, you can either get a resource by id (using
     * the <code>id</code> parameter, or perform a query on all
     * resources of a type.</p>
     *
     * <p>The <code>filter</code> and the <code>id</code> parameter exclude
     * each other. If you are using a filter, you may not set
     * <code>id</code> and vice versa.</p>
     *
     * <p>Returns a populated {@link Metadata} object containing the result.</p>
     *
     * @param entity        a string containing the entity's name
     * @param id            a string containing a UUID, or the empty string
     * @param includeParams a list containing values for the 'inc' parameter
     * @param filterParams  parameters, depending on the entity
     * @return A populated {@link Metadata} object
     * @throws WebServiceException A web service exception
     * @throws MbXMLException      A parser exception
     */
    Metadata get(String entity, String id, List<String> includeParams, Map<String, String> filterParams)
            throws WebServiceException, MbXMLException;

    /**
     * Submit data to the web service.
     *
     * @param a Metada Object
     */
    Metadata post(Metadata metadata)
            throws WebServiceException, MbXMLException;

    /**
     * Put data to the web service.
     *
     * @param entity A string containing the entity's name
     * @param id     A string containing a UUID, or the empty string
     * @param data   A List containing the data to put, directly in
     *               the URL (i.e. collection releases).
     */
    Metadata put(String entity, String id, List<String> data)
            throws WebServiceException, MbXMLException;

    /**
     * Delete data from the web service.
     *
     * @param entity A string containing the entity's name
     * @param id     A string containing a UUID, or the empty string
     * @param data   A List containing the data to delete, directly in
     *               the URL (i.e. collection releases).
     */
    Metadata delete(String entity, String id, List<String> data)
            throws WebServiceException, MbXMLException;

    void setUsername(String username);

    void setPassword(String password);

    void setClient(String client);
}