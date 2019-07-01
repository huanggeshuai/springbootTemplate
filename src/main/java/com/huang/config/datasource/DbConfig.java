package com.huang.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author huang
 * @Classname DbConfig
 * @Description TODO
 * @Date 2019/4/20 14:38
 * @Created by huang
 */
@Slf4j
@Configuration
public class DbConfig {
    @Autowired
    private DbBean dbBean;

    @Bean(name = "primaryDataSource",initMethod = "init")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //数据库地址
        dataSource.setUrl(dbBean.getUrl());
        //数据库驱动
        dataSource.setDriverClassName(dbBean.getDriver());
        //设置用户名
        dataSource.setUsername(dbBean.getUsername());
        dataSource.setPassword(dbBean.getOrginPassword());

        //如果解码true 进行密码解密
        if(dbBean.getDecrypt()){
            dataSource.setPassword(dbBean.getPassword());
            dataSource.setConnectionProperties(dbBean.getConnectionProperties()+dbBean.getPublicKey());
        }
        //设置过滤器
        try {
            dataSource.setFilters(dbBean.getFilters());
        } catch (SQLException e) {
            log.error(e.getSQLState());
        }
        //建立初始化大小
        dataSource.setInitialSize(dbBean.getInitialSize());
        //最小空闲数
        dataSource.setMinIdle(dbBean.getMinIdle());
        //最大活跃数
        dataSource.setMaxActive(dbBean.getMaxActive());
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(dbBean.getMaxWait());
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(dbBean.getTimeBetweenEvictionRunsMillis());
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(dbBean.getMinEvictableIdleTimeMillis());
        //配置验证sql 判断是不是mysql数据库
        dataSource.setValidationQuery(dbBean.getValidationQuery());
        dataSource.setTestWhileIdle(dbBean.getTestWhileIdle());
        dataSource.setTestOnBorrow(dbBean.getTestOnBorrow());
        dataSource.setTestOnReturn(dbBean.getTestOnReturn());
        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(dbBean.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(dbBean.getMaxPoolPreparedStatementPerConnectionSize());
        //超过时间限制是否回收
        dataSource.setRemoveAbandoned(dbBean.getRemoveAbandoned());
        //超时时间；单位为秒
        dataSource.setRemoveAbandonedTimeout(dbBean.getRemoveAbandonedTimeout());

        return dataSource;
    }


    /**
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","admin");
        return registrationBean;
    }

    public FilterRegistrationBean druidFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        return filterRegistrationBean;
    }
}
