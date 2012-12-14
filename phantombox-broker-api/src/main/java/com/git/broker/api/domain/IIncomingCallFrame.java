package com.git.broker.api.domain;

/**
 * Incoming call frame interface.
 * <p/>
 * Date: 14.12.12
 * Time: 16:56
 *
 * @author rpleshkov
 */
public interface IIncomingCallFrame {

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
     * Show.
     */
    void showFrame();

    /**
     * Close.
     */
    void close();
}
