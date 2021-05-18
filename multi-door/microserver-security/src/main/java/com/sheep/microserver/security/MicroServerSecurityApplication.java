package com.sheep.microserver.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangyangSheep
 * @ClassName MicroServerSecurityApplication.java
 * @Description security认证启动类
 * @createTime 2021/5/17 18:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.sheep.*")
public class MicroServerSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServerSecurityApplication.class, args);
    }

}
