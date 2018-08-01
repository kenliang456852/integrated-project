package com.integrated.shiros.cache;

import com.integrated.shiros.utils.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * ClassName: RedisCache
 * Description:
 * Author: liangchao
 * Date: 2018/8/1 14:48
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class RedisCache<K, V> implements Cache<K, V> {
    //@Autowired
    private JedisUtil jedisUtil;

    /**
     * @Description 为了不和其他的缓存混淆，追加前缀方式以作区分
     * @author liangchao
     * @date 2018/8/1 15:45
     */
    private static final String CACHE_PREFIX = "integrated-cache";

    private byte[] getRealKey(K k) {
        if(k instanceof String) {
            return StringUtils.concat(CACHE_PREFIX, k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K key) throws CacheException {
        byte[] value = jedisUtil.get(getRealKey(key));
        if(null == value) {
            return null;
        }
        return (V) SerializationUtils.deserialize(value);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        jedisUtil.set(getRealKey(key),SerializationUtils.serialize(value),60);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V value = get(key);
        jedisUtil.delete(getRealKey(key));
        return value;
    }

    @Override
    public void clear() throws CacheException {
        //cleea 不要  调用之后会将redis里面全部清理掉。
    }

    @Override
    public int size() {
        if(null != keys()) {
            return keys().size();
        }
        return 0;
    }

    @Override
    public Set<K> keys() {
        Set<K> kets = null;
        Set<byte[]> bytes = jedisUtil.keySet(this.CACHE_PREFIX);
        if(null != bytes) {
            kets = new HashSet<>();
            for (byte[] b : bytes) {
                kets.add((K)SerializationUtils.deserialize(b));
            }
        }
        return kets;
    }

    @Override
    public Collection<V> values() {
        List<V> vl = null;
        List<byte[]> values = jedisUtil.values(this.CACHE_PREFIX);
        if(null != values) {
            vl = new ArrayList<>();
            for (byte[] b: values) {
                vl.add((V) SerializationUtils.deserialize(b));
            }
        }
        return vl;
    }
}
