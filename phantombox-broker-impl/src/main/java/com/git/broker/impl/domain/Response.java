package com.git.broker.impl.domain;

import com.git.broker.api.domain.IResponse;
import com.git.broker.api.domain.ResponseType;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/28/12
 * Time: 5:03 PM
 */
public class Response implements IResponse {

    private ResponseType type;

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
