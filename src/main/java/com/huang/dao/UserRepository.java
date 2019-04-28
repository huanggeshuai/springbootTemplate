package com.huang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.huang.entity.User;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}