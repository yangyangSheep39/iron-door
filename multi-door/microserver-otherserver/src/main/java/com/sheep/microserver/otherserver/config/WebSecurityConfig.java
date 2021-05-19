package com.sheep.microserver.otherserver.config;

import com.sheep.microserver.common.security.filter.AuthFilter;
import com.sheep.microserver.common.security.response.AccessDenied;
import com.sheep.microserver.common.security.response.AuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yangyangSheep
 * @ClassName WebSecurityConfig.java
 * @Description SpringSecurity配置
 * @createTime 2021/3/12 15:39
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private AccessDenied accessDenied;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                //请求被拒绝
                .and().exceptionHandling().accessDeniedHandler(accessDenied).authenticationEntryPoint(authEntryPoint)
                //禁用session，使用前后端分离的模式
                .and().sessionManagement().disable()
                //放行所有接口，但是需要验证jwt
                .authorizeRequests().anyRequest().authenticated()
                .and().addFilter(new AuthFilter(super.authenticationManager(), authEntryPoint));
    }

}