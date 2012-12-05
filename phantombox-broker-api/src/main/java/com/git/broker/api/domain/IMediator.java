package com.git.broker.api.domain;

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
     * @param request {@link IRequest}
     */
    void callRequest(IRequest request);

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
     */
    void answer();

    /**
     * Cancel.
     */
    void cancel();
}
