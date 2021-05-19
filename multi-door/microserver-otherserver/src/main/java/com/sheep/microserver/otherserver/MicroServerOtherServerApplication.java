package com.sheep.microserver.otherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangyangSheep
 * @ClassName MicroServerOtherServerApplication.java
 * @Description 服务启动类
 * @createTime 2021/5/17 18:17
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.sheep.*")
public class MicroServerOtherServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServerOtherServerApplication.class, args);
    }

}
