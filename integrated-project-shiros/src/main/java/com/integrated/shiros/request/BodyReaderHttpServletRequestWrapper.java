package com.integrated.shiros.request;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.integrated.core.web.json.JsonHeader;
import com.integrated.core.web.json.JsonRequest;
import com.integrated.utils.common.StringUtils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * ClassName: RequestWrapper
 * Description: 参数重组
 * Author: liangchao
 * Date: 2018/8/7 11:47
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Logger logger = LoggerFactory.getLogger(BodyReaderHttpServletRequestWrapper.class);

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
            bodyStr = StringUtils.decodeUrl(bodyStr);
//            try {
                JsonRequest jsonRequest = JSONObject.parseObject(bodyStr, JsonRequest.class);
                if(null != jsonRequest) {
                    Object reqBody = jsonRequest.getReqBody();
                    body = JSONObject.toJSONString(reqBody).getBytes();
//                    JsonHeader reqHeader = jsonRequest.getReqHeader();
//                    TODO:根据 sid（sessionId）  查询当前登录人的信息放入 ThreadLocal
//                    reqHeader.getSid();
                }
//            } catch (Exception e) {
//                logger.error("BodyReaderHttpServletRequestWrapper do JsonObject JsonRequest error", e);
//            }
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
