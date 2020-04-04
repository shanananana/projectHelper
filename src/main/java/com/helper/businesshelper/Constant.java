package com.helper.businesshelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helper.exceptionhelper.EmBusinessError;
import com.helper.exceptionhelper.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Slf4j
public class Constant {
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * MD5通用方法
     */
    public static String getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error" + e);
        }
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    /**
     * 将任何对象转为String
     */
    public static <T> String convert2String(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("convert object to json failed");
        }
    }

    /**
     * 将类转map
     */
    public static HashMap<String, Object> convertToMap(Object obj) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);
            Object o = null;
            try {
                o = fields[i].get(obj);
            } catch (IllegalAccessException e) {
                throw new BusinessException(EmBusinessError.CONVERT_ERROR);
            }
            if (o != null)
                map.put(varName, o.toString());
            fields[i].setAccessible(accessFlag);
        }
        return map;
    }
}
