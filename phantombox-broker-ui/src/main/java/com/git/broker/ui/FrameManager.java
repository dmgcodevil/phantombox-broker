package com.git.broker.ui;

import com.git.broker.api.domain.ICallFame;
import com.git.broker.api.domain.IIncomingCallFrame;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.ui.IFrameManager;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link IFrameManager} interface implementation.
 * <p/>
 * Date: 14.12.12
 * Time: 17:15
 *
 * @author rpleshkov
 */
public class FrameManager implements IFrameManager {

    private Map<IRequest, IIncomingCallFrame> incomingCallFrames = new HashMap();
    private Map<IRequest, ICallFame> callFrames = new HashMap();

    /**
     * {@inheritDoc}
     */
    @Override
    public ICallFame createCallFame(IRequest request, IJmsExchanger jmsExchanger, String contactName) {
        ICallFame callFame = new CallFame(request, jmsExchanger, contactName);
        callFrames.put(request, callFame);
        callFame.showFrame();
        return callFame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeCallFame(IRequest request) {
        ICallFame callFame = callFrames.remove(request);
        if (callFame != null) {
            callFame.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIncomingCallFrame createIncomingCallFrame(IRequest request, IJmsExchanger jmsExchanger) {
        IIncomingCallFrame incomingCallFrame = new IncomingCallFrame(request, jmsExchanger);
        incomingCallFrames.put(request, incomingCallFrame);
        return incomingCallFrame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeIncomingCallFrame(IRequest request) {
        IIncomingCallFrame incomingCallFrame = incomingCallFrames.remove(request);
        if (incomingCallFrame != null) {
            incomingCallFrame.close();
        }
    }


}
