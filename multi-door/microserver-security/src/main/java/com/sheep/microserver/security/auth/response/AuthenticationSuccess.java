package com.sheep.microserver.security.auth.response;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sheep.microserver.common.core.constant.JwtConstant;
import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.core.utils.Md5Util;
import com.sheep.microserver.common.core.utils.ResponseUtil;
import com.sheep.microserver.common.security.utils.JwtUtil;
import com.sheep.microserver.common.security.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yangyangSheep
 * @ClassName AuthenticationSuccess.java
 * @Description 自定义登录成功后的json返回
 * @createTime 2021/2/27 15:51
 */
@Component
@Slf4j
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("security登录验证");
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        String securityUser = JSON.toJSONString(principal);
        //用户名
        //获取权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityList = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //获取Jwt
        String jwt = JwtUtil.createJwt(securityUser, authorityList);
        //保存jwt
        try {
            redisTemplate.opsForValue().set(JwtConstant.JWT_PREFIX + Md5Util.string2MD5(jwt), jwt, JwtConstant.EXPIRE_TIME, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("存储登录Token到Redis失败");
        }
        //返回jwt
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("access_token", Md5Util.string2MD5(jwt));
        map.put("expire_time", JwtConstant.EXPIRE_TIME);
        map.put("type", "Bearer");
        ResponseUtil.out(httpServletResponse, Result.ok(map, ErrorCodeEnum.PC100031));
    }
}
