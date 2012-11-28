package com.git.broker.impl.util;

import org.apache.log4j.Logger;
import org.springframework.jms.JmsException;
import org.springframework.jms.connection.ConnectionFactoryUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.util.Assert;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TemporaryQueue;

/**
 * Enter class description.
 * <p/>
 * Date: 28.11.12
 * Time: 17:23
 *
 * @author rpleshkov
 */
public class RequestReplyJmsTemplate extends JmsTemplate {

    private static final Logger LOGGER = Logger.getLogger(RequestReplyJmsTemplate.class);

    public RequestReplyJmsTemplate() {
        super();
    }

    public RequestReplyJmsTemplate(ConnectionFactory aConnectionFactory)  {
        super(aConnectionFactory);
    }

    public Message sendReceive(final MessageCreator aMessageCreator) {
        if (getDefaultDestination() != null) {
            return sendReceive(getDefaultDestination(),  aMessageCreator);
        } else {
            return executeWithoutTransaction(new  SessionCallback<Message>() {
                public Message doInJms(Session aSession) throws JMSException {
                    Destination lDestination =  resolveDestinationName(aSession, getRequiredDefaultDestinationName());
                    return doSendReceive(aSession, (Queue) lDestination,  aMessageCreator.createMessage(aSession), null);
                }
            });
        }
    }

    public Message sendReceive(final Destination aSendDestination, final  MessageCreator aMessageCreator) {
        return sendReceive(aSendDestination, aMessageCreator, null);
    }

    public Message sendReceive(final Destination aSendDestination, final  MessageCreator aMessageCreator, final Destination aReplyDestination) {
        return executeWithoutTransaction(new  SessionCallback<Message>() {
            public Message doInJms(Session aSession) throws JMSException  {
                return doSendReceive(aSession, (Queue) aSendDestination,  aMessageCreator.createMessage(aSession), (Queue) aReplyDestination);
            }
        });
    }

    public Object sendReceiveAndConvert(Object aMessage) throws JmsException {
        Destination defaultDestination = getDefaultDestination();
        if (defaultDestination != null) {
            return sendReceiveAndConvert(defaultDestination, aMessage);
        } else {
            return  sendReceiveAndConvert(getRequiredDefaultDestinationName(), aMessage);
        }
    }

    public Object sendReceiveAndConvert(Destination aDestination, final  Object aMessage) {
        return sendReceiveAndConvert(aDestination, aMessage, null);
    }

    public Object sendReceiveAndConvert(Destination aDestination, final  Object aMessage, Destination aReplyTo) {
        Message lMessage = sendReceive(aDestination, new  MessageCreator() {
            public Message createMessage(Session aSession) throws  JMSException {
                return getRequiredMessageConverter().toMessage(aMessage,  aSession);
            }
        });
        return doConvertFromMessage(lMessage);
    }

    public Object sendReceiveAndConvert(String aDestination, final  Object aMessage) {
        return sendReceiveAndConvert(aDestination, new MessageCreator() {
            public Message createMessage(Session aSession) throws  JMSException {
                return getRequiredMessageConverter().toMessage(aMessage,  aSession);
            }
        });
    }

    private Message doSendReceive(Session aSession, Queue aQueue, final  Message aRequestMessage, Queue aReplyTo) throws JMSException {
        Queue lResponseQueue = null;
        try {
            lResponseQueue = (aReplyTo != null ? aReplyTo :  aSession.createTemporaryQueue());
            aRequestMessage.setJMSReplyTo(lResponseQueue);
            doSend(aSession, aQueue, new MessageCreator() {
                public Message createMessage(Session aSession) throws  JMSException {
                    return aRequestMessage;
                }
            });
            String lMessageSelector = "JMSCorrelationID='" +  aRequestMessage.getJMSMessageID() + "'";
            // reception du message avec correlation id = celui  positionn? dans
            // la question
            return doReceive(aSession, lResponseQueue,  lMessageSelector);
        } finally {
            if (aReplyTo == null) {
                ((TemporaryQueue) lResponseQueue).delete();
            }
        }
    }

    public <T> T  executeWithoutTransaction(SessionCallback<T> aAction) {
        Assert.notNull(aAction, "Callback object must not be null");
        Session lSession = null;
        try {
            lSession =  ConnectionFactoryUtils.getTransactionalSession(getConnectionFactory(), null, false);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Executing callback on JMS Session: " +  lSession);
            }
            return aAction.doInJms(lSession);
        } catch (JMSException err) {
            throw convertJmsAccessException(err);
        } finally {
            JmsUtils.closeSession(lSession);
        }
    }

    private String getRequiredDefaultDestinationName() throws  IllegalStateException {
        String lName = getDefaultDestinationName();
        if (lName == null) {
            throw new IllegalStateException("No 'defaultDestination' or  'defaultDestinationName' specified." + " Check configuration of  JmsTemplate.");
        }
        return lName;
    }

    private MessageConverter getRequiredMessageConverter() throws  IllegalStateException {
        MessageConverter lConverter = getMessageConverter();
        if (lConverter == null) {
            throw new IllegalStateException("No 'messageConverter'  specified. Check configuration of JmsTemplate.");
        }
        return lConverter;
    }
}
