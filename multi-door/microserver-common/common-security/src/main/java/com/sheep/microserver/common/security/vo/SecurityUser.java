package com.sheep.microserver.common.security.vo;

import com.sheep.microserver.common.core.dto.security.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author yangyangSheep
 * @ClassName SecurityUser.java
 * @Description security的用户详细信息
 * @createTime 2021/2/20 14:10
 */
@Data
@EqualsAndHashCode
public class SecurityUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -295727152234412833L;
    /**
     * 当前登录用户
     */
    private UserDTO currentUserInfo;

    /**
     * 权限数据
     */
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser() {
    }

    public SecurityUser(UserDTO user) {
        if (user != null) {
            this.currentUserInfo = user;
        }
    }

    public SecurityUser(UserDTO currentUserInfo, Collection<? extends GrantedAuthority> authorities) {
        this.currentUserInfo = currentUserInfo;
        this.authorities = authorities;
    }


    @Override
    public String getPassword() {
        return currentUserInfo.getSecret();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

