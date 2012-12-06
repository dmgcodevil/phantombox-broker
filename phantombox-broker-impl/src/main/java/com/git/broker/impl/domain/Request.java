package com.git.broker.impl.domain;

import com.git.broker.api.domain.IRequest;
import com.git.domain.api.IConnection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link IRequest} interface implementation.
 * <p/>
 * Date: 29.11.12
 * Time: 12:34
 *
 * @author rpleshkov
 */
public class Request implements IRequest {

    private String correlationId;

    private String subscriberName;

    private Map<String, String> properties = new HashMap();

    private IConnection connection;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubscriberName() {
        return subscriberName;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IConnection getConnection() {
        return connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnection(IConnection connection) {
        this.connection = connection;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("correlationId", correlationId)
            .append("subscriberName", subscriberName)
            .append("properties", properties)
            .toString();
    }
}
