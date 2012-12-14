package com.git.broker.impl.service.producer;

import static com.git.broker.api.domain.Constants.CALL_REQUEST_QUEUE;
import static com.git.broker.api.domain.Constants.CALL_RESPONSE_QUEUE;
import com.git.broker.api.domain.IJmsExchanger;
import com.git.broker.api.domain.IRequest;
import com.git.broker.api.domain.IResponse;
import com.git.broker.api.service.factory.IMessageCreatorFactory;
import com.git.broker.api.service.marshaller.IMarshallerService;
import com.git.broker.api.service.producer.IProducerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javax.jms.Message;

/**
 * {@link IProducerService} interface implementation..
 * <p/>
 * Date: 27.11.12
 * Time: 19:03
 *
 * @author rpleshkov
 */
@Service("producerService")
public class ProducerService implements IProducerService {

    @Autowired
    @Qualifier("jettisonMarshallerService")
    private IMarshallerService marshallerService;

    @Autowired
    private IMessageCreatorFactory messageCreatorFactory;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private IJmsExchanger jmsExchanger;

    private Map<IRequest, MessageCreator> requestPool = new HashMap();

    private static final Logger LOGGER = Logger.getLogger(ProducerService.class);

    private static final int RECEIVE_TIMEOUT = 100000;

    /**
     * {@inheritDoc}
     */
    @Override
    public IResponse sendRequest(final IRequest request) {
        IResponse response = null;
        if (request != null) {
            MessageCreator messageCreator = messageCreatorFactory.create(request);
            jmsTemplate.send(CALL_REQUEST_QUEUE, messageCreator);
            jmsTemplate.setReceiveTimeout(RECEIVE_TIMEOUT);
            requestPool.put(request, messageCreator);
            Message message = jmsTemplate.receiveSelected(CALL_RESPONSE_QUEUE,
                buildSelector(request.getCorrelationId()));
            response = messageCreatorFactory.getResponse(message);
            jmsExchanger.reply(response);
        } else {
            throw new IllegalArgumentException("request for sending can't be null.");
        }
        return response;
    }

    @Override
    public void cancelRequest(IRequest request) {
        MessageCreator messageCreator = requestPool.remove(request);
        // TODO implement cancel logic. use for this
    }

    private String buildSelector(String correlationId) {
        String selector = MessageFormat.format("JMSCorrelationID=''{0}''",
            (StringUtils.isNotEmpty(correlationId) ? correlationId : StringUtils.EMPTY));
        return selector;
    }
}
