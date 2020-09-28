package com.cloud.alibaba.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <b>(Consumer)VO</b>
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:46:35
 */
@Getter
@Setter
public class ConsumerVO implements Serializable {
    private static final long serialVersionUID = 758453077886833773L;

    @ApiModelProperty()
    private Integer id;

    @ApiModelProperty()
    private Integer number;

}