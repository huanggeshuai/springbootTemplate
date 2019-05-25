package com.huang.utils;

import com.google.common.collect.Maps;
import com.huang.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author huang
 * @Classname JWTUtils
 * @Description 生成tokern 解析token 校验token
 * @Date 2019/5/22 21:47
 * @Created by huang
 */
public class JwtUtils {

    /**
     * 用户登入成功后创建jwt信息返回给前端页面
     * @param ttlMillis jwt信息生存时间
     * @param user 当前用户
     * @return
     */
    public static String createJWT(long ttlMillis, User user){

        //设置生成算法这是使用hash256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date date = new Date();

        //创建clains私有声明
        Map<String, Object> clains = Maps.newHashMap();
        clains.put("userId",user.getUserid());
        clains.put("password",user.getPassword());
        clains.put("userName",user.getUsername());

        //签名密钥
        String key = user.getPassword();
        //生成签发人
        String subject = user.getUsername();
        //为payload添加标准声明和私有声明
        JwtBuilder jwtBuilder = Jwts.builder()
                //设置私有声明
                .setClaims(clains)
                //设置jwt唯一id
                .setId(UUID.randomUUID().toString())
                //设置jwt签发时间
                .setIssuedAt(date)
                //设置jwt 主体个人理解作为用户的唯一标识
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的密钥
                .signWith(signatureAlgorithm,key);
        if(ttlMillis >= 0){
            long exit = date.getTime() + ttlMillis;
            Date exitDate = new Date(exit);
            //设置jwt过期时间
            jwtBuilder.setExpiration(exitDate);
        }
        //将所设置的参数转成字符串 就这个意思
        return jwtBuilder.compact();
    }

    /**
     * Token的解密
     * @param token
     * @param user
     * @return
     */
    public static Claims paraseJWT(String token, User user){
        //签名密钥，和生成的密钥一模一样
        String key = user.getPassword();

        Claims claims = Jwts.parser()
                //设置声明密钥
                .setSigningKey(key)
                //格式化token
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 校验token
     * @param token
     * @param user
     * @return
     */
    public static boolean isVerify(String token, User user){

        //签名密钥 和生成的签名的密钥一模一样
        String key = user.getPassword();

        Claims claims = Jwts.parser()
                //设置签名的密钥
                .setSigningKey(key)
                //设置需要解析jwt
                .parseClaimsJwt(token)
                .getBody();
        if(claims.get("password").equals(user.getPassword()) && claims.get("userName").equals(user.getUsername())){
            return true;
        }
        return false;
    }

}
