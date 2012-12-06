package com.git.broker.client;

import com.git.broker.api.domain.IMediator;
import com.git.broker.client.ui.CallFrame;
import com.git.broker.impl.domain.AbstractMediator;
import com.git.domain.api.IConnection;
import org.apache.log4j.Logger;

import javax.swing.JFrame;

/**
 * Enter class description.
 * <p/>
 * Date: 06.12.12
 * Time: 10:38
 *
 * @author rpleshkov
 */
public class SwingAbstractMediator extends AbstractMediator implements IMediator {

    private static final Logger LOGGER = Logger.getLogger(SwingAbstractMediator.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createIncomingCallFrame(String subscriberName, String correlationId, IMediator mediator) {
        return new CallFrame(subscriberName, correlationId, mediator);
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
