package com.helper.redishelper;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.helper.businesshelper.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("")
public class RedisHelper {


    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Param key:String
     * @Param value:T
     * @Description: set一个key为String value为对象的值
     * */
    public <T>void setModel(String key,T model) {
        HashMap<String, Object> map= Constant.convertToMap(model);
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * @Param key:String
     * @Param value:T.class
     * @Description: get一个key 并指定返回值的类类型
     * */
    public <T>Object getModel(String key,Class cls){
        Map res=redisTemplate.opsForHash().entries(key);
        Object resp= JSON.parseObject(JSON.toJSONString(res), cls);
        return resp;
    }

    /**
     * @Param key:String
     * @Param value:Object
     * @Description: redis的普通set功能
     * */
    public void setString(String key,Object value){
        redisTemplate.opsForValue().set(key, Constant.convert2String(value));
    }

    /**
     * 带有失效时间的set
     * */
    public void setStringWithExpiredSeconds(String key,Object value,Long expiredSeconds){
        redisTemplate.opsForValue().set(key, Constant.convert2String(value),expiredSeconds, TimeUnit.SECONDS);
    }

    public String getString(String key){
        Object result=redisTemplate.opsForValue().get(key);
        return result==null?null:String.valueOf(result);
    }

    public Boolean delKey(String key){
        return redisTemplate.delete(key);
    }

    public Long delKeyList(List<String> keys){
        if(keys!=null&&keys.size()>0){return 0L;}
        Long resp=redisTemplate.delete(keys);
        return resp;
    }
}
