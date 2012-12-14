package com.git.broker.impl.domain;

import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.api.service.consumer.IConsumerService;
import com.git.broker.api.service.producer.IProducerService;
import com.git.broker.api.ui.IFrameManager;
import com.git.domain.api.IConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private IFrameManager frameManager;

    private Map<String, IRequest> receivedRequests = new HashMap<>();

    private Map<String, IRequest> sentRequests = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(AbstractJmsExchanger.class);


    /**
     * {@inheritDoc}
     */
    @Override
    public void call(IRequest request) {
        sentRequests.put(request.getCorrelationId(), request);
        producerService.sendRequest(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String subscriberName, IConnection connection, String contactId) {
        IRequest request = createRequest(subscriberName, contactId, connection);
        call(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void call(String subscriberName, IConnection connection, String contactName,
                     String contactId) {
        IRequest request = createRequest(subscriberName, contactId, connection);
        frameManager.createCallFame(request, this, contactName);
        call(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incomingCall(IRequest request) {
        receivedRequests.put(request.getCorrelationId(), request);
        frameManager.createIncomingCallFrame(request, this);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void reply(IResponse response) {
        IRequest request = sentRequests.remove(response.getCorrelationId());
        if (ResponseType.ACCEPT.equals(response.getType())) {
            broadcast(request.getConnection());
            listen(response.getConnection());
        } else {
            producerService.cancelRequest(request);
            frameManager.disposeCallFame(request);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void answer(ResponseType responseType, IConnection connection, String correlationId) {
        IRequest receivedRequest = receivedRequests.remove(correlationId);
        IResponse response = createResponse(correlationId, responseType, connection);
        consumerService.sendResponse(response);
        if (ResponseType.ACCEPT.equals(responseType)) {
            listen(receivedRequest.getConnection());
            broadcast(connection);
        } else {
            frameManager.disposeCallFame(receivedRequest);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelCall(IRequest request) {
        producerService.cancelRequest(request);
    }

    private IRequest createRequest(String subscriberName, String contactId,
                                   IConnection connection) {
        IRequest request = new Request();
        String correlationId = UUID.randomUUID().toString();
        request.setCorrelationId(correlationId);
        request.setSubscriberName(subscriberName);
        request.setConnection(connection);
        request.getProperties().put(CONTACT_ID_PROPERTY, contactId);
        return request;
    }

    private IResponse createResponse(String correlationId, ResponseType type,
                                     IConnection connection) {
        IResponse response = new Response();
        response.setCorrelationId(correlationId);
        response.setType(type);
        response.setConnection(connection);
        return response;
    }

}
