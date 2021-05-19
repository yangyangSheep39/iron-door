package com.sheep.microserver.otherserver.controller;

import com.sheep.microserver.common.security.controller.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyangSheep
 * @ClassName OtherServerController.java
 * @Description 测试在其他服务中获取security认证信息的Controller
 * @createTime 2021年05月19日 21:35
 */
@RestController
@RequestMapping("/otherServer")
public class OtherServerController extends BaseController {

    @PostMapping("/testOtherServerAuth")
    @PreAuthorize("hasAuthority('testOtherServerAuth')")
    public void testOtherServerAuth() {
        System.out.println(getUserInfo());
    }

    @PostMapping("/testOtherServerNoAuth")
    @PreAuthorize("hasAuthority('testOtherServerNoAuth')")
    public void testOtherServerNoAuth() {
        System.out.println(getUserInfo());
    }
}
