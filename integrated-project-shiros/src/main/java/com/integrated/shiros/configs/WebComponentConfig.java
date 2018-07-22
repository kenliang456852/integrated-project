package com.integrated.shiros.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ClassName: WebComponentConfig
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 19:11
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Configuration
@ImportResource(locations= {"classpath:spring-*.xml"})
public class WebComponentConfig {

//    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        // 新建过滤器注册类
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // 将自定义的过滤器添加进来
        registrationBean.setFilter(null);
        //设置过滤器的请求方式
        registrationBean.addUrlPatterns("/*");

        return  registrationBean;
    }
}
