package com.git.broker.impl.domain;

import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.domain.api.IConnection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link IResponse} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 11/28/12
 * Time: 5:03 PM
 */
public class Response implements IResponse {

    private String correlationId;

    private ResponseType type;

    private String message;

    private IConnection connection;

    private Map<String, String> properties = new HashMap();

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
    public ResponseType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(ResponseType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
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
            .append("type", type)
            .append("message", message)
            .append("properties", properties)
            .toString();
    }
}
