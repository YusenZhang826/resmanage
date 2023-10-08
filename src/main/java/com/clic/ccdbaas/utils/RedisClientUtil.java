package com.clic.ccdbaas.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPooled;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class RedisClientUtil {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    @Value("${spring.redis.password:#{null}}")
    private String redisKey;

    public static JedisPooled jedis;

    // redis 执行成功标志
    private static final Long SUCCESS_LABEL = 1L;

    private RedisClientUtil() {
    }

    @PostConstruct
    public void loadConfig() {
        String host = this.redisHost;
        Integer port = this.redisPort;
        String key = null;
        //密码不存在时必须传null
        if (this.redisKey != null && !this.redisKey.isEmpty()) {
            key = this.redisKey;
        }
        jedis = new JedisPooled(host, port, null, key);
    }

    /**
     * 获取 redis 分布式锁
     *
     * @param lockKey   分布式锁的key值
     * @param lockValue 分布式锁的value值
     * @param second    超时时间（以秒为单位）
     * @return 是否执行成功
     */
    public static boolean getDistributedLock(String lockKey, String lockValue, Long second) {
        Object lockResult = jedis.setnx(lockKey, lockValue);
        Object expireResult = jedis.expire(lockKey, second);
        return SUCCESS_LABEL.equals(lockResult) && SUCCESS_LABEL.equals(expireResult);
    }

    /**
     * 释放 redis 锁
     *
     * @param lockKey 分布式锁的key值
     * @return 是否执行成功
     */
    public static boolean releaseDistributedLock(String lockKey) {
        if (!jedis.exists(lockKey))
            return false;

        Object result = jedis.del(lockKey);
        return SUCCESS_LABEL.equals(result);
    }

    public static Integer generateExpireTime(Integer expireTime, Integer offsetTime) {
        return expireTime + new Random().nextInt(offsetTime);
    }

    public static Integer generateExpireTime(Integer expireTime) {
        return generateExpireTime(expireTime, 0);
    }

}
