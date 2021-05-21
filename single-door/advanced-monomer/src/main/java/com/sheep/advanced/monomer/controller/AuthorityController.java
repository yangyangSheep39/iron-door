package com.sheep.advanced.monomer.controller;

import com.sheep.advanced.monomer.entity.MyUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyangSheep
 * @ClassName AuthorityController.java
 * @Description 用户授权测试类
 * @createTime 2021年05月16日 17:32
 */
@RestController
@RequestMapping("/auth")
public class AuthorityController {

    /**
     * 只有包含权限才能够访问这个接口
     */
    @GetMapping("/hasAuthorityConfig")
    public String hasAuthorityConfig() {
        return "hasAuthority Test!";
    }

    /**
     * 包含任意需要的权限才能够访问这个接口
     */
    @GetMapping("/hasAnyAuthorityConfig")
    public String hasAnyAuthorityConfig() {
        return "hasAnyAuthority Test!";
    }

    /**
     * 包含限定角色才能够访问这个接口
     */
    @GetMapping("/hasRoleConfig")
    public String hasRoleConfig() {
        return "hasRoleConfig Test!";
    }

    /**
     * 包含任意需要的角色才能够访问这个接口
     */
    @GetMapping("/hasAnyRoleConfig")
    public String hasAnyRoleConfig() {
        return "hasAnyRoleConfig Test!";
    }

    /**
     * 包含注解中需要的角色才能够访问这个接口
     */
    @GetMapping("/testSecured")
    @Secured({"ROLE_testSecured", "ROLE_testSecured2"})
    public String testSecured() {
        return "Secured Test!";
    }

    /**
     * 方法执行之前验证权限
     */
    @GetMapping("/testPreAuthorize")
    //@PreAuthorize("hasRole('testPreAuthorize')")
    //@PreAuthorize("hasAnyRole('testPreAuthorize,test')")
    @PreAuthorize("hasAuthority('testPreAuthorize')")
    //@PreAuthorize("hasAnyAuthority('testPreAuthorize')")
    public String testPreAuthorize() {
        return "PreAuthorize Test!";
    }

    /**
     * 方法执行之后进行权限验证
     */
    @GetMapping("/testPostAuthorize")
    @PostAuthorize("hasAuthority('testPostAuthorize')")
    public String testPostAuthorize() {
        System.out.println("==============================方法体执行中==============================");
        return "testPostAuthorize";
    }

    /**
     * 方法执行之后进行数据过滤
     */
    @GetMapping("/testPostFilter")
    @PostFilter(value = "filterObject.id%2 == 0")
    public ArrayList<MyUser> testPostFilter() {
        MyUser user1 = new MyUser();
        user1.setId(1L).setUsername("11").setName("11").setPassword("11").setDescription("11");
        MyUser user2 = new MyUser();
        user2.setId(2L).setUsername("22").setName("22").setPassword("22").setDescription("22");
        ArrayList arrayList = new ArrayList();
        arrayList.add(user1);
        arrayList.add(user2);
        System.out.println("==============================方法体执行中==============================");
        System.out.println(arrayList);
        return arrayList;
    }

    /**
     * 过滤传入的数据
     */
    @GetMapping("/testPreFilter")
    @PreFilter(value = "filterObject.id%2 == 0")
    public List<MyUser> preFilter(@RequestBody List<MyUser> list) {
        list.forEach(System.out::println);
        return list;
    }

}
