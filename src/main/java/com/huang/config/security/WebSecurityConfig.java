package com.huang.config.security;

import com.huang.config.jwt.JwtAuthenticationFilter;
import com.huang.config.jwt.JwtAuthenticationTokenFilter;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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

    @Autowired
    private SuccessHandle successHandle;

    @Autowired
    private FailureHandle failureHandle;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.formLogin().successHandler(successHandle).failureHandler(failureHandle);
       // super.configure(http);
        //禁用csrf，应为不使用session
        http.csrf().disable()
                //禁用sessionManage 禁用session
                .sessionManagement().disable()
                //禁用form表单登入
                .formLogin().disable()
                //支持跨域
                .cors()
                .and()   //添加header设置，支持跨域和ajax请求
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","authentic"))))
                .and() //拦截OPTIONS请求，直接返回header
                .addFilterAt(jwtAuthenticationFilter(), LogoutFilter.class)

                .addFilterAfter(jwtAuthenticationTokenFilter,jwtAuthenticationFilter().getClass());

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

    @Bean(name = "jwtauthenication")
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(successHandle);
        jwtAuthenticationFilter.setAuthenticationFailureHandler(failureHandle);
        jwtAuthenticationFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
        jwtAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        return jwtAuthenticationFilter;
    }
}
