package com.git.broker.api.domain;

import com.git.domain.api.IConnection;

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
     * Gets subscriber name.
     *
     * @return correlation id
     */
    String getSubscriberName();

    /**
     * Sets subscriber name.
     *
     * @param subscriberName subscriber name
     */
    void setSubscriberName(String subscriberName);

    /**
     * Get connection.
     *
     * @return {@link IConnection}
     */
    IConnection getConnection();

    /**
     * Set connection.
     *
     * @param connection {@link IConnection}
     */
    void setConnection(IConnection connection);


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
