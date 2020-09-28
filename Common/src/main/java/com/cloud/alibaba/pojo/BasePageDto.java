package com.cloud.alibaba.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BasePageDto {

    @NotNull(message = "当前第几页不能为空")
    @Min(value = 1,message = "当前页数最小为1")
    @ApiModelProperty(value = "当前第几页", required = true )
    public int page=1;

    @NotNull(message = "每页显示数据条数不能为空")
    @Min(value = 1,message = "每页显示数据条数最小为1")
    @Max(value = 50,message = "每页显示数据条数最大为50")
    @ApiModelProperty(value = "每页显示数据条数",required = true)
    private int rows=20;

    /*@ApiModelProperty(value = "排序字段", required = false)
    private String sidx;

    @ApiModelProperty(value = "排序类型,asc:从小到大,desc:从大到小", required = false)
    private String sord;*/
}
