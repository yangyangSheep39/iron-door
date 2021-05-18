package com.sheep.microserver.security.auth.config;

import com.sheep.microserver.common.security.filter.AuthFilter;
import com.sheep.microserver.common.security.response.AccessDenied;
import com.sheep.microserver.common.security.response.AuthEntryPoint;
import com.sheep.microserver.security.auth.response.AuthenticationFail;
import com.sheep.microserver.security.auth.response.AuthenticationSuccess;
import com.sheep.microserver.security.auth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yangyangSheep
 * @ClassName WebSecurityConfig.java
 * @Description SpringSecurity配置
 * @createTime 2021/2/26 10:54
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccess authenticationSuccess;
    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private AuthenticationFail authenticationFail;
    @Autowired
    private AccessDenied accessDenied;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .formLogin()
                //登录接口地址
                .loginPage("http://localhost:8080/#/login")
                //自定义属性
                .usernameParameter("username")
                .passwordParameter("secret")
                //登录的地址,和前台的登录页面匹配
                .loginProcessingUrl("/user/login")
                //登录成功返回json
                .successHandler(authenticationSuccess)
                //登录失败返回json
                .failureHandler(authenticationFail)
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                //登出
                .and().logout()
                //登出后返回的页面
                .logoutUrl("http://localhost:8080/#/login")
                //登出地址
                .logoutSuccessUrl("/outLogin").permitAll()
                //请求被拒绝
                .and().exceptionHandling().accessDeniedHandler(accessDenied).authenticationEntryPoint(authEntryPoint)
                //禁用session，使用前后端分离的模式
                .and().sessionManagement().disable()
                //放行所有接口，但是需要验证jwt
                .authorizeRequests().anyRequest().authenticated()
                .and().addFilter(new AuthFilter(super.authenticationManager(), authEntryPoint))
        //.authorizeRequests().anyRequest().permitAll()
        ;
    }

}