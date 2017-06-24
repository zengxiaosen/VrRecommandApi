package com.sohu.rdcinf.vr.common;

import com.sohu.rdcinf.vr.exception.BaseException;

import java.io.Serializable;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 133989723905213177L;
    private Integer code;
    private String msg;
    private T data;

    public Response(){
        this.code = 0;
        this.msg = "success";
    }

    public Response(Integer code, String msg, T data, long timestamp){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setErrMsg(int code, String msg) {
        this.data = null;
        this.code = code;
        this.msg = msg;
    }

    public void setErrMsg(BaseException e) {
        this.data = null;
        this.code = -1;
        this.msg = e.getMessage();
    }
}
