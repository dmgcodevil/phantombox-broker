package com.git.broker.producer;

import com.git.broker.api.domain.ICall;
import com.git.broker.api.service.consumer.ICallConsumerService;
import com.git.broker.api.service.producer.ICallProducerService;
import com.git.broker.impl.domain.Call;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Producer service integration test.
 * <p/>
 * Date: 28.11.12
 * Time: 11:17
 *
 * @author rpleshkov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/git/broker/spring/producer-config.xml",
    "classpath:com/git/broker/spring/consumer-config.xml"})
public class ProducerServiceIntegrationTest {

    private static final String CONTACT_ID = "1235050793";

    private ICall call;

    @Autowired
    private ICallProducerService callProducerService;


    @Before
    public void setUp() {
        call = buildCall();
    }

    @Test
    public void testSend() {
        callProducerService.sendObject(call);
    }

    private ICall buildCall() {
        ICall call = new Call();
        call.setContactId(CONTACT_ID);
        return call;
    }
}
