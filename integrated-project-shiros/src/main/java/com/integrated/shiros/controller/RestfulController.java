package com.integrated.shiros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: ToPageController
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 18:56
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Controller
public class RestfulController {

    @GetMapping("/{pageName}.html")
    public String toPage(@PathVariable String pageName) {
        return pageName;
    }

}
