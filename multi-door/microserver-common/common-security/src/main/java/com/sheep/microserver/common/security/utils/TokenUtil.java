package com.sheep.microserver.common.security.utils;

import cn.hutool.json.JSONUtil;
import com.sheep.microserver.common.security.vo.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author zhj
 * @since 2021-03-15
 * @apiNote 解析token 获取用户信息
 */
@Component
public class TokenUtil {
    public SecurityUser getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = (String) authentication.getPrincipal();
        SecurityUser securityUser = JSONUtil.toBean(principal, SecurityUser.class);
        //TODO
        //保密信息需要进行隐藏的在此处进行设置
        securityUser.getCurrentUserInfo().setSecret("");
        return securityUser;
    }
}
