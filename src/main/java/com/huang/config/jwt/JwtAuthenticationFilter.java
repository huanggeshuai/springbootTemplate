package com.huang.config.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author huang
 * @Classname JwtAuthenticationFilter
 * @Description 前后端分离 需要重写AbstractAuthenticationProcessingFilter
 * AbstractAuthenticationProcessingFilter这个类主要做身份认证的
 * @Date 2019/5/25 15:32
 * @Created by huang
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(new AntPathRequestMatcher("/login","POST"));
    }

    /**
     * 封装前端传来的用户信息
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //从http流获取文本信息
        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        String userName = null,password = null;
        if(StringUtils.hasText(body)){
            JSONObject userInfo = JSON.parseObject(body);
            userName = userInfo.getString("userName").trim();
            password = userInfo.getString("password").trim();
        }
        if(ObjectUtils.isEmpty(userName)){
            userName = "";
        }
        if(ObjectUtils.isEmpty(password)){
            password = "";
        }
        //封装用户token信息 这个时候token还没有经过校验
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
