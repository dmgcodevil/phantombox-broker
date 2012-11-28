package com.git.broker.impl.service.consumer;

import static com.git.broker.api.domain.Constants.CALL_RESPONSE_QUEUE;
import static com.git.broker.api.domain.Constants.CLASS_NAME_PROPERTY;
import com.git.broker.api.domain.ICall;
import com.git.broker.api.domain.IRequestCallbackAction;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.IResponseCallbackAction;
import com.git.broker.api.domain.ResponseType;
import com.git.broker.api.service.consumer.ICallConsumerService;
import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.impl.domain.Response;
import com.git.broker.impl.service.producer.MessageCreatorFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * {@link ICallConsumerService} interface implementation.
 * <p/>
 * Date: 28.11.12
 * Time: 11:05
 *
 * @author rpleshkov
 */
public class CallConsumerService implements ICallConsumerService {

    private static final Logger LOGGER = Logger.getLogger(CallConsumerService.class);

    @Autowired
    @Qualifier("jettisonMarshallerService")
    private IMarshallerService marshallerService;

    @Autowired
    private JmsTemplate jmsTemplate;

    private IResponseCallbackAction responseCallbackAction;

    public IResponseCallbackAction getResponseCallbackAction() {
        return responseCallbackAction;
    }

    public void setResponseCallbackAction(IResponseCallbackAction responseCallbackAction) {
        this.responseCallbackAction = responseCallbackAction;
    }


    @PostConstruct
    public void init() {
        responseCallbackAction = new IResponseCallbackAction() {

            @Override
            public void accept() {
                process(ResponseType.ACCEPT);
            }

            @Override
            public void cancel() {
                process(ResponseType.CANCEL);
            }

            private void process(ResponseType responseType) {
                IResponse response = new Response();
                response.setType(responseType);
                String msg = marshallerService.marshall(response);
                MessageCreator messageCreator = MessageCreatorFactory.create(msg, CLASS_NAME_PROPERTY, response.getClass().getName());
                jmsTemplate.send(CALL_RESPONSE_QUEUE, messageCreator);
            }
        };
    }

    @Override
    public void onMessage(Message message) {


        ICall call = null;
        BasicConfigurator.configure();
        try {
            Class clazz = message.getObjectProperty(CLASS_NAME_PROPERTY).getClass();
            if (message instanceof TextMessage) {
                final TextMessage tm = (TextMessage) message;
                String msgBody = tm.getText();
                LOGGER.info("message: " + msgBody);
                call = (ICall) marshallerService.unmarshall(msgBody, clazz);
                System.out.println(call);
                IResponse response = new Response();
                response.setType(ResponseType.ACCEPT);
                String msg = marshallerService.marshall(response);
                MessageCreator messageCreator = MessageCreatorFactory.create(msg, tm.getJMSCorrelationID());
                jmsTemplate.send(CALL_RESPONSE_QUEUE, messageCreator);
                //responseCallbackAction.accept();
            }
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
