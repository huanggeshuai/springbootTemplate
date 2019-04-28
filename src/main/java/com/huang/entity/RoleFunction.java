package com.huang.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import org.hibernate.mapping.PrimaryKey;

@Table(name = "role_function")
@Entity
@Data
@IdClass(RoleFunctionKey.class)
public class RoleFunction implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 角色id
   */
  @Id
  @Column(name = "role_id", insertable = false, nullable = false)
  private Integer roleId;

  /**
   * 功能id
   */
  @Id
  @Column(name = "function_id", insertable = false, nullable = false)
  private Integer functionId;

  
}