package com.git.broker.impl.service.producer;

import static com.git.broker.api.domain.Constants.CLASS_NAME_PROPERTY;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.collections.MapUtils;
import org.springframework.jms.core.MessageCreator;

import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Enter class description.
 * <p/>
 * Date: 28.11.12
 * Time: 19:08
 *
 * @author rpleshkov
 */
public class MessageCreatorFactory {

    private MessageCreatorFactory() {

    }

    public static MessageCreator create(final String msg, final String correlationID, final Map<String, String> properties) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage(msg);
                message.setJMSCorrelationID(correlationID);
                if (MapUtils.isNotEmpty(properties)) {
                    for (Map.Entry<String, String> entry : properties.entrySet()) {
                        message.setStringProperty(entry.getKey(), entry.getValue());
                    }
                }
                //lOGGER.info("Sending message(json): " + jsonMsg);
                return message;
            }
        };
        return messageCreator;
    }
}
