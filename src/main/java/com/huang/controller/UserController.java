package com.huang.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @Classname UserController
 * @Description TODO
 * @Date 2019/5/3 21:36
 * @Created by huang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String getUsers() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return "Hello Spring Security";
    }
}
