package com.cloud.alibaba.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BaseDto {

    @NotBlank(message = "appName不能为空")
    @Length(max = 50, message = "appName不能超过50个字符长度")
    @ApiModelProperty(notes = "appName", required = true)
    private String appName;

    @NotBlank(message = "appVersion不能为空")
    @Length(max = 50, message = "appVersion不能超过50个字符长度")
    @ApiModelProperty(notes = "appVersion", required = true)
    private String appVersion;

    // 内部使用，操作人员id
    @ApiModelProperty(required = false)
    private Integer userId;
}
