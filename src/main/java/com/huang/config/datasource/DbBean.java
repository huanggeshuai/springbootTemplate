package com.huang.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author huang
 * @Classname DbBean
 * @Description 数据库属性bean
 * @Date 2019/4/20 14:13
 * @Created by huang
 */
@Setter
@Getter
//批量注入属性 去除data前缀
@ConfigurationProperties(prefix = "data")
@Component
public class DbBean {

    private String type;

    private String driver;

    private String url;

    private String username;

    private String orginPassword;

    private String password;

    private String publicKey;

    private String privateKey;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxIdle;

    private Integer maxWait;

    private Boolean defaultAutoCommit;

    private Boolean removeAbandoned;

    private Integer removeAbandonedTimeout;

    private Integer whenExhaustedAction;

    private String validationQuery;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private Boolean testWhileIdle;

    private Boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    private Boolean decrypt;



}
