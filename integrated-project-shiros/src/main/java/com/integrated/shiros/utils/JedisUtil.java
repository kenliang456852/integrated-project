package com.integrated.shiros.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * ClassName: JedisUtil
 * Description:
 * Author: liangchao
 * Date: 2018/7/24 22:39
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
@Component
public class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    /**
     * @Description 设置 key value
     * @author liangchao
     * @date 2018/7/24 23:01
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * @Description 设置 key value outTime(过期时间)
     * @author liangchao
     * @date 2018/7/24 23:01
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int outTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            jedis.expire(key, outTime * 60);
            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * @Description 设置过期时间
     * @author liangchao
     * @date 2018/7/24 23:00
     * @param key
     * @param outTime （单位分钟）
     * @return
     */
    public void expire(byte[] key, int outTime) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.expire(key, outTime * 60);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void delete(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            Long del = jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keySet(String shiroSessionPrefix) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.keys((shiroSessionPrefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }

    public List<byte[]> values(String shiroSessionPrefix) {
        List<byte[]> list = null;
        Jedis jedis = jedisPool.getResource();
        try {
            Set<byte[]> bytes = keySet(shiroSessionPrefix);
            if(null != bytes) {
                for (byte[] b : bytes) {
                    byte[] valB = jedis.get(b);
                    if(null != valB) {
                        list.add(valB);
                    }
                }
            }
        } finally {
            jedis.close();
        }
        return list;
    }
}
