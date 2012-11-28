package com.git.broker.impl.service.consumer;

import com.git.broker.api.domain.ICall;
import com.git.broker.api.service.consumer.ICallConsumerService;
import com.git.broker.api.service.marshaller.IMarshallerService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * {@link ICallConsumerService} interface implementation..
 * <p/>
 * Date: 28.11.12
 * Time: 11:05
 *
 * @author rpleshkov
 */
public class CallConsumerService implements ICallConsumerService {

    private static final Logger LOGGER = Logger.getLogger(CallConsumerService.class);

    @Autowired
    @Qualifier("jettisonMarshallerService")
    private IMarshallerService marshallerService;

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void onMessage(Message message) {
        ICall call = null;
        BasicConfigurator.configure();
        try {
            Class clazz = message.getObjectProperty("className").getClass();
            if (message instanceof TextMessage) {
                final TextMessage tm = (TextMessage) message;
                String msgBody = tm.getText();
                LOGGER.info("message: " + msgBody);
                call = (ICall) marshallerService.unmarshall(msgBody, clazz);
                System.out.println(call);
                jmsTemplate.send("call.queue.response", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage("Hi man!!!");
                        message.setJMSCorrelationID(tm.getJMSCorrelationID());
                        return message;
                    }
                });
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
