package com.sheep.microserver.common.security.controller;

import cn.hutool.json.JSONUtil;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.security.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyangSheep
 * @Date: 2021/2/7 11:02
 */
@Slf4j
@RestController
public class BaseController {

    /**
     * 获取完整的security账户信息
     */
    public SecurityUser getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = (String) authentication.getPrincipal();
        SecurityUser securityUser = JSONUtil.toBean(principal, SecurityUser.class);
        //TODO
        //保密信息需要进行隐藏的在此处进行设置
        securityUser.getCurrentUserInfo().setSecret("");
        return securityUser;
    }

    /**
     * 返回当前登录用户信息
     *
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfoForView() {
        return Result.ok(getUserInfo());
    }
}
