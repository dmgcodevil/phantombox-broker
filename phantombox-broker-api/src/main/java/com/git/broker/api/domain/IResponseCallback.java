package com.git.broker.api.domain;

/**
 * Response callback.
 * <p/>
 * User: dmgcodevil
 * Date: 11/28/12
 * Time: 5:20 PM
 */
public interface IResponseCallback {

    /**
     * Response.
     *
     * @param response {@link IResponse}
     */
    void response(IResponse response);
}
