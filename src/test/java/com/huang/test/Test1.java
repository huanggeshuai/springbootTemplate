package com.huang.test;

import base.BaseTest;
import com.huang.entity.User;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huang
 * @Classname Test
 * @Description TODO
 * @Date 2019/4/27 18:28
 * @Created by huang
 */
@Slf4j
public class Test1 extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void show(){
        User user = User.builder().username("spring").password("admin").build();
        userService.addUser(user);
        //log.error("result11111:"+userService.getUserByName("admin"));
    }

}
