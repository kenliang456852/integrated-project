package com.integrated.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: IntegratedSpringbootApplication
 * Description:
 * Author: liangchao
 * Date: 2018/7/16 11:59
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@SpringBootApplication
public class IntegratedSpringbootApplication {
    private final static Logger logger = LoggerFactory.getLogger(IntegratedSpringbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IntegratedSpringbootApplication.class, args);
        logger.info("springBoot execute Success!");
    }
}
