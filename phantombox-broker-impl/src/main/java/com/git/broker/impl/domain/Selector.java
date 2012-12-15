package com.git.broker.impl.domain;

import com.git.broker.api.domain.ISelector;

/**
 * Selector.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 5:43 PM
 */
public class Selector implements ISelector {

    private String value;

    /**
     * Constructor with parameter.
     *
     * @param value value
     */
    public Selector(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
