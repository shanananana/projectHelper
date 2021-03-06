package com.helper.redishelper;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.helper.businesshelper.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>RedisHelper class.</p>
 *
 * @author zhangjie
 * @version $Id: $Id
 */
@SuppressWarnings("")
@Component
public class RedisHelper {

    @Autowired
    RedisTemplate redisTemplate;
    /**
     *
     * <p> set一个key为String value为对象的值</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param model a T object.
     * @param <T> a T object.
     */
    public <T>void setModel(String key,T model) {
        HashMap<String, Object> map= Constant.convertToMap(model);
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * <p>getModel.</p>
     *
     * <p>:get一个key 并指定返回值的类类型<p/>
     * @param key a {@link java.lang.String} object.
     * @param cls a {@link java.lang.Class} object.
     * @param <T> a T object.
     * @return a {@link java.lang.Object} object.
     */
    public <T>Object getModel(String key,Class cls){
        Map res=redisTemplate.opsForHash().entries(key);
        Object resp= JSON.parseObject(JSON.toJSONString(res), cls);
        return resp;
    }

    /**
     * <p>setString.</p>

     * <p>redis的普通set功能<p/>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     */
    public void setString(String key,Object value){
        redisTemplate.opsForValue().set(key, Constant.convert2String(value));
    }

    /**
     * 带有失效时间的set
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     * @param expiredSeconds a {@link java.lang.Long} object.
     */
    public void setStringWithExpiredSeconds(String key,Object value,Long expiredSeconds){
        redisTemplate.opsForValue().set(key, Constant.convert2String(value),expiredSeconds, TimeUnit.SECONDS);
    }

    /**
     * <p>getString.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public String getString(String key){
        Object result=redisTemplate.opsForValue().get(key);
        return result==null?null:String.valueOf(result);
    }

    /**
     * <p>delKey.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean delKey(String key){
        return redisTemplate.delete(key);
    }

    /**
     * <p>delKeyList.</p>
     *
     * @param keys a {@link java.util.List} object.
     * @return a {@link java.lang.Long} object.
     */
    public Long delKeyList(List<String> keys){
        if(keys!=null&&keys.size()>0){return 0L;}
        Long resp=redisTemplate.delete(keys);
        return resp;
    }
}
