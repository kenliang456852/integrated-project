package com.integrated.shiros.request;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.integrated.utils.common.StringUtils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @Description : 参数重组
 * @Date : 2018/5/25 16:09
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    /**
     * @Description 备份处理
     * @author liangchao
     * @date 2018/8/13 17:25
     * @param request 请求对象
     * @return
     */
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
        String bodyStr = StringUtils.toString(body);
        if(!StringUtils.isEmpty(bodyStr)){
        	JSONObject parseObject = JSONObject.parseObject(bodyStr);
        	JSONObject reqBody = parseObject.getJSONObject("reqBody");
        	reqBody.put("managerAcctId",  JSONObject.parseObject(parseObject.getString("reqHeader")).getString("acctId"));
        	reqBody.put("enterpriseId",  JSONObject.parseObject(parseObject.getString("reqHeader")).getString("memId"));
        	if(!StringUtils.isEmpty(parseObject.toJSONString())){
        		body = parseObject.toJSONString().getBytes();
        	}
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
