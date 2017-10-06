package com.hehe.entity;

import java.io.Serializable;
public class R<T> implements Serializable {

    private static final long serialVersionUID = -4577255781088498763L;
    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; //服务端数据
    private int status = OK; //状态码
    private String msg = ""; //描述信息

    //APIS
    public static R isOk(){
        return new R();
    }
    public static R isFail(){
        return new R().status(FAIL);
    }
    public static R isFail(Throwable e){
        return isFail().msg(e);
    }
    public R msg(Throwable e){
        this.setMsg(e.toString());
        return this;
    }
    public R data(T data){
        this.setData(data);
        return this;
    }
    public R status(int status){
       this.setStatus(status);
       return this;
    }


    //Constructors
    public R() {

    }

    //Getter&Setters
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
