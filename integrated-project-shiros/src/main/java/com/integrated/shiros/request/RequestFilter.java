package com.integrated.shiros.request;

import com.integrated.shiros.configs.UrlFilterConfig;
import com.integrated.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private UrlFilterConfig urlFilterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestFilter do init execute...");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        logger.info("RequestFilter do doFilter execute...");
        HttpServletRequest request = (HttpServletRequest) req;
        // 获得url
        String requestURI = request.getRequestURI().substring(1);
        // 判断utl时候需要过滤 不过滤的放行
        if(urlFilterConfig.check(requestURI)) {
            chain.doFilter(request,resp);
            return ;
        }
        // 处理POST请求
        if(StringUtils.equals("POST", request.getMethod())) {
            logger.info("contentType: " + request.getContentType());

            String queryString = request.getQueryString();
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
            chain.doFilter(requestWrapper, response);
        } else {

            chain.doFilter(request, resp);
        }

    }

    @Override
    public void destroy() {
        logger.info("RequestFilter do destroy execute...");

    }
}
