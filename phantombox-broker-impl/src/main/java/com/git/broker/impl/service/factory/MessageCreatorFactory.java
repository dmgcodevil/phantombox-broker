package com.git.broker.impl.service.factory;

import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.service.factory.IMessageCreatorFactory;
import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.impl.domain.Request;
import com.git.broker.impl.domain.Response;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * {@link IMessageCreatorFactory} interface implementation.
 * <p/>
 * Date: 29.11.12
 * Time: 16:39
 *
 * @author rpleshkov
 */
@Component("messageCreatorFactory")
public class MessageCreatorFactory implements IMessageCreatorFactory {

    @Autowired
    @Qualifier("jettisonMarshallerService")
    private IMarshallerService marshallerService;

    private static final Logger LOGGER = Logger.getLogger(MessageCreatorFactory.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageCreator create(IRequest request) {
        MessageCreator messageCreator = null;
        if (request != null) {
            //String correlationId = UUID.randomUUID().toString();
            //request.setCorrelationId(correlationId);
            String message = marshallerService.marshall(request);
            messageCreator = create(message, request.getCorrelationId(), request.getProperties());
        } else {
            throw new IllegalArgumentException("request can't be a null.");
        }
        return messageCreator;
    }

    @Override
    public MessageCreator create(IResponse response) {
        MessageCreator messageCreator = null;
        if (response != null) {
            String correlationId = StringUtils.EMPTY;
            if (StringUtils.isNotEmpty(response.getCorrelationId())) {
                correlationId = response.getCorrelationId();
            } else {
                correlationId = UUID.randomUUID().toString();
                response.setCorrelationId(correlationId);
            }
            String message = marshallerService.marshall(response);
            messageCreator = create(message, correlationId, response.getProperties());
        } else {
            throw new IllegalArgumentException("request can't be a null.");
        }
        return messageCreator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResponse getResponse(Message message) {
        IResponse response = null;
        if (message instanceof TextMessage) {
            try {
                final TextMessage tm = (TextMessage) message;
                response = marshallerService.unmarshall(tm.getText(), Response.class);
                LOGGER.info("--------------- RESPONSE RECEIVED ---------------");
            } catch (JMSException e) {
                LOGGER.error(ExceptionUtils.getMessage(e));
            }
        } else {
            LOGGER.info("--------------- RESPONSE DOESN'T RECEIVED ---------------");
        }
        return response;
    }

    @Override
    public IRequest getRequest(Message message) {
        IRequest request = null;
        if (message instanceof TextMessage) {
            try {
                final TextMessage tm = (TextMessage) message;
                request = marshallerService.unmarshall(tm.getText(), Request.class);
                LOGGER.info("--------------- REQUEST RECEIVED ---------------");
            } catch (JMSException e) {
                LOGGER.error(ExceptionUtils.getMessage(e));
            }
        } else {
            LOGGER.info("--------------- REQUEST DOESN'T RECEIVED ---------------");
        }
        return request;
    }


    private MessageCreator create(final String msg, final String correlationID, final Map<String, String> properties) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(msg);
                message.setJMSCorrelationID(correlationID);
                if (MapUtils.isNotEmpty(properties)) {
                    for (Map.Entry<String, String> entry : properties.entrySet()) {
                        message.setStringProperty(entry.getKey(), entry.getValue());
                    }
                }
                return message;
            }
        };
        return messageCreator;
    }
}
