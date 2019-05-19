package com.huang.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @Classname FailureHandle
 * @Description TODO
 * @Date 2019/5/19 14:32
 * @Created by huang
 */
@Component("failureHandle")
public class FailureHandle implements AuthenticationFailureHandler {
    /**
     * 这个方法专门处理用户认证失败的异常
     * @param request
     * @param response
     * @param exception 认证失败的异常信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("success",false);
        successMap.put("msg",exception.getMessage());
        String info = JSON.toJSONString(successMap);
        response.getWriter().write(info);
    }
}
