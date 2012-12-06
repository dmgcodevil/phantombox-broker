package com.git.broker.api.service.consumer;

import com.git.broker.api.domain.IResponse;

import javax.jms.MessageListener;

/**
 * Consumer service.
 * <p/>
 * Date: 28.11.12
 * Time: 11:03
 *
 * @author rpleshkov
 */
public interface IConsumerService extends MessageListener {

    /**
     * Sends response.
     *
     * @param response {@link IResponse}
     */
    void sendResponse(IResponse response);

    String getMessageSelector();
}
