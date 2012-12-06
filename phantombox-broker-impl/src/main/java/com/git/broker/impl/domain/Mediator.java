package com.git.broker.impl.domain;

import static com.git.broker.api.domain.Constants.IP_ADDRESS;
import static com.git.broker.api.domain.Constants.AUDIO_PORT;
import static com.git.broker.api.domain.Constants.VIDEO_PORT;
import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IMediator;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.api.service.consumer.IConsumerService;
import com.git.broker.api.service.producer.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * AbstractMediator.
 * <p/>
 * Date: 05.12.12
 * Time: 18:55
 *
 * @author rpleshkov
 */
public abstract class Mediator implements IMediator {

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IConsumerService consumerService;

    private Map<String, IRequest> receivedRequests = new HashMap<>();

    private Map<String, IRequest> sentRequests = new HashMap<>();

    @Override
    public void callRequest(String subscriberName, String contactId, String ipAddress, int audioPort, int videoPort) {
        IRequest request = createRequest(subscriberName, contactId, ipAddress, audioPort, videoPort);
        producerService.sendRequest(request);
        sentRequests.put(request.getCorrelationId(), request);
    }

    @Override
    public void incomingCall(IRequest request) {
        receivedRequests.put(request.getCorrelationId(), request);
        JFrame frame = createIncomingCallFrame(request.getSubscriberName(), this);
        frame.setVisible(true); // put to map
    }

    @Override
    public void reply(IResponse response) {
        if (ResponseType.ACCEPT.equals(response.getType())) {
            IRequest request = sentRequests.remove(response.getCorrelationId());
            acceptOnBroadcast(request.getProperties().get(IP_ADDRESS),
                Integer.parseInt(request.getProperties().get(AUDIO_PORT)),
                Integer.parseInt(request.getProperties().get(VIDEO_PORT)));
            acceptOnListen(response.getProperties().get(IP_ADDRESS),
                Integer.parseInt(response.getProperties().get(AUDIO_PORT)),
                Integer.parseInt(response.getProperties().get(VIDEO_PORT)));
        }
    }


    @Override
    public void answer(ResponseType responseType, String correlationId, String ipAddress, int audioPort, int videoPort) {
        IRequest request = receivedRequests.remove(correlationId);
        IResponse response = createResponse(correlationId, responseType, ipAddress, audioPort, videoPort);
        consumerService.sendResponse(response);
        if (ResponseType.ACCEPT.equals(responseType)) {
            acceptOnListen(request.getProperties().get(IP_ADDRESS),
                Integer.parseInt(request.getProperties().get(AUDIO_PORT)),
                Integer.parseInt(request.getProperties().get(VIDEO_PORT)));
            acceptOnBroadcast(ipAddress, audioPort, videoPort);
        } else if (ResponseType.CANCEL.equals(responseType)) {
            cancel();
        }
    }


    IRequest createRequest(String subscriberName, String contactId, String ipAddress, int audioPort, int videoPort) {
        IRequest request = new Request();
        request.setSubscriberName(subscriberName);
        request.getProperties().put(CONTACT_ID_PROPERTY, contactId);
        request.getProperties().put(IP_ADDRESS, ipAddress);
        request.getProperties().put(AUDIO_PORT, String.valueOf(audioPort));
        request.getProperties().put(VIDEO_PORT, String.valueOf(videoPort));
        return request;
    }

    private IResponse createResponse(String correlationId, ResponseType type, String ipAddress, int audioPort, int videoPort) {
        IResponse response = new Response();
        response.setCorrelationId(correlationId);
        response.setType(type);
        response.getProperties().put(IP_ADDRESS, ipAddress);
        response.getProperties().put(AUDIO_PORT, String.valueOf(audioPort));
        response.getProperties().put(VIDEO_PORT, String.valueOf(videoPort));
        return response;
    }
}
