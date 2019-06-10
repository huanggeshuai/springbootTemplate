package com.huang.config.jwt;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huang
 * @Classname JwtAuthenticationTokenFilter
 * @Description 对带token信息的请求处理
 * @Date 2019/5/21 21:10
 * @Created by huang
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    String getJwtToken(HttpServletRequest request){
        String jwt = request.getHeader("authority");
        return jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenInfo = getJwtToken(request);
        if(ObjectUtils.isEmpty(tokenInfo)){

        }
    }
}
