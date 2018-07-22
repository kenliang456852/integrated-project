package com.integrated.shiros;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: ShiroApplication
 * Description:
 * Author: liangchao
 * Date: 2018/7/20 17:23
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@SpringBootApplication(scanBasePackages = {"com.integrated.shiros"})
@MapperScan(basePackages = {"com.integrated.shiros.dao"})
public class ShiroApplication {
    private static Logger logger = LoggerFactory.getLogger(ShiroApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class,args);
        logger.info("ShiroApplication execute success");
    }
}