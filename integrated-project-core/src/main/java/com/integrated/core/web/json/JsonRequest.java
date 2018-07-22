package com.integrated.core.web.json;

import java.io.Serializable;

/**
 * ClassName: JsonRequest
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 19:58
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonRequest<T> implements Serializable {
    private T reqBody;
    private JsonHeader reqHeader;

    public T getReqBody() {
        return reqBody;
    }

    public void setReqBody(T reqBody) {
        this.reqBody = reqBody;
    }

    public JsonHeader getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(JsonHeader reqHeader) {
        this.reqHeader = reqHeader;
    }
}
