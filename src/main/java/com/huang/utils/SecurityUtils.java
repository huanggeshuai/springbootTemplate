package com.huang.utils;

import com.huang.config.sys.SysInfo;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description 包含获取盐 获取登入用户等方法
 * @auther huang
 * @create 2019-05-14 11:42
 */
public class SecurityUtils {

    /**
     * 获取盐
     * @return
     */
    public static String getSalt(){
        return BCrypt.gensalt((int) (Math.random()*27+4));
    }

    /**
     *
     * @param rawPassword 原密码
     * @param salt 盐值
     * @return
     */
    public static String getPassword(String rawPassword,String salt){
        //根据name获取spring容器对应的bean
        PasswordEncoder passwordEncoder = SpringUtils.getBean("bcrypt");
        SysInfo sysInfo = SpringUtils.getBean("sysInfo");
        String password = rawPassword+sysInfo.getDefaultSalt()+salt;
        return passwordEncoder.encode(password);
    }

}
