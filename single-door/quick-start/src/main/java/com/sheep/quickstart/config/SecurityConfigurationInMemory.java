package com.sheep.quickstart.config;

import com.sheep.quickstart.utils.PasswordEncoderUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author yangyangSheep
 * @ClassName SecurityConfiguration.java
 * @Description security配置类
 * @createTime 2021年05月09日 17:42
 */
//@Configuration
public class SecurityConfigurationInMemory extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
            不可以和配置文件的方法重叠使用(配置文件的密码需要加密)
            如果有自己的密码加密工具,需要在密码加密类工具类中注入@Bean PasswordEncoder 并且@Compoment
            如果没有,则需要在此类中注入PasswordEncoder
        */
        //加密密码   需要@Bean
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("fang");
        ////在内存中存放一个用户
        auth.inMemoryAuthentication().withUser("yang").password(password).roles("admin");
        //使用自己的工具加密
        String zhangsan = PasswordEncoderUtil.encode("zhangsan");
        auth.inMemoryAuthentication().withUser("zhangsan").password(zhangsan).roles("admin");
    }

    //
    //@Bean
    //PasswordEncoder password() {
    //    return new BCryptPasswordEncoder();
    //}

}
