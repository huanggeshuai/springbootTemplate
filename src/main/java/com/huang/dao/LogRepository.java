package com.huang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.huang.entity.Log;
import org.springframework.stereotype.Repository;

public interface LogRepository extends JpaRepository<Log, Integer>, JpaSpecificationExecutor<Log> {

}