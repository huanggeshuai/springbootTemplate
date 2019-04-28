package com.huang.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
public class RoleFunctionKey implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 角色id
   */

  private Integer roleId;

  /**
   * 功能id
   */

  private Integer functionId;

  
}