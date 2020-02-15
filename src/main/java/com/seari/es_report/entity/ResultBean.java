package com.seari.es_report.entity;

import com.seari.es_report.util.PropertiesUtils;

/**
 * 公共返回类
 */
public class ResultBean {
    /*
    返回码
     */
    private int code;
    /*
    提示信息
     */
    private String msg;
    /*
    返回数据
     */
    private Object data;

    public ResultBean(int code) {
        this.code = code;
        this.msg = PropertiesUtils.getString(code);
    }

    public ResultBean() {
        this.msg = PropertiesUtils.getString(this.code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultBean getDefaultBean(){
        return  new ResultBean();
    }
}
