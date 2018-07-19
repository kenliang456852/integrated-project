package com.integrated.utils.common;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName: JsonUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/18 16:19
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonUtils<T> {

    public T parseObj(String jsonStr, Class<T> clazz) {
        return (T) JSONObject.parseObject(jsonStr ,clazz);
    }

}
