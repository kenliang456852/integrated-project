package com.integrated.shiros.handler;

import com.integrated.core.exception.BusinessException;
import com.integrated.core.web.json.JsonResponse;
import com.integrated.utils.common.IpUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 17:43
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    private JsonResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
//        logger.error("异常信息：{}",e.getMessage());
        //url
        logger.error("url={}", request.getRequestURL());

        //method
        logger.error("method={}", request.getMethod());

        //ip
        logger.error("ip={}", IpUtils.getIpAddr(request));

        //TODO：获取当前登录人信息。

        logger.error("异常：", e);

        if(e instanceof UnknownAccountException ) {
            JsonResponse unknownAccountJsonResponse = JsonResponse.failRespose(e);
            unknownAccountJsonResponse.setRetDesc("用户名或密码错误！");
            return unknownAccountJsonResponse;
        } else if (e instanceof IncorrectCredentialsException ){
            JsonResponse incorrectCredentialsJsonResponse = JsonResponse.failRespose(e);
            incorrectCredentialsJsonResponse.setRetDesc("用户名或密码错误！");
            return incorrectCredentialsJsonResponse;
        } else if(e instanceof BusinessException ) {
            BusinessException be = (BusinessException) e;
            if(be.getErrorCode().equals(be.getMessage())) {
                JsonResponse beJsonResponse = JsonResponse.failRespose(e);
                beJsonResponse.setRetDesc(be.getErrorCode());
            }
            return new JsonResponse(be.getErrorCode(), be.getMessage());
        } else if(e instanceof RuntimeException) {
            return JsonResponse.failRespose(e);
        }

        return JsonResponse.exceptionRespose(e);
    }

}
