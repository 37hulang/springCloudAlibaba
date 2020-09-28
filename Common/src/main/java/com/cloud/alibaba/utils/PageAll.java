package com.cloud.alibaba.utils;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "分页")
public class PageAll<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总条数")
    protected long total;

    @ApiModelProperty(value = "列表")
    protected List<T> list;

    private PageAll(){}

    public PageAll(List<T> list) {
        if(null == list){
            list = new ArrayList<>();
        }
        this.list = list;
        this.total = list.size();
    }

    public static <T> PageAll<T> of(List<T> list) {
        return new PageAll(list);
    }

    public String toJson(){
        return JSONObject.toJSONString(this);
    }

    @Override
    public String toString(){
        return toJson();
    }
}
