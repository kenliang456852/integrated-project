package com.integrated.utils.common;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JsonUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/18 16:19
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class JsonUtils {
    public static<T> T parseObj(String jsonStr, Class<T> clazz) {
        return (T) JSONObject.parseObject(jsonStr ,clazz);
    }

    public static<T> T parseObj(Object obj, Class<T> clazz) {
        String jsonStr = "";
        if(obj instanceof String) {
            jsonStr = (String) obj;
        } else {
            jsonStr = JSONObject.toJSONString(obj);
        }
        if(StringUtils.isNotBlank(jsonStr)) {
            return (T) JSONObject.parseObject(jsonStr, clazz);
        }
        return null;
    }

}
