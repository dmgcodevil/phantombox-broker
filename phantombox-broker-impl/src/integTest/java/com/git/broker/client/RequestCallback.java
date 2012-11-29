package com.git.broker.client;

import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IRequestCallback;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.IResponseCallback;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.impl.domain.Response;

/**
 * Mock request callback implementation for integration test.
 * <p/>
 * Date: 29.11.12
 * Time: 13:10
 *
 * @author rpleshkov
 */
public class RequestCallback implements IRequestCallback {

    private static final String SUBSCRIBER = "Alex";

    /**
     * Process request.
     *
     * @param request          {@link IRequest}
     * @param responseCallback response callback
     */
    @Override
    public void request(IRequest request, IResponseCallback responseCallback) {
        IResponse response = null;
        if (request.getSubscriberName().equals(SUBSCRIBER)) {
            response = createResponse(request.getCorrelationId(), ResponseType.ACCEPT);
        } else {
            response = createResponse(request.getCorrelationId(), ResponseType.CANCEL);
        }
        responseCallback.response(response);
    }

    private IResponse createResponse(String correlationId, ResponseType type) {
        IResponse response = new Response();
        response.setCorrelationId(correlationId);
        response.setType(type);
        return response;
    }
}
