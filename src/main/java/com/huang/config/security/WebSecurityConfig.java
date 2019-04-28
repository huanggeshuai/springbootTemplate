package com.huang.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author huang
 * @Classname WebSecurityConfig
 * @Description spring security 安全框架的配置
 * @Date 2019/4/27 17:33
 * @Created by huang
 */
/*@Configuration
@EnableWebSecurity
*//** EnableGlobalMethodSecurity就是让spring security的注解生效 就相当于aop的前置通知*//*
@EnableGlobalMethodSecurity(prePostEnabled = true)*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

}
