package com.git.broker.impl.service.marshaller.json;

import com.git.broker.api.service.marshaller.IMarshallerService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link IMarshallerService} interface implementation.
 * <p/>
 * Date: 27.11.12
 * Time: 19:27
 *
 * @author rpleshkov
 */
@Service("jettisonMarshallerService")
public class JettisonMarshallerService implements IMarshallerService {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> String marshall(T object) {
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias(object.getClass().getName(), object.getClass());
        return xstream.toXML(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<String> marshall(List<T> objects) {
        List<String> messages = new ArrayList<>();
        for (T obj : objects) {
            messages.add(marshall(obj));
        }
        return messages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unmarshall(String message, Class<T> clazz) {
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias(clazz.getName(), clazz);
        T obj = (T) xstream.fromXML(message);
        return obj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> unmarshall(List<String> messages, Class<T> clazz) {
        List<T> objects = new ArrayList<>();
        for (String msg : messages) {
            objects.add(unmarshall(msg, clazz));
        }
        return objects;
    }
}
