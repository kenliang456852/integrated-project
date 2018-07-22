package com.integrated.shiros.handler;

import com.integrated.core.web.json.JsonResponse;
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

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    private JsonResponse exceptionHandler(HttpServletRequest requsst, HttpServletResponse response, Exception e) {
        if(e instanceof RuntimeException) {
            return JsonResponse.failRespose(e.getMessage());
        }
        return JsonResponse.exceptionRespose(e);
    }

}
