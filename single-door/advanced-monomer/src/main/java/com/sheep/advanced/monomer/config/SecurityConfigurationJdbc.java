package com.sheep.advanced.monomer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yangyangSheep
 * @ClassName SecurityConfiguration.java
 * @Description security配置类
 * @createTime 2021年05月09日 17:42
 */
@Configuration
public class SecurityConfigurationJdbc extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义访问页面
                .loginPage("/login.html")
                //自定义属性
                .usernameParameter("username")
                .passwordParameter("secret")
                //登录访问路径
                .loginProcessingUrl("/user/login")
                //登录成功后跳转路径
                .defaultSuccessUrl("/base/successful")
                .and()
                //设置不需要认证的路径
                .authorizeRequests().antMatchers("/*", "/base/hello", "/user/login", "/base/successful").permitAll()
                //任何请求都必须经过身份验证
                .anyRequest().authenticated()
                //关闭csrf防护
                .and().csrf().disable();

    }
}
