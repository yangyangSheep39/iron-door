package com.sheep.microserver.security.controller;

import com.sheep.microserver.common.core.constant.JwtConstant;
import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangyangSheep
 * @ClassName IndexController.java
 * @Description 默认的借口列表
 * @createTime 2021/3/12 15:38
 */
@Slf4j
@RestController
public class AccessController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登出，删除redis的JWT键
     *
     * @return
     */
    @PostMapping("/outLogin")
    public Result myLogout() {
        //拿到Token删除
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String header = request.getHeader(JwtConstant.AUTHORIZATION);
        if (StringUtils.hasText(header)) {
            String jwt = header.replace("Bearer ", "");
            if (StringUtils.hasText(jwt)) {
                try {
                    redisTemplate.delete(JwtConstant.JWT_PREFIX + jwt);
                } catch (Exception e) {
                    log.info("登出时删除Token错误");
                    e.printStackTrace();
                }
            }
        }
        return Result.ok(null, ErrorCodeEnum.PC100032);
    }
}
