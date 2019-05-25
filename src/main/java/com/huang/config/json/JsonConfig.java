package com.huang.config.json;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huang
 * @Classname JsonConfig
 * @Description 配置json解析器
 * @Date 2019/5/22 21:53
 * @Created by huang
 */
@Configuration
public class JsonConfig {

    /**
     * 设置fastjson
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonMessageConverteer(){

        FastJsonHttpMessageConverter jsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);

        List<MediaType> fastMedia = Lists.newArrayList();
        fastMedia.add(MediaType.APPLICATION_JSON_UTF8);

        jsonHttpMessageConverter.setSupportedMediaTypes(fastMedia);
        jsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter converter = jsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
}
