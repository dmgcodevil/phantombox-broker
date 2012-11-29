package com.git.broker.producer;

import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.api.service.producer.IProducerService;
import com.git.broker.impl.domain.Request;
import junit.framework.Assert;
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
@ContextConfiguration(locations = {
    "classpath:com/git/broker/phantombox-broker-test-context.xml",
    "classpath:com/git/broker/spring/phantombox-broker-context.xml"
})
public class ProducerServiceIntegrationTest {

    private static final String CONTACT_ID = "1235050793";

    private IRequest request;

    @Autowired
    private IProducerService producerService;


    /**
     * Set up.
     */
    @Before
    public void setUp() {
        request = buildRequest();
    }

    /**
     * Test SendRequest.
     */
    @Test
    public void testSendRequestWithAccept() {
        IResponse response = producerService.sendRequest(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), ResponseType.ACCEPT);
    }

    /**
     * Test SendRequest.
     */
    @Test
    public void testSendRequestWithCancel() {
        request.setSubscriberName("Marcus");
        IResponse response = producerService.sendRequest(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getType(), ResponseType.CANCEL);
    }


    private IRequest buildRequest() {
        IRequest newRequest = new Request();
        newRequest.getProperties().put(CONTACT_ID_PROPERTY, CONTACT_ID);
        newRequest.setSubscriberName("Alex");
        return newRequest;
    }

}
