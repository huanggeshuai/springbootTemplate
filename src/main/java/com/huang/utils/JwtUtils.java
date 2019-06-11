package com.huang.utils;

import com.google.common.collect.Maps;
import com.huang.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author huang
 * @Classname JWTUtils
 * @Description 生成tokern 解析token 校验token
 * jwt由三部分组成，head，payload，Signature，其中head和payload是由base64加密的，是对称加密，所以不要把私密信息放在payload里
 * payload是存放数据的，而signature是签名，其中签名的密钥必须要放在服务器，不能暴露给用户，不然的话他们可以随意签名了
 * @Date 2019/5/22 21:47
 * @Created by huang
 */
public class JwtUtils {

    /**
     * 用户登入成功后创建jwt信息返回给前端页面
     * @param ttlMillis 生存时间
     * @param userId 用户唯一标识
     * @param userName 用户名称
     * @param password 用户密码
     * @return
     */
    public static String createJWT(long ttlMillis, Long userId, String userName, String password){
        //设置生成算法这是使用hash256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date date = new Date();

        //创建clains私有声明
        Map<String, Object> clains = Maps.newHashMap();
       // clains.put("userId", userId);
       // clains.put("password", password);
        clains.put("userName", userName);

        //设置jwt过期时间
        long exit = date.getTime() + ttlMillis;
        Date exitDate = new Date(exit);

        //签名密钥
        String key = password;
        //生成签发人
        String subject = userName;
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
                .signWith(signatureAlgorithm,key)
                //设置过期时间
                .setExpiration(exitDate);


        //将所设置的参数转成字符串 就这个意思
        return jwtBuilder.compact();
    }

    /**
     * 用户登入成功后创建jwt信息返回给前端页面
     * @param ttlMillis jwt信息生存时间
     * @param user 当前用户
     * @return
     */
    public static String createJWT(long ttlMillis, User user){
        return createJWT(ttlMillis, user.getUserid(), user.getUsername(), user.getPassword());
    }

    /**
     * Token的解密
     * @param token
     * @param password 用户的密码
     * @return
     */
    public static Claims paraseJWT(String token, String password){
        //签名密钥，和生成的密钥一模一样
        String key = password;

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
     * @param userName 用户名
     * @param password 用户密码
     * @return
     */
    public static boolean isVerify(String token, String userName, String password){

        //签名密钥 和生成的签名的密钥一模一样
        String key = password;

        Claims claims = paraseJWT(token,password);

        if(claims.get("password").equals(password) && claims.get("userName").equals(userName)){
            return true;
        }
        return false;
    }

    /**
     * 根据key获取相应的value
     * @param token
     * @param key
     * @param password token的密钥用来解密token
     * @return
     */
    public static String JwtValue(String token, String key, String password){
        Claims claims = paraseJWT(token,password);
        String value = String.valueOf(claims.get(key));
        if("null".equals(value)){
            return null;
        }
        return  value;
    }
}
