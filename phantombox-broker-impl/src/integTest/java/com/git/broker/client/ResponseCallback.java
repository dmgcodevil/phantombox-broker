package com.git.broker.client;

import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.IResponseCallback;
import com.git.broker.api.domain.ResponseType;
import org.apache.log4j.Logger;

/**
 * Mock response callback implementation for integration test.
 * <p/>
 * Date: 29.11.12
 * Time: 13:28
 *
 * @author rpleshkov
 */
public class ResponseCallback implements IResponseCallback {

    private static final Logger LOGGER = Logger.getLogger(ResponseCallback.class);

    /**
     * Response.
     *
     * @param response {@link IResponse}
     */
    @Override
    public void response(IResponse response) {
        if (response.getType().equals(ResponseType.ACCEPT)) {
            LOGGER.info("Hello !!!");
        } else {
            LOGGER.info("Bye!!!");
        }
    }

}
