package com.integrated.shiros.session;

import com.integrated.shiros.utils.JedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    private Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    private static final String SHIRO_SESSION_PREFIX = "integrated-session";
    private static final Integer TIME_OUT = 10; // 分钟

    @Autowired
    private JedisUtil jedisUtil;


    private byte[] getKey(Serializable sessionId) {
        return (this.SHIRO_SESSION_PREFIX + sessionId.toString()).getBytes();
    }

    /**
     * @Description 创建session
     * @author liangchao
     * @date 2018/7/24 23:26
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        //设定一个key  将session序列化设为值 存储在缓存中。
        byte[] key = this.getKey(session.getId());
        byte[] value = SerializationUtils.serialize(session);
        logger.info("key-> " + key.toString() + "  value-> " + value);
        jedisUtil.set(key, value);
        jedisUtil.expire(key, TIME_OUT);

        return sessionId;
    }

    /**
     * @Description 获得session
     * @author liangchao
     * @date 2018/7/24 23:27
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (null != sessionId) {
            byte[] key = getKey(sessionId);
            byte[] bytes = jedisUtil.get(key);
            return (Session) SerializationUtils.deserialize(bytes);
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        byte[] key = this.getKey(session.getId());
        byte[] value = SerializationUtils.serialize(session);
        logger.info("key-> " + key.toString() + "  value-> " + value);
        jedisUtil.set(key, value);
        jedisUtil.expire(key, TIME_OUT);
    }

    @Override
    public void delete(Session session) {
        if(null != session && null != session.getId()) {
            byte[] key = getKey(session.getId());
            jedisUtil.delete(key);
        }
    }

    @Override
    public Set<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<byte[]> keys = jedisUtil.keySet(this.SHIRO_SESSION_PREFIX);
        if(CollectionUtils.isNotEmpty(keys)) {
            for (byte[] key: keys) {
                byte[] bytes = jedisUtil.get(key);
                sessions.add((Session) SerializationUtils.deserialize(bytes));
            }
        }
        return sessions;
    }
}
