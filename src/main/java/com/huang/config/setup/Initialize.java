package com.huang.config.setup;

import com.huang.config.sys.SysInfo;
import com.huang.entity.User;
import com.huang.service.UserService;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @author huang
 * @Classname Init
 * @Description TODO
 * @Date 2019/4/22 21:57
 * @Created by huang
 */
@Component
@DependsOn("springUtils") // 等springUtils注入完成之后在注入这个类
public class Initialize  {

   final static private String packName = "com.huang";

   @Autowired
   private UserService userService;

    @Autowired
    private SysInfo sysInfo;

    @PostConstruct
    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //扫描所有的包
        Reflections reflection = new Reflections(packName);
        //获取所有setup接口的集合
        Set<Class<? extends Setup>> set = reflection.getSubTypesOf(Setup.class);
        //循环遍历该接口实现类
        for (Class<? extends Setup> classz : set) {
            //判断是不是抽象类
            if (!Modifier.isAbstract(classz.getModifiers()) && !Modifier.isInterface(classz.getModifiers())) {
                //通过反射获取该接口实现的方法
                Method method = classz.getMethod("Setup");
                //执行该接口方法
                method.invoke(classz.getConstructor().newInstance());
            }
        }
            //如果用户表没有数据，就创建一条数据
            if(userService.count() == 0){
                User user = User.builder().username(sysInfo.getDefaultUserName())
                        .password(sysInfo.getDefaultPassword()).build();
                userService.addUser(user);
            }
        }

}
