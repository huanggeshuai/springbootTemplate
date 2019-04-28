package com.huang.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "log")
@Entity
public class Log implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, name = "logid", nullable = false)
  private Integer logid;

  /**
   * msg 操作信息
   */
  @Column(name = "msg")
  private String msg;

  /**
   * 存储异常信息
   */
  @Column(name = "exception")
  private String exception;

  /**
   * 访问的方法名称
   */
  @Column(name = "method_name")
  private String methodName;

  /**
   * 操作人id
   */
  @Column(name = "userid")
  private Integer userid;

  /**
   * 操作时间
   */
  @Column(name = "operate_time")
  private Date operateTime;

  /**
   * 存储ip地址
   */
  @Column(name = "ip")
  private String ip;

  
}