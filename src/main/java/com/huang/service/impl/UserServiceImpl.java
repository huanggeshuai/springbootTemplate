package com.huang.service.impl;

import com.huang.dao.UserRepository;
import com.huang.entity.User;
import com.huang.service.UserService;
import com.huang.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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

    @Override
    public void delUser() {
        repository.deleteAll();
    }

    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        user.setSalt(SecurityUtils.getSalt());
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public User getUserByName(String userName) {
        User user = User.builder().username(userName).build();
        Example<User> example = Example.of(user);
        return repository.findOne(example).get();
    }
}
