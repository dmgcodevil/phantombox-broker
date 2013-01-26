package com.git.broker.api.domain;

import com.git.domain.api.IContact;

import java.util.Map;

/**
 * Request.
 * <p/>
 * Date: 29.11.12
 * Time: 12:24
 *
 * @author rpleshkov
 */
public interface IRequest {

    /**
     * Gets correlation id.
     *
     * @return correlation id
     */
    String getCorrelationId();

    /**
     * Sets correlation id.
     *
     * @param correlationId correlation id
     */
    void setCorrelationId(String correlationId);

    /**
     * Gets contact.
     *
     * @return contact
     */
    IContact getContact();

    /**
     * Sets contact.
     *
     * @param contact contact
     */
    void setContact(IContact contact);

    /**
     * Gets request type.
     *
     * @return RequestType
     */
    RequestType getRequestType();

    /**
     * Set request type.
     *
     * @param requestType {@link RequestType}
     */
    void setRequestType(RequestType requestType);

    /**
     * Gets properties.
     *
     * @return properties
     */
    Map<String, String> getProperties();

    /**
     * Sets properties.
     *
     * @param properties properties.
     */
    void setProperties(Map<String, String> properties);

}
