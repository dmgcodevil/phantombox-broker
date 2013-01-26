package com.git.broker.impl.domain;

import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.domain.api.IContact;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    private IContact contact;

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
    public IContact getContact() {
        return contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContact(IContact contact) {
        this.contact = contact;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Response)) {
            return false;
        }

        Response that = (Response) o;
        return new EqualsBuilder()
            .append(correlationId, that.correlationId)
            .append(contact, that.contact)
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(correlationId)
            .append(contact)
            .toHashCode();
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
            .append("contact", contact)
            .toString();
    }
}
