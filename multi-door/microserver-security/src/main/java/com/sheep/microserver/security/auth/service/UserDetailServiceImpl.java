package com.sheep.microserver.security.auth.service;

import com.sheep.microserver.common.core.dto.security.UserDTO;
import com.sheep.microserver.common.security.vo.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangyangSheep
 * @ClassName UserDetailsService.java
 * @Description security登录
 * @createTime 2021年02月03日 17:00
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用UserMapper中的方法查询数据库
      /*  QueryWrapper<com.hande.security.model.User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        com.hande.security.model.User dataBaseUser = userMapper.selectOne(wrapper);*/
        //数据库没有用户
       /* if (dataBaseUser == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        UserDTO copyBean = BeanHelperUtil.createCopyBean(dataBaseUser, UserDTO.class);*/
        //查询权限集合
      /*  List<Menu> menus = menuMapper.listByUser(dataBaseUser.getId());
        String authorityListStr = menus.stream()
                .map(Menu::getMenuAuth)
                .collect(Collectors.joining(","));*/
        //实际上是查询数据库的用户
        UserDTO copyBean = new UserDTO();
        copyBean.setUsername(username);
        copyBean.setSecret("$2a$10$glktQRTODJ/2XFTdqtmyu.tYhrWDARnQx3jzMN4XPQsuQEVLks8W2");
        String authorityListStr = "testOtherServerAuth";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityListStr);
        //从数据库查询对象中得到用户名和密码，返回
        return new SecurityUser(copyBean, authorities);
    }

}
