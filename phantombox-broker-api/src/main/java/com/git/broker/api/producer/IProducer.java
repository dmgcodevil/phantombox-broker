package com.git.broker.api.producer;

/**
 * Producer.
 * <p/>
 * Date: 27.11.12
 * Time: 18:59
 *
 * @author rpleshkov
 */
public interface IProducer {

    /**
     * Send object.
     *
     * @param obj object
     * @param <T> type of object
     */
    <T> void sendObject(T obj);
}
