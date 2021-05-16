package com.sheep.csrftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author yangyangSheep
 * @ClassName CsrfController.java
 * @Description
 * @createTime 2021年05月16日 20:29
 */
@Controller
public class CsrfController {

    @GetMapping("/toupdate")
    public String test(Model model) {
        return "csrf/csrfTest";
    }

    @PostMapping("/update_token")
    public String updateToken(Model model) {
        return "csrf/csrf_token";
    }
}
