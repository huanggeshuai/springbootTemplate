package com.huang.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/test")
    @ResponseBody
    public Object test() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Map map = new HashMap();
        map.put("11",11);
        map.put("sad",123);
        return map;
    }
}
