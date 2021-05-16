package com.sheep.csrftest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyangSheep
 * @ClassName FirstController.java
 * @Description Spring Security第一步体验
 * @createTime 2021年05月09日 16:09
 */
@RestController
public class LoginController {

    @RequestMapping("/userLogin")
    public String login() {
        return "/login/login";
    }
}
