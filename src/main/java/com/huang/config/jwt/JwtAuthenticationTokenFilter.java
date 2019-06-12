package com.huang.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huang.config.security.FailureHandle;
import com.huang.config.security.SuccessHandle;
import com.huang.config.security.UserDetailsImpl;
import com.huang.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huang
 * @Classname JwtAuthenticationTokenFilter
 * @Description 对带token信息的请求处理
 * @Date 2019/5/21 21:10
 * @Created by huang
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("userdeail")
    private UserDetailsService userService;

    @Autowired
    private FailureHandle failureHandle;

    @Autowired
    private SuccessHandle successHandle;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();


    String getJwtToken(HttpServletRequest request){
        String jwt = request.getHeader("authentic");
        return jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenInfo = getJwtToken(request);
        if(ObjectUtils.isEmpty(tokenInfo)){
            //如果没有认证信息，直接放行
            filterChain.doFilter(request,response);
            return;
        }
        Authentication authentication = null;
        AuthenticationException failed = null;
        try {
            DecodedJWT decodedJWT = JWT.decode(tokenInfo);
            String userName = decodedJWT.getClaims().get("userName").asString();
            UserDetails userDetails = userService.loadUserByUsername(userName);
            if(ObjectUtils.isEmpty(userDetails)){
                failed = new InsufficientAuthenticationException("jwt is null");
            }
            //校验jwt token信息
            Claims claims = JwtUtils.paraseJWT(tokenInfo, userDetails.getPassword());


            List list = new ArrayList();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admin:main");
            list.add(grantedAuthority);


            //封装usernamePasswordAuthenticationToken token信息
             authentication =
                    new UsernamePasswordAuthenticationToken(userDetails,null, list);
             ((UsernamePasswordAuthenticationToken) authentication).
                     setDetails(authenticationDetailsSource.buildDetails(request));
             //将数据封装在context中
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (JWTDecodeException e){
            failed =new InsufficientAuthenticationException("jwt formatter error");;
        }
        catch (IllegalArgumentException e){
            failed =new InsufficientAuthenticationException("jwt formatter error");;

        }catch (ExpiredJwtException e){
            //时间过期 说明jwt的时间过了
            failed =new InsufficientAuthenticationException("jwt timeout");;

        }catch (SignatureException e){
            //签名异常说明 校验不正确
            failed =new InsufficientAuthenticationException("jwt sign error");;

        }catch (AuthenticationException e){
            failed = e;
        }
        //将异常信息抛出
        if(!ObjectUtils.isEmpty(failed)){
            failureHandle.onAuthenticationFailure(request,response,failed);
            return;
        }else {
           // successHandle.onAuthenticationSuccess(request, response, authentication);
        }
        filterChain.doFilter(request,response);
    }
}
