package com.git.broker.impl.domain;

import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.api.service.consumer.IConsumerService;
import com.git.broker.api.service.producer.IProducerService;
import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.swing.JFrame;

/**
 * {@link IJmsExchanger} interface implementation.
 * <p/>
 * Date: 05.12.12
 * Time: 18:55
 *
 * @author rpleshkov
 */
public abstract class AbstractJmsExchanger implements IJmsExchanger {

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IConsumerService consumerService;

    private IContact contact;

    private Map<String, IRequest> receivedRequests = new HashMap<>();

    private Map<String, IRequest> sentRequests = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(AbstractJmsExchanger.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact getContact() {
        return contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContact(IContact contact) {
        this.contact = contact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String subscriberName, String contactId) {
        IRequest request = createRequest(subscriberName, contactId, contact.getConnection());
        sentRequests.put(request.getCorrelationId(), request);
        producerService.sendRequest(request);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incomingCall(IRequest request) {
        receivedRequests.put(request.getCorrelationId(), request);
        JFrame frame = createIncomingCallFrame(request.getSubscriberName(), request.getCorrelationId(), this);
        frame.setVisible(true); // put to map
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reply(IResponse response) {
        if (ResponseType.ACCEPT.equals(response.getType())) {
            IRequest request = sentRequests.remove(response.getCorrelationId());
            broadcast(request.getConnection());
            listen(response.getConnection());
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void answer(ResponseType responseType, String correlationId) {
        IRequest request = receivedRequests.remove(correlationId);
        IResponse response = createResponse(correlationId, responseType, contact.getConnection());
        consumerService.sendResponse(response);
        if (ResponseType.ACCEPT.equals(responseType)) {
            listen(request.getConnection());
            broadcast(contact.getConnection());
        } else if (ResponseType.CANCEL.equals(responseType)) {
            cancel();
        }
    }


    private IRequest createRequest(String subscriberName, String contactId, IConnection connection) {
        IRequest request = new Request();
        String correlationId = UUID.randomUUID().toString();
        request.setCorrelationId(correlationId);
        request.setSubscriberName(subscriberName);
        request.setConnection(connection);
        request.getProperties().put(CONTACT_ID_PROPERTY, contactId);
        return request;
    }

    private IResponse createResponse(String correlationId, ResponseType type, IConnection connection) {
        IResponse response = new Response();
        response.setCorrelationId(correlationId);
        response.setType(type);
        response.setConnection(connection);
        return response;
    }

}
