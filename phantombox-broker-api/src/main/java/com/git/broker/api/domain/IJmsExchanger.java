package com.git.broker.api.domain;

import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;

import javax.swing.JFrame;

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
     * Gets contacts.
     *
     * @return {@link com.git.domain.api.IContact}
     */
    public IContact getContact();

    /**
     * Sets contacts.
     *
     * @param contact {@link IContact}
     */
    public void setContact(IContact contact);

    /**
     * Call.
     *
     * @param subscriberName subscriber name
     * @param contactId      contact id
     */
    void call(String subscriberName, String contactId);

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
     * Create incoming call window.
     *
     * @param subscriberName subscriber name
     * @param correlationId  correlation id
     * @param jmsExchanger   jmsExchanger
     * @return frame
     */
    JFrame createIncomingCallFrame(String subscriberName, String correlationId, IJmsExchanger jmsExchanger);

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
     * Cancel.
     */
    void cancel();

}
