package com.git.broker.api.service.producer;

/**
 * Producer service.
 * <p/>
 * Date: 27.11.12
 * Time: 18:59
 *
 * @param <T> type
 * @author rpleshkov
 */
public interface ICallProducerService<T> {

    /**
     * Send object.
     *
     * @param obj object
     */
    void sendObject(T obj);
}
