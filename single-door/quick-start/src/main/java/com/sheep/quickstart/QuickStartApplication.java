package com.sheep.quickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangyangSheep
 * @ClassName QuickStartApplication.java
 * @Description Spring Securit单体项目快速开始示例
 * @createTime 2021/5/9 18:51
 */
@SpringBootApplication
@MapperScan("com.sheep.quickstart.mapper")
public class QuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

}
