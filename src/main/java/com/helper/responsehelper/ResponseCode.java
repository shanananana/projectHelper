package com.helper.responsehelper;

import lombok.Data;

@Data
public class ResponseCode<T> {

    public Integer code;
    public String message;
    public T data;

    //状态码
    public static class Code{
        public static final Integer SUCCESS=200;//成功
        public static final Integer REQ_FAIL=400;//失败
        public static final Integer SERVER_ERROR=500;//失败

    }

    public static class Message{
        public static final String SUCCESS="请求成功";//成功
        public static final String REQ_FAIL="请求失败";//失败
        public static final String SERVER_ERROR="服务异常";//失败

    }


















}
