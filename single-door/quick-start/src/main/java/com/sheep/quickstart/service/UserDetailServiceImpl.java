package com.sheep.quickstart.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheep.quickstart.entity.MyUser;
import com.sheep.quickstart.mapper.MyUserMapper;
import com.sheep.quickstart.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyUserMapper myUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //以下为数据库查询操作
        QueryWrapper<MyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        MyUser myUser = myUserMapper.selectOne(queryWrapper);
        if (myUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        /*使用security自己默认的User*/
        //List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("secAdmin");
        //return new User("secUser", PasswordEncoderUtil.encode("secUser"), auths);

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("jdbcAdmin");
        return new User(myUser.getUsername(), PasswordEncoderUtil.encode(myUser.getPassword()), auths);
    }
}
