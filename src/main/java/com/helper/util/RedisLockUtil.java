package com.helper.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
/**
 * @author zhangjie
 * 基于redis实现的分布式锁工具
 * */
@Slf4j
@Component
public class RedisLockUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Long SUCCESS = 1L;
    //锁的过期时间
    private static final  int  EXPIRE_TIME=3;

    private static  ScriptSource scriptSourceLock;
    private static  ScriptSource scriptSourceReleaseLock;
    @PostConstruct
    void lua(){
        scriptSourceLock=new ResourceScriptSource(new ClassPathResource("redis_lock.lua"));
        scriptSourceReleaseLock=new ResourceScriptSource(new ClassPathResource("release_lock.lua"));
    }
    /**
     * 获取锁
     * 默认3秒失效
     * @param lockKey
     * @param value
     * @return
     */
    public boolean getLock(String lockKey, String value) {
        boolean ret = false;
        try {
            DefaultRedisScript redisScript = new DefaultRedisScript();
            redisScript.setScriptSource(scriptSourceLock);
            // 设置脚本返回类型
            redisScript.setResultType(Long.class);
            List<String> list=Collections.singletonList(lockKey);
            Object result = redisTemplate.execute(redisScript,list, value, EXPIRE_TIME);
            if (SUCCESS.equals(result)) {
                ret= true;
            }
        } catch (Exception e) {
            log.error("获取redis锁异常失败", e);
        }
        return ret;
    }

    /**
     * 获取锁
     * 带有失效时间
     * @param lockKey
     * @param value
     * @return
     */
    public boolean getLock(String lockKey, String value,Integer expireTime) {
        boolean ret = false;
        try {
            DefaultRedisScript redisScript = new DefaultRedisScript();
            redisScript.setScriptSource(scriptSourceLock);
            // 设置脚本返回类型
            redisScript.setResultType(Long.class);
            List<String> list=Collections.singletonList(lockKey);
            Object result = redisTemplate.execute(redisScript,list, value, expireTime);
            if (SUCCESS.equals(result)) {
                ret= true;
            }
        } catch (Exception e) {
            log.error("获取redis锁异常失败", e);
        }
        return ret;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param value
     * @return
     */
    public boolean releaseLock(String lockKey, String value) {
        boolean ret = false;
        try {
            DefaultRedisScript redisScript = new DefaultRedisScript();
            redisScript.setScriptSource(scriptSourceReleaseLock);
            redisScript.setResultType(Long.class);
            List<String> list=Collections.singletonList(lockKey);
            Object result = redisTemplate.execute(redisScript,list, value);
            if (SUCCESS.equals(result)) {
                ret = true;
            }
        } catch (Exception e) {
            log.error("释放redis锁异常失败", e);
        }
        return ret;

    }
}
