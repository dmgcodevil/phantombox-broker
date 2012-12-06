package com.git.broker.api.domain;

import com.git.domain.api.IConnection;

import javax.swing.*;

/**
 * Mediator interface.
 * <p/>
 * Date: 05.12.12
 * Time: 18:52
 *
 * @author rpleshkov
 */
public interface IMediator {

    /**
     * Call request.
     *
     * @param subscriberName subscriber name
     * @param connection     connection
     * @param contactId      contact id
     */
    void callRequest(String subscriberName, String contactId, IConnection connection);

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
     * Show incoming call window.
     */
    JFrame createIncomingCallFrame(String subscriberName, IMediator mediator);

    /**
     * Answer.
     *
     * @param responseType {@link ResponseType}
     */
    void answer(ResponseType responseType, String correlationId, IConnection connection);

    /**
     * Accept on broadcast.
     */
    void acceptOnBroadcast(String ipAddress, int audioPort, int videoPort);

    /**
     * Accept on listen.
     */
    void acceptOnListen(String ipAddress, int audioPort, int videoPort);

    /**
     * Cancel.
     */
    void cancel();
}
