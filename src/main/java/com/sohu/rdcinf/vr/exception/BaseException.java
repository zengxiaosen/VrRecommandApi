package com.sohu.rdcinf.vr.exception;

/**
 * Created by zengxiaosen on 2017/6/23.
 */
public class BaseException  extends RuntimeException{


    private static final long serialVersionUID = 4968574468611441150L;
    public String msg;
    public int code;

    public BaseException(){

    }

    protected BaseException(int code, String argO, Throwable arg1){
        super(argO, arg1);
        this.code = code;
        this.msg = argO;
    }

    public BaseException(int code){
        this.code = code;
        this.msg = BaseExceptionEnum.getDescStrByCode(code);
    }

    public BaseException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMessage(){
        return this.msg;
    }

    public int getCode(){
        return this.code;
    }


}
