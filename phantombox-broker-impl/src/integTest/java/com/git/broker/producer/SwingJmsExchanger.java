package com.git.broker.producer;

import com.git.broker.impl.domain.AbstractJmsExchanger;
import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/14/12
 * Time: 4:04 PM
 */
public class SwingJmsExchanger extends AbstractJmsExchanger {
    @Override
    public void broadcast(IConnection connection) {
        System.out.println("broadcast");
    }

    @Override
    public void listen(IContact contact) {
        System.out.println("listen");
    }

    @Override
    public void onCallStop(IContact contact) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
