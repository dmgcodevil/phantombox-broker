package com.git.broker.producer;

import com.git.broker.api.domain.IJmsExchanger;
import static com.git.broker.api.domain.Constants.CONTACT_ID_PROPERTY;
import com.git.broker.api.domain.ISelectorBuilder;
import com.git.broker.api.domain.SelectorCondition;
import com.git.broker.api.service.consumer.IConsumerService;
import com.git.broker.api.service.producer.IProducerService;
import com.git.broker.impl.domain.SelectorBuilder;
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

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IConsumerService consumerService;

    @Autowired
    private IJmsExchanger jmsExchanger;

    private ISelectorBuilder selectorBuilder;

    private IContact contact;

    private IContact subscriber;


    /**
     * Set up.
     */
    @Before
    public void setUp() {
        selectorBuilder = new SelectorBuilder();
        contact = UserDomainBuilder.buildOwnContact();
        subscriber = UserDomainBuilder.buildOwnContact();
        contact.setId(UUID.randomUUID().toString());
        subscriber.setId(UUID.randomUUID().toString());
        jmsExchanger.setSelector(selectorBuilder.createSelector()
            .addProperty(CONTACT_ID_PROPERTY)
            .addCondition(SelectorCondition.EQUALS)
            .addPropertyValue(contact.getId())
            .buildSelector());

    }

    /**
     * Test SendRequest.
     */
    @Test
    public void testCall() {
        jmsExchanger.call(subscriber, contact);
    }

}
