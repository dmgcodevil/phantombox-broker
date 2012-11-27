package com.git.broker.impl.domain;

import com.git.broker.api.domain.ICall;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * {@link ICall} interface implementation.
 * <p/>
 * Date: 27.11.12
 * Time: 14:58
 *
 * @author rpleshkov
 */
public class Call implements ICall {

    private String contactId;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContactId() {
        return contactId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Call)) {
            return false;
        }

        Call that = (Call) o;
        return new EqualsBuilder()
            .append(contactId, that.contactId)
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(contactId)
            .toHashCode();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("contactId", contactId)
            .toString();
    }
}
