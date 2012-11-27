package com.git.broker.impl.producer;

import com.git.broker.api.producer.IProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * Enter class description.
 * <p/>
 * Date: 27.11.12
 * Time: 19:03
 *
 * @author rpleshkov
 */
public class Producer implements IProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public <T> void sendObject(T obj) {
    }
}
