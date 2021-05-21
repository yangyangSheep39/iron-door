package com.sheep.advanced.monomer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author yangyangSheep
 * @ClassName SecurityConfiguration.java
 * @Description security配置类
 * @createTime 2021年05月09日 17:42
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
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
        //设定未授权
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        //设定退出
        http.logout().logoutUrl("/base/logOut").logoutSuccessUrl("/logout.html");
        //设定认证
        http.formLogin()
                //自定义访问页面
                .loginPage("/login.html")
                //前后端分离的时候也可以指定到前端服务的页面上去
                //.loginPage("http://localhost:8080/#/login")
                //自定义属性
                .usernameParameter("username")
                .passwordParameter("secret")
                //登录访问路径
                .loginProcessingUrl("/user/login")
                //登录成功后跳转路径
                //.defaultSuccessUrl("/base/successful")
                //登录成功后跳转页面
                .defaultSuccessUrl("/base/successfulUrl")
                .and()
                //设置不需要认证的路径  /* 是放出静态资源  否则访问不到页面
                .authorizeRequests().antMatchers("/*", "/base/hello", "/user/login", "/base/successful").permitAll()
                //设置只有包含admin权限才能狗访问这个接口
                .antMatchers("/auth/hasAuthorityConfig").hasAuthority("hasAuthorityConfigAdmin")
                //多个权限的配置
                .antMatchers("/auth/hasAnyAuthorityConfig").hasAnyAuthority("hasAnyAuthorityConfigAdmin,hasAdmin")
                //针对某一个角色或者多个角色的配置,源码分析,加上Role_
                .antMatchers("/auth/hasRoleConfig").hasRole("hasRoleConfigAdmin")
                .antMatchers("/auth/hasAnyRoleConfig").hasAnyRole("hasAnyRoleConfigAdmin")
                //任何请求都必须经过身份验证
                .anyRequest().authenticated()
                //关闭csrf防护
                .and().csrf().disable();
        //自动登录
        http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60).userDetailsService(myUserDetailsService);
    }

    /**
     * 自动登录配置  注入数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 自动登录配置  配置对象
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建表
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
