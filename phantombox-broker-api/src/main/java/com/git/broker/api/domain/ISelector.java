package com.git.broker.api.domain;

/**
 * Selector interface.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 5:30 PM
 */
public interface ISelector {

    /**
     * Gets value.
     *
     * @return value
     */
    String getValue();

    /**
     * Gets value.
     *
     * @param value value
     */
    void setValue(String value);
}
