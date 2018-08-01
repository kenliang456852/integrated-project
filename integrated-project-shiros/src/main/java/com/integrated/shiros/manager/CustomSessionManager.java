package com.integrated.shiros.manager;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * ClassName: CustomSessionManager
 * Description:
 * Author: liangchao
 * Date: 2018/8/1 14:23
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Session session = null;
        Serializable sessionId = getSessionId(sessionKey);
        if(null == sessionId) {
            return null;
        }
        ServletRequest request = null;
        if(sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if(null == request) {
            session = super.retrieveSession(sessionKey);
        } else {
            session = (Session) request.getAttribute(sessionId.toString());
            if(null == session) {
                session = super.retrieveSession(sessionKey);
                if(null != request && null != sessionId) {
                    request.setAttribute(sessionId.toString(), session);
                }
            }
        }
        return session;
    }
}
