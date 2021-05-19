package com.sheep.advanced.monomer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangyangSheep
 * @ClassName MyUser.java
 * @createTime 2021/05/13 17:02
 * @Description 自定义用户
 */
@Data
@Accessors(chain = true)
@TableName("single_user")
public class MyUser {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String description;
}
