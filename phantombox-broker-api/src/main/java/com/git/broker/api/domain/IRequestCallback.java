package com.git.broker.api.domain;

/**
 * Request callback.
 * <p/>
 * User: dmgcodevil
 * Date: 11/28/12
 * Time: 5:06 PM
 */
public interface IRequestCallback {

    /**
     * Request.
     *
     * @param request          {@link IRequest}
     * @param responseCallback response callback
     */
    void request(IRequest request, IResponseCallback responseCallback);

    /**
     * Request.
     *
     * @param request  {@link IRequest}
     * @param jmsExchanger {@link IJmsExchanger}
     */
    void request(IRequest request, IJmsExchanger jmsExchanger);

}
