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
@Table(name = "function")
@Entity
public class Function implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 系统功能id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "function_id", insertable = false, nullable = false)
  private Integer functionId;

  /**
   * 系统功能父id
   */
  @Column(name = "parent_id")
  private Integer parentId;

  /**
   * 功能名称
   */
  @Column(name = "function_name")
  private String functionName;

  /**
   * 功能描述
   */
  @Column(name = "function_des")
  private String functionDes;

  /**
   * 路径
   */
  @Column(name = "url")
  private String url;

  /**
   * 功能说明
   */
  @Column(name = "text")
  private String text;

  @Column(name = "state")
  private String state;

  /**
   * 权限代码
   */
  @Column(name = "code")
  private String code;

  /**
   * 图标名称
   */
  @Column(name = "icon")
  private String icon;

  /**
   * 0代表按钮'button' 1代表页面'page'
   */
  @Column(name = "type")
  private Integer type;

  @Column(name = "value", nullable = false)
  private Integer value;

  
}