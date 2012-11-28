package com.git.broker.api.domain;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/28/12
 * Time: 5:20 PM
 */
public interface IResponseCallbackAction {

    //void invoke(ResponseType responseType);

    void accept();

    void cancel();
}
