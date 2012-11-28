package com.git.broker.impl.service.producer;

import static com.git.broker.api.domain.Constants.CLASS_NAME_PROPERTY;
import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.ICall;
import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.api.service.producer.ICallProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * {@link ICallProducerService} interface implementation..
 * <p/>
 * Date: 27.11.12
 * Time: 19:03
 *
 * @author rpleshkov
 */
@Service("callProducerService")
public class CallProducerService implements ICallProducerService<ICall> {

    @Autowired
    @Qualifier("jettisonMarshallerService")
    private IMarshallerService marshallerService;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final Logger lOGGER = Logger.getLogger(CallProducerService.class);

    private static final int RECEIVE_TIMEOUT = 10000;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendObject(final ICall call) {
        String queue = "call.queue";
        if (call == null) throw new IllegalArgumentException("Object for sending can't be null.");
        String msg = marshallerService.marshall(call);
        String correlationId = UUID.randomUUID().toString();
        Map<String, String> properties = new HashMap<>();
        properties.put(CLASS_NAME_PROPERTY, call.getClass().getName());
        properties.put(CONTACT_ID_PROPERTY, call.getContactId());
        MessageCreator messageCreator = MessageCreatorFactory.create(msg, correlationId, properties);
        jmsTemplate.send(queue, messageCreator);
        jmsTemplate.setReceiveTimeout(RECEIVE_TIMEOUT);
        Message message = jmsTemplate.receiveSelected("call.queue.response", "JMSCorrelationID='" + correlationId + "'");
        if (message instanceof TextMessage) {
            try {
                System.out.print(((TextMessage) message).getText());
            } catch (JMSException e) {
                lOGGER.error(ExceptionUtils.getMessage(e));
            }
        }
    }

}
