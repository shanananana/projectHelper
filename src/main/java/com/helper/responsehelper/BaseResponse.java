package com.helper.responsehelper;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *返回结果封装
 */
@Data
@Accessors(chain = true)
public class BaseResponse<T> extends ResponseCode  {

    /**
     * 无参的成功响应
     * */
    public static<T> BaseResponse success(){
        BaseResponse response =new BaseResponse();
        response.code=ResponseCode.Code.SUCCESS;
        response.message=ResponseCode.Message.SUCCESS;
        response.data=null;
        return response;
    }

    /**
     * 带数据的成功响应
     * */
    public static<T> BaseResponse success(T t){
        BaseResponse response =new BaseResponse();
        response.code=ResponseCode.Code.SUCCESS;
        response.message=ResponseCode.Message.SUCCESS;
        response.data=t;
        return response;
    }

    /**
     * 无参的失败响应
     * */
    public static<T> BaseResponse fail(){
        BaseResponse response =new BaseResponse();
        response.code=ResponseCode.Code.REQ_FAIL;
        response.message=ResponseCode.Message.REQ_FAIL;
        response.data=null;
        return response;
    }

    /**
     * 带状态码的失败响应
     * */
    public static<T> BaseResponse fail(Integer code){
        BaseResponse response =new BaseResponse();
        response.code=code;
        response.message=ResponseCode.Message.REQ_FAIL;
        response.data=null;
        return response;
    }

    /**
     * 带消息的失败响应
     * */
    public static<T> BaseResponse fail(String message){
        BaseResponse response =new BaseResponse();
        response.code=ResponseCode.Code.SERVER_ERROR;
        response.message=message;
        response.data=null;
        return response;
    }

    /**
     * 带消息和状态码的响应
     * */
    public  static<T> BaseResponse<T> fail(Integer code, String message){
        BaseResponse response =new BaseResponse();
        response.code=code;
        response.message=message;
        response.data=null;
        return response;
    }

}

