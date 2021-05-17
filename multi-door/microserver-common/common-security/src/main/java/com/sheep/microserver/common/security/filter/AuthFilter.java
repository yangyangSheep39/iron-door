package com.sheep.microserver.common.security.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sheep.microserver.common.core.constant.JwtConstant;
import com.sheep.microserver.common.core.exception.BaseException;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.core.utils.ResponseUtil;
import com.sheep.microserver.common.security.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author yangyangSheep
 * @ClassName AuthFilter.java
 * @Description 将登录用户的JWT转化成用户信息的全局过滤器
 * @createTime 2021/3/10 16:39
 */
@Slf4j
@Component
@ComponentScan("com.hande.*")
public class AuthFilter extends BasicAuthenticationFilter {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 赋值一个静态的redisTemplate
     */
    public static RedisTemplate redis;

    /**
     * 构造时赋值
     */
    @PostConstruct
    public void redisTemplate() {
        redis = this.redisTemplate;

    }

    public AuthFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Result result = new Result();
        result.setCode(500);
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取Token
        Enumeration<String> headerValue = request.getHeaders("Authorization");
        String token = "";
        while (headerValue.hasMoreElements()) {
            token = headerValue.nextElement();
        }
        if (StrUtil.isEmpty(token)) {
            log.info("token为空");
            result.setMsg("未验证用户，请求失败");
            ResponseUtil.out(response, result);
            throw new BaseException("未验证用户，请求失败");
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String redisToken = token.replace("Bearer ", "");
            //判断redis中有没有
            String realToken = (String) redis.opsForValue().get(JwtConstant.JWT_PREFIX + redisToken);
            //解析Jwt
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtConstant.ALGORITHM_HMAC256)).build();
            DecodedJWT verify = jwtVerifier.verify(realToken);
            //验证过期时间
            Date expiresAt = verify.getExpiresAt();
            int i = expiresAt.compareTo(new Date());
            if (i == -1) {
                log.info("token过期");
                result.setMsg("登录已过期");
                ResponseUtil.out(response, result);
                throw new BaseException("登录已过期");
            }
            //获取用户信息
            HashMap<String, Object> userInfo = JwtUtil.getUserInfo(realToken);
            if (userInfo.isEmpty() || userInfo == null || realToken.isEmpty()) {
                log.info("token验证失败");
                result.setMsg("未验证用户，请求失败");
                ResponseUtil.out(response, result);
                throw new BaseException("未验证用户，请求失败");
            }
            String securityUser = (String) userInfo.get("securityUser");
            List<String> auths = (List<String>) userInfo.get("auths");
            //转变权限信息
            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>(auths.size() * 2);
            auths.forEach(auth -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(auth)));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(securityUser, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("微服务过滤器校验Token异常");
            result.setMsg("微服务过滤器校验Token异常");
            ResponseUtil.out(response, result);
            throw new BaseException("微服务过滤器校验Token异常");
        }
    }


}