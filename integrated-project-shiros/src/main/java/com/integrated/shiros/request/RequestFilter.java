package com.integrated.shiros.request;

import com.integrated.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: RequestFilter
 * Description:
 * Author: liangchao
 * Date: 2018/8/7 10:11
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@WebFilter(filterName = "requestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestFilter do init execute...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("RequestFilter do doFilter execute...");
        HttpServletRequest req = (HttpServletRequest) request;
        // 获得url
        String requestURI = req.getRequestURI();
        // TODO： 判断utl时候需要过滤 不过滤的放行

        // 处理POST请求
        if(StringUtils.equals("POST", req.getMethod())) {
            Map<String, Object> resultParamMap = new HashMap<>();
            Map<String, String[]> parameterMap = req.getParameterMap();
            ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(req, resultParamMap);
            chain.doFilter(parameterRequestWrapper, response);
        }

    }

    @Override
    public void destroy() {
        logger.info("RequestFilter do destroy execute...");

    }
}
