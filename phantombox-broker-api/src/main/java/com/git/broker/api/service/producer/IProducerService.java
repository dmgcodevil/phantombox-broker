package com.git.broker.api.service.producer;

import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;

/**
 * Producer service.
 * <p/>
 * Date: 27.11.12
 * Time: 18:59
 *
 * @author rpleshkov
 */
public interface IProducerService {

    /**
     * Sends request.
     *
     * @param request {@link IRequest}
     * @return {@link IResponse}
     */
    IResponse sendRequest(IRequest request);

    /**
     * Cancel the request sending.
     *
     * @param request {@link IRequest}
     */
    void cancelRequest(IRequest request);
}
