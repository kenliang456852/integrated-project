package com.integrated.shiros.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ClassName: RolesOrFilter
 * Description:
 * Author: liangchao
 * Date: 2018/7/23 21:48
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class RolesOrFilter extends AuthorizationFilter {
    @Override protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse,
            Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] roles = (String[]) o;
        if( ArrayUtils.isEmpty(roles)) {
            return true;
        }
        for (String role: roles) {
            if(subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
