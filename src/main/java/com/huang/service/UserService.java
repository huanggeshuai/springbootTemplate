package com.huang.service;

import com.huang.entity.User;

import java.util.List;

/**
 * @author huang
 * @Classname UserService
 * @Description TODO
 * @Date 2019/4/28 20:44
 * @Created by huang
 */
public interface UserService {
    long count();

    List<User> allUsr();
}
