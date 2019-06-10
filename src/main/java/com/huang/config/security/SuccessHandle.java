package com.huang.config.security;

import com.alibaba.fastjson.JSON;
import com.huang.config.sys.SysInfo;
import com.huang.service.UserService;
import com.huang.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysInfo sysInfo;

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
        String userName = authentication.getName();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("success",true);
        //封装token
        String tokenInfo = JwtUtils.createJWT(sysInfo.getTtlMillis(),userDetails.getUserId(), userDetails.getUsername(),userDetails.getPassword());
        successMap.put("msg", tokenInfo);
        String info = JSON.toJSONString(successMap);
        response.setHeader("authority", tokenInfo);
        response.getWriter().write(info);
    }
}
