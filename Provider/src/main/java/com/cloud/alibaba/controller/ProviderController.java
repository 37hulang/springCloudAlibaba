package com.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author 74716
 * @Date 2020/9/24 13:39
 **/
@RequestMapping("/pro")
@RestController
public class ProviderController {

    @Value("${alibaba_config}")
    private String ALIBABA_CONFIG;

    String get() {
        return "provider server";
    }


    String getConfig(){
        return ALIBABA_CONFIG;
    }

}
