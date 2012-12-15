package com.git.broker.api.domain;

/**
 * Selector condition.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 5:28 PM
 */
public enum SelectorCondition {

    EQUALS("=");

    /**
     * Constructor with parameters.
     *
     * @param value value
     */
    SelectorCondition(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets value.
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
