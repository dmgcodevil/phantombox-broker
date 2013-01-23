package com.git.broker.api.domain;

import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;

/**
 * JmsExchanger interface.
 * <p/>
 * Date: 05.12.12
 * Time: 18:52
 *
 * @author rpleshkov
 */
public interface IJmsExchanger {

    /**
     * Sets contact.
     *
     * @param contact contact
     */
    public void setContact(IContact contact);

    /**
     * Gets contact.
     *
     * @return contact
     */
    public IContact getContact();

    /**
     * Gets selector.
     *
     * @return ISelector
     */
    public ISelector getSelector();

    /**
     * Sets selector.
     *
     * @param selector selector
     */
    public void setSelector(ISelector selector);

    /**
     * Call with creating call frame.
     *
     * @param subscriber subscriber
     * @param receiver   receiver
     */
    void call(IContact subscriber, IContact receiver);

    /**
     * Incoming call.
     *
     * @param request {@link IRequest}
     */
    void incomingCall(IRequest request);

    /**
     * Reply.
     *
     * @param response {@link IResponse}
     */
    void reply(IResponse response);


    /**
     * Answer.
     *
     * @param responseType  {@link ResponseType}
     * @param correlationId correlation id
     */
    void answer(ResponseType responseType, String correlationId);

    /**
     * Broadcast.
     *
     * @param connection {@link IConnection}
     */
    void broadcast(IConnection connection);

    /**
     * Listen.
     *
     * @param connection {@link IConnection}
     */
    void listen(IConnection connection);

    /**
     * Cancel call.
     *
     * @param request {@link IRequest}
     */
    void cancelCall(IRequest request);

    /**
     * Stop call.
     *
     * @param connection {@link IConnection}
     * @param receiver   {@link IContact}
     */
    void stopCall(IConnection connection, IContact receiver);

    /**
     * Will invoke then subscriber stop the call.
     *
     * @param connection {@link IConnection}
     */
    void onCallReject(IConnection connection);

}
