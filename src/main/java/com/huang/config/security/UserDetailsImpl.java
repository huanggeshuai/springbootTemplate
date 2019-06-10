package com.huang.config.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author huang
 * @Classname UserDetailsImpl
 * @Description 用户信息就相当与shiro的subject
 * @Date 2019/5/4 15:05
 * @Created by huang
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    @Getter
    @Setter
    private Long userId;

    private String userName;

    private String password;

    private List roles;

    @Getter
    @Setter
    private String salt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
