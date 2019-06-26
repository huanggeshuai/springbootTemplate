package com.huang.test;

import base.BaseTest;
import com.huang.entity.MongoDB;
import com.huang.entity.User;
import com.huang.service.MongoDBService;
import com.huang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

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

    @Autowired
    MongoDBService mongoDBService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void show(){
        User user = User.builder().username("spring").password("admin").build();
        userService.addUser(user);
        //log.error("result11111:"+userService.getUserByName("admin"));
    }

    @Test
    public void mongo(){
        System.out.println(mongoDBService.findAll());
    }

    @Test
    public void mongoinsert(){
        User user = User.builder().password("qwewqe").username("huangg").userColor("yellow")
                .roleId(1).salt("ass").build();
        MongoDB mongoDB = MongoDB.builder().name("hahha").user(user).build();
        mongoDBService.add(mongoDB);
    }

    @Test
    public void redis(){
        redisTemplate.opsForSet().add("test","sasdsasd");
    }

}
