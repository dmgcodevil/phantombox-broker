package com.git.broker.api.domain;

/**
 * Call fame interface.
 * <p/>
 * Date: 14.12.12
 * Time: 17:07
 *
 * @author rpleshkov
 */
public interface ICallFame {

    /**
     * Sets Request.
     *
     * @param request {@link IRequest}
     */
    void setRequest(IRequest request);

    /**
     * Gets Request.
     *
     * @return {@link IRequest}
     */
    IRequest getRequest();

    /**
     * Sets jms exchanger.
     *
     * @param jmsExchanger {@link IJmsExchanger}
     */
    void setJmsExchanger(IJmsExchanger jmsExchanger);

    /**
     * Gets jms exchanger.
     *
     * @return {@link IJmsExchanger}
     */
    IJmsExchanger getJmsExchanger();

    /**
     * Sets contact name.
     *
     * @param contactName contact name
     */
    void setContactName(String contactName);

    /**
     * Gets contact name.
     *
     * @return contact name
     */
    String getContactName();

    /**
     * Show.
     */
    void showFrame();

    /**
     * Close.
     */
    void close();

}
