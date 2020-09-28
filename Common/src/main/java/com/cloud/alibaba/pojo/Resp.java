package com.cloud.alibaba.pojo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * <b></b>
 *
 * @author xiao
 * @version 1.0
 * @create 2020/1/16
 */
public class Resp<T> {

    @ApiModelProperty(value = "返回编号")
    private Integer code;
    @ApiModelProperty(value = "返回信息描述")
    private String msg;
    @ApiModelProperty(value = "返回对象")
    private T data;

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

    protected Resp() {
        this.code = 1;
        this.msg = "";
    }

    protected Resp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    protected Resp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Resp fail(String msg) {
        return fail(0, "失败");
    }

    public static Resp fail(Integer code, String msg) {
        return new Resp(code, msg);
    }

    public static Resp success() {
        return new Resp();
    }

    public static <T> Resp<T> success(T data) {
        if (null == data) {
            return new Resp(1,"成功", null);
        }
        return new Resp(1, "成功", data);
    }

    public static <T> Resp<T> success(T data, String msg) {
        return new Resp(1, msg, data);
    }


    // validateFailed
    // unauthorized
    // forbidden

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
