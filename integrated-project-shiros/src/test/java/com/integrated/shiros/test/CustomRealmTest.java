package com.integrated.shiros.test;

import com.integrated.shiros.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: CustomRealmTest
 * Description: 测试自定义CustomRealm
 * Author: liangchao
 * Date: 2018/7/20 15:50
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomRealmTest {
    @Autowired
    private CustomRealm customRealm;

    @Test
    public void testCustomRealm() {
        // 1.勾践SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(customRealm);
        // 密码加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("md5");
        customRealm.setCredentialsMatcher(matcher);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("admin","123456"));
        System.out.println("isAuthenticated---" + subject.isAuthenticated());

//        subject.checkRole("admin");
//        subject.checkPermissions("user:delete","user:add");
    }
}
