package com.sheep.microserver.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sheep.microserver.common.core.constant.JwtConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author yangyangSheep
 * @ClassName AuthGlobalFilter.java
 * @Description 将登录用户的JWT转化成用户信息的全局过滤器
 * @createTime 2021/2/27 21:10
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final static String LOGIN_URL = "/**/user/login";

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        //获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("请求接口地址：{}", path);
        //放行登录接口
        if (antPathMatcher.match(LOGIN_URL, path)) {
            return chain.filter(exchange);
        }
        //获取Token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StrUtil.isEmpty(token)) {
            log.info("token为空");
            return returnResponse(response, "未验证用户，请求失败");
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String redisToken = token.replace("Bearer ", "");
            //判断redis中有没有
            String realToken = (String) redisTemplate.opsForValue().get(JwtConstant.JWT_PREFIX + redisToken);
            log.info("请求Token：{}", realToken);
            //解析Jwt
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtConstant.ALGORITHM_HMAC256)).build();
            DecodedJWT verify = jwtVerifier.verify(realToken);
            //验证过期时间
            Date expiresAt = verify.getExpiresAt();
            int i = expiresAt.compareTo(new Date());
            if (i == -1) {
                log.info("token过期");
                return returnResponse(response, "登录已过期");
            }

            return chain.filter(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("网关校验Token异常");
            return returnResponse(response, "网关校验Token异常");
        }
    }

    private Mono<Void> returnResponse(ServerHttpResponse response, String s) {
        JSONObject message = new JSONObject();
        message.put("status", -1);
        message.put("msg", s);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}