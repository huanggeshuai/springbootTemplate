package com.huang.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @Classname SuccessHandle
 * @Description spring security 自定义登入成功处理
 * @Date 2019/5/19 10:49
 * @Created by huang
 */
@Component("successHandle")
public class SuccessHandle implements AuthenticationSuccessHandler {
    /**
     * 这个方法是专门处理用户认证成功的 authentic 认证
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("success",true);
        successMap.put("msg","");
        String info = JSON.toJSONString(successMap);
        response.getWriter().write(info);
    }
}
