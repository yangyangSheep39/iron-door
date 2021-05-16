package com.sheep.advanced.monomer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangyangSheep
 * @ClassName FirstController.java
 * @Description Spring Security第一步体验
 * @createTime 2021年05月09日 16:09
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Security!";
    }

    @GetMapping("/successful")
    public String successful() {
        return "Login Successful!";
    }

    @GetMapping("/successfulUrl")
    public ModelAndView successfulUrl(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = "redirect:/loginSuccessful.html";
        return new ModelAndView(url);
    }

    @GetMapping("/getSec")
    public String getSec() {
        return "Hello GetSec!";
    }

    @GetMapping("/logOut")
    public String logOut() {
        return "LogOut Success!";
    }
}
