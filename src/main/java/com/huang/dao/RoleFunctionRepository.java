package com.huang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.huang.entity.RoleFunction;
import org.springframework.stereotype.Repository;

public interface RoleFunctionRepository extends JpaRepository<RoleFunction, Integer>, JpaSpecificationExecutor<RoleFunction> {

}