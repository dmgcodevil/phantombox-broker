package com.git.broker.api.domain;

/**
 * Selector builder interface.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 5:26 PM
 */
public interface ISelectorBuilder {

    /**
     * Create selector.
     *
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder createSelector();

    /**
     * Create selector.
     *
     * @param selector selector as string
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder createSelector(String selector);

    /**
     * Add property.
     *
     * @param property property
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder addProperty(String property);

    /**
     * Add property value.
     *
     * @param propertyValue property value
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder addPropertyValue(String propertyValue);

    /**
     * Add condition.
     *
     * @param condition condition
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder addCondition(SelectorCondition condition);

    /**
     * Add full condition.
     *
     * @param property      property
     * @param condition     condition
     * @param propertyValue property value
     * @return {@link ISelectorBuilder}
     */
    ISelectorBuilder addFullCondition(String property, SelectorCondition condition,
                                      String propertyValue);

    /**
     * Builds selector.
     *
     * @return {@link ISelector}
     */
    ISelector buildSelector();
}
