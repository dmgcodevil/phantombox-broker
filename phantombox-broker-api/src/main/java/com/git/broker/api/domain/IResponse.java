package com.git.broker.api.domain;

/**
 * Response.
 * <p/>
 * Date: 28.11.12
 * Time: 21:06
 *
 * @author rpleshkov
 */
public interface IResponse {

    ResponseType getType();

    void setType(ResponseType type);

}
