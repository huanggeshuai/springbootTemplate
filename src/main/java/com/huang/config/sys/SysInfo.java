package com.huang.config.sys;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName SysInfo
 * @Description
 * @auther huang
 * @create 2019-05-14 13:35
 */
@Component("sysInfo")
@ConfigurationProperties(prefix = "sysinfo")
@Getter
@Setter
public class SysInfo {

    /**
     * 默认用户
     */
    private String defaultUserName;

    /**
     * 默认用户密码
     */
    private String defaultPassword;

    /**
     * 默认加密盐
     */
    private String defaultSalt;

    /**
     * jwt默认生存时间
     */
    private Long ttlMillis;
}
