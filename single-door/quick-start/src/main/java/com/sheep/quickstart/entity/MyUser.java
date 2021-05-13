package com.sheep.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yangyangSheep
 * @ClassName MyUser.java
 * @createTime 2021/05/13 17:02
 * @Description
 */
@Data
@TableName("user")
public class MyUser {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String description;
}
