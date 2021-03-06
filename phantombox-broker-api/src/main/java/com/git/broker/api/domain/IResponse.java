package com.git.broker.api.domain;

import com.git.domain.api.IContact;

import java.util.Map;

/**
 * Response.
 * <p/>
 * Date: 28.11.12
 * Time: 21:06
 *
 * @author rpleshkov
 */
public interface IResponse {

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
     * Gets type.
     *
     * @return {@link ResponseType}
     */
    ResponseType getType();

    /**
     * Sets type.
     *
     * @param type {@link ResponseType}
     */
    void setType(ResponseType type);

    /**
     * Gets message.
     *
     * @return message
     */
    String getMessage();

    /**
     * Sets message.
     *
     * @param message message
     */
    void setMessage(String message);


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
     * Gets properties.
     *
     * @return properties
     */
    Map<String, String> getProperties();

    /**
     * Sets properties.
     *
     * @param properties properties
     */
    void setProperties(Map<String, String> properties);
}
