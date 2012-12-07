package com.git.broker.client;

import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.client.ui.CallFrame;
import com.git.broker.impl.domain.AbstractJmsExchanger;
import com.git.domain.api.IConnection;
import org.apache.log4j.Logger;

import javax.swing.JFrame;

/**
 * Swing jms exchanger.
 * <p/>
 * Date: 06.12.12
 * Time: 10:38
 *
 * @author rpleshkov
 */
public class SwingAbstractJmsExchanger extends AbstractJmsExchanger implements IJmsExchanger {

    private static final Logger LOGGER = Logger.getLogger(SwingAbstractJmsExchanger.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createIncomingCallFrame(String subscriberName, String correlationId, IJmsExchanger jmsExchanger) {
        return new CallFrame(subscriberName, correlationId, jmsExchanger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void broadcast(IConnection connection) {
        LOGGER.info("Start broadcast: " + connection.getIpAddress());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void listen(IConnection connection) {
        LOGGER.info("Start listen: " + connection.getIpAddress());
    }

    @Override
    public void cancel() {
        LOGGER.info("cancel");
    }
}
