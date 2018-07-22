package com.integrated.core.web.json;

/**
 * ClassName: JsonHeader
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 19:59
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonHeader {

    public JsonHeader() {
    }

    public JsonHeader(String sid) {
        this.sid = sid;
    }

    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
