package com.git.broker.api.marshaller;

import java.util.List;

/**
 * Marshaller.
 * <p/>
 * Date: 27.11.12
 * Time: 19:19
 *
 * @author rpleshkov
 */
public interface IMarshaller {

    /**
     * Coverts object to message.
     *
     * @param object object
     * @param <T>    type of object
     * @return message
     */
    <T> String marshall(T object);

    /**
     * Coverts list of objects to list of messages.
     *
     * @param objects objects
     * @param <T>     type of object
     * @return messages
     */
    <T> List<String> marshall(List<T> objects);

    /**
     * Coverts message to object.
     *
     * @param message message
     * @param clazz   {@link Class}
     * @param <T>     type of object
     * @return object
     */
    <T> T unmarshall(String message, Class<T> clazz);

    /**
     * Coverts list of messages to list of objects.
     *
     * @param messages messages
     * @param clazz    {@link Class}
     * @param <T>      type of object
     * @return objects
     */
    <T> List<T> unmarshall(List<String> messages, Class<T> clazz);
}
