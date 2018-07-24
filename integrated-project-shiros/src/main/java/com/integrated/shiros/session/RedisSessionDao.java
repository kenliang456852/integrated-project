package com.integrated.shiros.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * ClassName: RedisSessionDao
 * Description:
 * Author: liangchao
 * Date: 2018/7/23 21:59
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class RedisSessionDao extends AbstractSessionDAO {
    @Override
    protected Serializable doCreate(Session session) {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
