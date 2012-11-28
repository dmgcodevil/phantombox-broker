package com.git.broker.impl.service.producer;

import static com.git.broker.api.domain.Constants.CLASS_NAME_PROPERTY;
import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import static com.git.broker.api.domain.Constants.CALL_REQUEST_QUEUE;
import static com.git.broker.api.domain.Constants.CALL_RESPONSE_QUEUE;
import com.git.broker.api.domain.ICall;
import com.git.broker.api.domain.IRequestCallbackAction;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.api.service.producer.ICallProducerService;
import com.git.broker.impl.domain.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
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

    private IRequestCallbackAction callbackAction;

    private static final Logger lOGGER = Logger.getLogger(CallProducerService.class);

    private static final int RECEIVE_TIMEOUT = 10000;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendObject(final ICall call) {
        if (call == null) throw new IllegalArgumentException("Object for sending can't be null.");
        String msg = marshallerService.marshall(call);
        String correlationId = UUID.randomUUID().toString();
        Map<String, String> properties = new HashMap<>();
        properties.put(CLASS_NAME_PROPERTY, call.getClass().getName());
        properties.put(CONTACT_ID_PROPERTY, call.getContactId());
        MessageCreator messageCreator = MessageCreatorFactory.create(msg, correlationId, properties);
        jmsTemplate.send(CALL_REQUEST_QUEUE, messageCreator);
        jmsTemplate.setReceiveTimeout(RECEIVE_TIMEOUT);
        Message message = jmsTemplate.receiveSelected(CALL_RESPONSE_QUEUE, buildSelector(correlationId));
        if (message instanceof TextMessage) {
            try {
                final TextMessage tm = (TextMessage) message;
                IResponse response = marshallerService.unmarshall(tm.getText(), Response.class);
                System.out.print("RESPONSE: " + response.getType());
            } catch (JMSException e) {
                lOGGER.error(ExceptionUtils.getMessage(e));
            }
        }
    }

    private String buildSelector(String correlationId) {
        String selector = MessageFormat.format("JMSCorrelationID=''{0}''",
            (StringUtils.isNotEmpty(correlationId) ? correlationId : StringUtils.EMPTY));
        return selector;
    }
}
