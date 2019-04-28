package com.huang.config.setup;

import javassist.tools.reflect.Reflection;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author huang
 * @Classname Init
 * @Description TODO
 * @Date 2019/4/22 21:57
 * @Created by huang
 */
@Component
public class Initialize  {

   final static private String packName = "com.huang";


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
    }
}
