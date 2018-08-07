package com.integrated.shiros.request;

import com.integrated.utils.common.IpUtils;
import com.integrated.utils.common.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: RequestAspect
 * Description:
 * Author: liangchao
 * Date: 2018/8/1 16:44
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Component
@Aspect
public class RequestAspect {
    private Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    /**
     * @Description 定义扫描规则
     * @author liangchao
     * @date 2018/5/23 17:12
     */
    @Pointcut("execution(public * com.integrated.shiros.controller.*.*(..))")
    public void scanningRule() {
    }

    /**
     * @Description  scanningRule()引用扫描规则
     * @author liangchao
     * @date 2018/5/23 17:16
     * @param joinPoint
     * @return
     */
    @Before("scanningRule()")
    public void doBefore(JoinPoint joinPoint) {
        //获得Request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", IpUtils.getIpAddr(request));

        //类方法
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName(); //类名
        String name = joinPoint.getSignature().getName();//类方法名
        if( StringUtils.equals(name, "toPage")) {
            return ;
        }
        logger.info("class_method={}", declaringTypeName + "." + name);

        //参数
        logger.info("args={}", joinPoint.getArgs());
        Object[] args = joinPoint.getArgs();
        System.out.println();
    }

    @After("scanningRule()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "scanningRule()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }

}
