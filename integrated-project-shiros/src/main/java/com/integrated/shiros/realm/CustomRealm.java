package com.integrated.shiros.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: CustomRealm
 * Description:
 * Author: liangchao
 * Date: 2018/7/20 15:33
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class CustomRealm extends AuthorizingRealm {
    {
        super.setName("customRealm");
    }
    /**
     * @Description 授权
     * @author liangchao
     * @date 2018/7/20 15:36
     * @param principalCollection
     * @return
     */
    @Override protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 1.从主体传过来的认证信息中，获得用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        // TODO:获取用户的角色数据
        Set<String> roles = getRolesByUserName(userName);
        // TODO:获取用户的操作权限
        Set<String> permissions = getPermissionByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionByUserName(String userName) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:delete");
        permissions.add("user:add");
        permissions.add("user:update");
        permissions.add("user:select");
        return permissions;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("user");
        return roleSet;
    }

    /**
     * @Description 认证
     * @author liangchao
     * @date 2018/7/20 15:36
     * @param authenticationToken
     * @return
     */
    @Override protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 2.通过用户名到数据库查询凭证
        String password = getPasswordByUserName(userName);
        if( StringUtils.isBlank(password)) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(userName, password, super.getName());
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {

        return "123456";
    }
}
