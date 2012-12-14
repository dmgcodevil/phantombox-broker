package com.git.broker.api.ui;

import com.git.broker.api.domain.ICallFame;
import com.git.broker.api.domain.IIncomingCallFrame;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;

/**
 * IFrameManager interface.
 * <p/>
 * Date: 05.12.12
 * Time: 20:26
 *
 * @author rpleshkov
 */
public interface IFrameManager {


    /**
     * Create call fame.
     *
     * @param request      {@link IRequest}
     * @param jmsExchanger {@link IJmsExchanger}
     * @param contactName  contact name
     * @return call frame
     */
    ICallFame createCallFame(IRequest request, IJmsExchanger jmsExchanger, String contactName);

    /**
     * Dispose call frame.
     *
     * @param request {@link IRequest}
     */
    void disposeCallFame(IRequest request);

    /**
     * Create incoming call frame.
     *
     * @param request      {@link IRequest}
     * @param jmsExchanger {@link IJmsExchanger}
     * @return frame
     */
    IIncomingCallFrame createIncomingCallFrame(IRequest request, IJmsExchanger jmsExchanger);

    /**
     * Dispose incoming call frame.
     *
     * @param request {@link IRequest}
     */
    void disposeIncomingCallFrame(IRequest request);

}
