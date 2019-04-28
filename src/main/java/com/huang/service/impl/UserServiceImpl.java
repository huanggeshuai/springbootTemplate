package com.huang.service.impl;

import com.huang.dao.UserRepository;
import com.huang.entity.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huang
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/4/28 20:44
 * @Created by huang
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<User> allUsr() {
        return repository.findAll();
    }
}
