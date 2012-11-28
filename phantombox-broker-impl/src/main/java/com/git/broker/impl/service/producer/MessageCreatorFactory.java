package com.git.broker.impl.service.producer;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.core.MessageCreator;

import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Message creator factory.
 * <p/>
 * Date: 28.11.12
 * Time: 19:08
 *
 * @author rpleshkov
 */
public class MessageCreatorFactory {

    private MessageCreatorFactory() {
        throw new IllegalAccessError();
    }

    public static MessageCreator create(final String msg, final String correlationID, final Map<String, String> properties) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(correlationID)) {
            throw new IllegalArgumentException("parameters can't be a null or empty.");
        }
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

    public static MessageCreator create(final String msg, final String correlationID) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(correlationID)) {
            throw new IllegalArgumentException("parameters can't be a null or empty.");
        }
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(msg);
                message.setJMSCorrelationID(correlationID);
                return message;
            }
        };
        return messageCreator;
    }


    public static MessageCreator create(final String msg, final Map<String, String> properties) {
        if (StringUtils.isEmpty(msg)) {
            throw new IllegalArgumentException("parameters can't be a null or empty.");
        }
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(msg);
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

    public static MessageCreator create(final String msg, final String propertyName, final String propertyValue) {
        if (StringUtils.isEmpty(msg)) {
            throw new IllegalArgumentException("parameters can't be a null or empty.");
        }
        Map<String, String> properties = new HashMap<>();
        properties.put(propertyName, propertyValue);
        return create(msg, properties);
    }
}
