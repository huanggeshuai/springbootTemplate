package com.huang.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 角色id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id", insertable = false, nullable = false)
  private Integer roleId;

  /**
   * 角色名称
   */
  @Column(name = "rold_name")
  private String roldName;

  /**
   * 描述
   */
  @Column(name = "des")
  private String des;

  /**
   * 角色标识
   */
  @Column(name = "role_code")
  private String roleCode;

  @Column(name = "value", nullable = false)
  private Integer value;

  
}