package com.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author 74716
 * @Date 2020/9/24 13:26
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication
{
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
