package com.huang.config.cache;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author huang
 * @Classname RedisCacheConfig
 * @Description redis缓存管理
 * @Date 2019/6/26 21:24
 * @Created by huang
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * keyGenerator key生成器
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(target.getClass().getName());
                stringBuilder.append(method.getName());
                for (Object param : params){
                    stringBuilder.append(params.getClass().getName());
                }
                return stringBuilder.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        //初始化cacheWriter
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        //设置生存时间
        redisCacheConfiguration.entryTtl(Duration.ofMinutes(30));
        //初始化cacheManage
        RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter,redisCacheConfiguration);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        //使用fastjson 序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        template.setDefaultSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


}
