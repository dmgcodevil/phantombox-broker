package com.git.broker.api.domain;

import com.git.domain.api.IConnection;

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
     * Call.
     *
     * @param request {@link IRequest}
     */
    void call(IRequest request);

    /**
     * Call without creating call frame.
     *
     * @param subscriberName subscriber name
     * @param connection     {@link IConnection}
     * @param contactId      contact id
     */
    void call(String subscriberName, IConnection connection, String contactId);


    /**
     * Call with creating call frame.
     *
     * @param subscriberName subscriber name
     * @param connection     {@link IConnection}
     * @param contactName    contact name
     * @param contactId      contact id
     */
    void call(String subscriberName, IConnection connection, String contactName, String contactId);

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
     * @param connection    {@link IConnection}
     * @param correlationId correlation id
     */
    void answer(ResponseType responseType, IConnection connection, String correlationId);

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

}
