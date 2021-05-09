package com.sheep.quickstart.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author yangyangSheep
 * @ClassName DefaultPasswordEncoder.java
 * @Description 密码加密工具
 * @createTime 2021年05月09日 17:46
 */
@Component
public class PasswordEncoderUtil {
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 进行密码加密
     */
    public static String encode(CharSequence charSequence) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(charSequence.toString());
        return encode;
    }

    /**
     * 进行密码比对
     */
    public boolean matches(CharSequence charSequence, String s) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(charSequence, s);
    }

}
