package com.integrated.shiros.request;

import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: RequestWrapper
 * Description:
 * Author: liangchao
 * Date: 2018/8/7 11:47
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    /**
     * @Description 存储请求参数
     * @author liangchao
     * @date 2018/8/7 14:48
     */
    private Map<String , String[]> params = new HashMap<String, String[]>();

    public ParameterRequestWrapper(HttpServletRequest request) {
        //将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
//        this.params.putAll(request.getParameterMap());
    }

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, Object> extraParams) {
        this(request);
        addParameters(extraParams);
    }

    @Override
    public String getParameter(String key) {
        String[] values = params.get(key);
        return ArrayUtils.isEmpty(values) ? null : values[0];
    }

    /**
     * @Description 增加参数s
     * @author liangchao
     * @date 2018/8/7 15:36
     * @param extraParams
     * @return
     */
    public void addParameters(Map<String,Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * @Description 增加参数
     * @author liangchao
     * @date 2018/8/7 14:54
     * @param key
     * @param value
     * @return
     */
    public void addParameter(String key, Object value) {
        if(value instanceof String[]) {
            params.put(key, (String[]) value);
        } else if(value instanceof String) {
            params.put(key, new String[] {(String) value});
        } else {
            params.put(key, new String[] {String.valueOf(value)});
        }

    }
}
