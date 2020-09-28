package com.cloud.alibaba.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <b>(Provider)</b>
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:47:24
 */
@Getter
@Setter
public class ProviderMForm {

    @ApiModelProperty(value ="id", required = true)
    @NotNull(message = "必填项")
    @Min(value = 1, message = "必须大于0")
    private Integer id;

    @ApiModelProperty(value ="id", required = true)
    @NotNull(message = "必填项")
    @Min(value = 1, message = "必须大于0")
    private Integer number;

}