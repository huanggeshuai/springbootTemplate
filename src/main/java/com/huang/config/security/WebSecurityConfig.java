package com.huang.config.security;

import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author huang
 * @Classname WebSecurityConfig
 * @Description spring security 安全框架的配置
 * @Date 2019/4/27 17:33
 * @Created by huang
 */
@Configuration
@EnableWebSecurity
//EnableGlobalMethodSecurity就是让spring security的注解生效 就相当于aop的前置通知
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userdeail")
    private UserDetailsService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(loginAuthenticationProvider()).userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    /**
     * 加密策略 使用spring security自己的加密策略 BCryptPasswordEncoder
     * @return
     */
    @Bean(name = "bcrypt")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "loginAuthenticationProvider")
    public LoginAuthenticationProvider loginAuthenticationProvider(){
        return new LoginAuthenticationProvider(userService);
    }
}
