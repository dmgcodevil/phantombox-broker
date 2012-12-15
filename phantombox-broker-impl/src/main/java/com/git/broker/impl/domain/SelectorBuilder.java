package com.git.broker.impl.domain;

import com.git.broker.api.domain.ISelector;
import com.git.broker.api.domain.ISelectorBuilder;
import com.git.broker.api.domain.SelectorCondition;

/**
 * SelectorBuilder.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 5:38 PM
 */
public class SelectorBuilder implements ISelectorBuilder {

    private StringBuilder selectorBuilder = new StringBuilder();
    private static final String SPACE = " ";

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder createSelector() {
        selectorBuilder = new StringBuilder();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder createSelector(String selector) {
        selectorBuilder = new StringBuilder(selector);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder addProperty(String property) {
        selectorBuilder.append(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder addPropertyValue(String propertyValue) {
        selectorBuilder.append("'");
        selectorBuilder.append(propertyValue);
        selectorBuilder.append("'");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder addCondition(SelectorCondition condition) {
        selectorBuilder.append(condition.getValue());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelectorBuilder addFullCondition(String property, SelectorCondition condition, String propertyValue) {
        addProperty(property);
        addCondition(condition);
        addPropertyValue(propertyValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelector buildSelector() {
        return new Selector(selectorBuilder.toString());
    }
}
