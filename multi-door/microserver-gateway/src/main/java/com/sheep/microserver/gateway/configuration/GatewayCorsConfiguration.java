package com.sheep.microserver.gateway.configuration;

import com.sheep.microserver.gateway.filter.AuthGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author yangyangSheep
 * @ClassName GatewayCorsConfiguration.java
 * @Description 网关跨域配置
 * @createTime 2021年02月21日 17:41
 */
@Configuration
public class GatewayCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        //这个跨域请求配置会报错
        // corsConfiguration.addAllowedOrigin("*");
        //需要修改成这句
        corsConfiguration.addAllowedOriginPattern("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }

    /**
     * 自定义全局拦截器
     * 拦截规则：在路由配置的filters之后
     */
    @Bean
    public GlobalFilter customGlobalFilter() {
        return new AuthGlobalFilter();
    }
}
