package com.git.broker.producer;

import com.git.broker.api.domain.IMediator;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.service.consumer.IConsumerService;
import com.git.broker.api.service.producer.IProducerService;
import com.git.domain.api.IConnection;
import com.git.domain.api.IContact;
import com.git.domain.util.helper.UserDomainBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

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
public class CallIntegrationTest {

    private static final String CONTACT_ID = "1235050793";

    private IRequest request;

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IConsumerService consumerService;

    @Autowired
    private IMediator mediator;

    private IContact contact;


    /**
     * Set up.
     */
    @Before
    public void setUp() {
        contact = UserDomainBuilder.buildOwnContact();
        contact.setId(CONTACT_ID);
        mediator.setContact(contact);

    }

    /**
     * Test SendRequest.
     */
    @Test
    public void testCall() {
        mediator.call("Alex", contact.getId());
    }

}
