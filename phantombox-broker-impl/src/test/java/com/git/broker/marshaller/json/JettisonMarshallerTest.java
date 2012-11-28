package com.git.broker.marshaller.json;

import com.git.broker.api.domain.ICall;

import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.impl.domain.Call;
import com.git.broker.impl.service.marshaller.json.JettisonMarshallerService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Jettison marshaller test.
 * <p/>
 * Date: 28.11.12
 * Time: 10:47
 *
 * @author rpleshkov
 */
public class JettisonMarshallerTest {

    private static final String CONTACT_ID = "1gh#d3&!d42hk%21";

    IMarshallerService marshaller = new JettisonMarshallerService();

    private ICall call;


    @Before
    public void setUp() {
        call = buildCall();
    }

    @Test
    public void testMarshall() {
        String message = marshaller.marshall(call);
        ICall newCall = marshaller.unmarshall(message, Call.class);
        Assert.assertEquals(call, newCall);
    }

    private ICall buildCall() {
        ICall call = new Call();
        call.setContactId(CONTACT_ID);
        return call;
    }
}
