package com.huang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.huang.entity.Function;
import org.springframework.stereotype.Repository;

public interface FunctionRepository extends JpaRepository<Function, Integer>, JpaSpecificationExecutor<Function> {

}