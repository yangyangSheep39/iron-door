package com.sheep.csrftest.service;

import com.sheep.csrftest.utils.PasswordEncoderUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangyangSheep
 * @ClassName UserDetailServiceImpl.java
 * @createTime 2021/05/13 16:16
 * @Description UserDetailsService的实现类
 */
@Service("myUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("hasAuthorityConfigAdmin,ROLE_hasRoleConfigAdmin,ROLE_testSecured,testPreAuthorize");
        return new User("Lucy", PasswordEncoderUtil.encode("123"), auths);
    }
}
