package com.huang.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 用户id 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userid", insertable = false, nullable = false)
  private Integer userid;

  /**
   * 用户名称
   */
  @Column(name = "username")
  private String username;

  /**
   * 用户密码 加密的
   */
  @Column(name = "password")
  private String password;

  /**
   * 盐 混淆加密
   */
  @Column(name = "salt")
  private String salt;

  /**
   * 角色编号
   */
  @Column(name = "role_id")
  private Integer roleId;

  /**
   * 用户头像
   */
  @Column(name = "user_icon")
  private String userIcon;

  /**
   * 用户颜色
   */
  @Column(name = "user_color")
  private String userColor;

  
}