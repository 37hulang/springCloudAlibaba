package com.cloud.alibaba.entity;

import java.io.Serializable;


/**
 * (Provider)实体类
 *
 * @author 74716
 * @since 2020-09-28 09:47:19
 */
public class Provider implements Serializable {
    private static final long serialVersionUID = -93594752573004270L;

    private Integer id;

    private Integer number;

    public Provider() {
    }

    public Provider(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}