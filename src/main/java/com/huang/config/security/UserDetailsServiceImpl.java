package com.huang.config.security;

import com.huang.config.sys.SysInfo;
import com.huang.entity.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author huang
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/5/4 15:14
 * @Created by huang
 */
@Service("userdeail")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysInfo sysInfo;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        String password = user.getPassword() + sysInfo.getDefaultSalt();
        UserDetailsImpl userDetails = new UserDetailsImpl(user.getUserid(),user.getUsername(),user.getPassword(),null);
        return userDetails;
    }
}
