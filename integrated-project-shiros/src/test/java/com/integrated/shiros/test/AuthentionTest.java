package com.integrated.shiros.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * ClassName: AuthentionTest
 * Description: 测试认证
 * Author: liangchao
 * Date: 2018/7/13 15:22
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class AuthentionTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("kenliang", "123456", "admin");
    }

    @Test
    public void testAuthentication() {

        // 1. 勾践SecurityManager
        DefaultSecurityManager dsm = new DefaultSecurityManager();
        // 初始化用户信息
        dsm.setRealm(simpleAccountRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(dsm);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("kenliang","123456");
        subject.login(token);
        subject.checkRole("admin");
        // 认证
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
    }
}
