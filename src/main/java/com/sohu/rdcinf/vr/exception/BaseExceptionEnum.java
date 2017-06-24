package com.sohu.rdcinf.vr.exception;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public enum  BaseExceptionEnum {
    BASE_EXCEPTION_ENUM(500, "测试错误");

    int code;
    String msg;

    BaseExceptionEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public static String getDescStrByCode(int code){
        BaseExceptionEnum[] payTypes = values();
        for(BaseExceptionEnum payType : payTypes){
            if(payType.getCode() == code){
                return payType.getMsg();
            }
        }
        return "";
    }

    public static BaseExceptionEnum getByCode(int code){
        BaseExceptionEnum[] payTypes = values();
        for(BaseExceptionEnum payType : payTypes){
            if(payType.getCode() == code){
                return payType;
            }
        }
        return null;
    }



}
