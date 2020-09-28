package com.cloud.alibaba.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <b>(Provider)VO</b>
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:47:22
 */
@Getter
@Setter
public class ProviderVO implements Serializable {
    private static final long serialVersionUID = 127608471975796509L;

    @ApiModelProperty()
    private Integer id;

    @ApiModelProperty()
    private Integer number;

}