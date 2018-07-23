package com.integrated.shiros.realm;

import com.integrated.shiros.model.BusiAcctInfo;
import com.integrated.shiros.model.PermissionInfo;
import com.integrated.shiros.model.RoleInfo;
import com.integrated.shiros.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private LoginService loginService;

    /**
     * @Description 授权
     * @author liangchao
     * @date 2018/7/20 15:36
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
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
        List<PermissionInfo> permissionList = loginService.getPermissionByAcctName(userName);
        Set<String> permissions = new HashSet<>();
        if(CollectionUtils.isNotEmpty(permissionList)) {
            for (PermissionInfo permissionInfo : permissionList) {
                permissions.add(permissionInfo.getPermissionName());
            }
        }
        return permissions;
    }

    private Set<String> getRolesByUserName(String userName) {
        List<RoleInfo> roleList = loginService.getRoleListByAcctName(userName);
        Set<String> roleSet = new HashSet<>();
        if(CollectionUtils.isNotEmpty(roleList)) {
            for (RoleInfo roleInfo: roleList) {
                roleSet.add(roleInfo.getRoleName());
            }
        }
        return roleSet;
    }

    /**
     * @Description 认证
     * @author liangchao
     * @date 2018/7/20 15:36
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();
        // 2.通过用户名到数据库查询凭证
        String password = getPasswordByUserName(userName);
//        String password = loginService.getBusiAcctInfoByUserName(userName);
        if( StringUtils.isBlank(password)) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(userName), super.getName());
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {
        BusiAcctInfo busiAcctInfo = loginService.getBusiAcctInfoByUserName(userName);
        if(busiAcctInfo!=null) {
            return busiAcctInfo.getPassword();
        }
        return StringUtils.EMPTY;
    }

}
