package com.cloud.alibaba.entity;

import java.io.Serializable;


/**
 * (Consumer)实体类
 *
 * @author 74716
 * @since 2020-09-28 09:46:30
 */
public class Consumer implements Serializable {
    private static final long serialVersionUID = 346014296502086818L;

    private Integer id;

    private Integer number;

    public Consumer() {
    }

    public Consumer(Integer id) {
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