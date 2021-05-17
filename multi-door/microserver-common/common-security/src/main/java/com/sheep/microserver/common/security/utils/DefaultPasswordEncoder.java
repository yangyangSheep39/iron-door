package com.sheep.microserver.common.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author yangyangSheep
 * @ClassName DefaultPasswordEncoder.java
 * @Description 默认的自定义密码处理器
 * @createTime 2021/2/4 14:28
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {
    }

    /**
     * 进行密码加密
     */
    @Override
    public String encode(CharSequence charSequence) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(charSequence.toString());
        return encode;
    }

    /**
     * 进行密码比对
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(charSequence, s);
    }

    public static void main(String[] args) {
        String encode = new DefaultPasswordEncoder().encode("123456");
        System.out.println(encode);
        boolean matches = new DefaultPasswordEncoder().matches("123456", encode);
        System.out.println(matches);
    }

}
