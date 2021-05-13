package com.sheep.advanced.monomer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getSec")
    public String getSec() {
        return "Hello GetSec!";
    }
}
