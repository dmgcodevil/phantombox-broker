package com.git.broker.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 12/10/12
 * Time: 6:19 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:com/git/broker/spring/broker-config.xml"
})
public class ApplicationContextIntegrationTest {

    @Test
    public void test(){

    }
}
