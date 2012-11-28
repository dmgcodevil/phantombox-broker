package com.git.broker.impl.handler;

import com.git.broker.api.domain.ICall;
import com.git.broker.api.handler.ICallHandler;
import com.git.broker.api.service.marshaller.IMarshallerService;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Enter class description.
 * <p/>
 * Date: 28.11.12
 * Time: 13:12
 *
 * @author rpleshkov
 */
public class CallHandler implements ICallHandler {

    private static final Logger LOGGER = Logger.getLogger(CallHandler.class);

    private IMarshallerService marshallerService;

    @Override
    public void onMessage(Message message) {
        ICall call = null;
        BasicConfigurator.configure();
        try {
            Class clazz = message.getObjectProperty("className").getClass();
            if (message instanceof TextMessage) {
                //message.acknowledge();
                TextMessage tm = (TextMessage) message;
                String msgBody = tm.getText();
                LOGGER.info("message: " + msgBody);
                call = (ICall) marshallerService.unmarshall(msgBody, clazz);
                System.out.println(call);
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
