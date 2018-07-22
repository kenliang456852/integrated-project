package com.integrated.shiros.controller;

import com.integrated.core.web.json.JsonRequest;
import com.integrated.core.web.json.JsonResponse;
import com.integrated.shiros.dto.vo.LoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: LoginController
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 18:46
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("login")
    public JsonResponse login(@RequestBody JsonRequest<LoginInfo> jsonRequest) {
        LoginInfo reqBody = jsonRequest.getReqBody();
        logger.info(reqBody.toString());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(reqBody.getUserName(),reqBody.getPassword());
        subject.login(token);
        return new JsonResponse();
    }
}
