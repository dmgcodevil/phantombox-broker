package com.git.broker.api.service.factory;

import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Message;

/**
 * Message creator factory.
 * <p/>
 * Date: 29.11.12
 * Time: 16:38
 *
 * @author rpleshkov
 */
public interface IMessageCreatorFactory {

    /**
     * Creates message.
     *
     * @param request {@link IRequest}
     * @return {@link MessageCreator}
     */
    MessageCreator create(IRequest request);

    /**
     * Creates message.
     *
     * @param response {@link IResponse}
     * @return {@link MessageCreator}
     */
    MessageCreator create(IResponse response);

    /**
     * Gets response.
     *
     * @param message {@link Message}
     * @return {@link IResponse}
     */
    IResponse getResponse(Message message);

    /**
     * Gets request.
     *
     * @param message {@link Message}
     * @return {@link IRequest}
     */
    IRequest getRequest(Message message);
}
