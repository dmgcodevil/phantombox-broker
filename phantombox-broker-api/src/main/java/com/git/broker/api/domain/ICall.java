package com.git.broker.api.domain;

/**
 * Call interface.
 * <p/>
 * Date: 27.11.12
 * Time: 14:51
 *
 * @author rpleshkov
 */
public interface ICall {

    /**
     * Gets contact id.
     *
     * @return contact id
     */
    String getContactId();

    /**
     * Sets contact id.
     *
     * @param contactId contact id
     */
    void setContactId(String contactId);
}
