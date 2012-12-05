package com.git.broker.impl.domain;

import com.git.broker.api.domain.IMediator;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IRequestCallback;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.IResponseCallback;
import com.git.broker.api.service.consumer.IConsumerService;

/**
 * AbstractMediator.
 * <p/>
 * Date: 05.12.12
 * Time: 18:55
 *
 * @author rpleshkov
 */
public class Mediator implements IMediator {


    @Override
    public void callRequest(IRequest request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void incomingCall(IRequest request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void reply(IResponse response) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void answer() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cancel() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
