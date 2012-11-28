package com.git.broker.api.service.consumer;

import com.git.broker.api.domain.IResponseCallbackAction;

import java.util.Map;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Consumer service.
 * <p/>
 * Date: 28.11.12
 * Time: 11:03
 *
 * @author rpleshkov
 */
public interface ICallConsumerService extends MessageListener {

    IResponseCallbackAction getResponseCallbackAction();

    void setResponseCallbackAction(IResponseCallbackAction responseCallbackAction);
}
