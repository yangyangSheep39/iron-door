package com.sheep.microserver.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yangyangSheep
 * @ClassName MicroServerGatewayApplication.java
 * @Description 网关启动类
 * @createTime 2021/5/17 18:20
 */
@EnableEurekaClient
@SpringBootApplication
public class MicroServerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServerGatewayApplication.class, args);
    }

}
