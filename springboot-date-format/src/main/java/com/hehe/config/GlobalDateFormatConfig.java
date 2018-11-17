package com.hehe.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 全局日期格式化
 */
@Configuration
public class GlobalDateFormatConfig {

    /**
     * 获取日期格式化的工具类
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {

        //根据实际业务支持各种复杂格式的日期字符串。
        @Override
        public Date parse(String source) {
            try {
                return super.parse(source);//支持解析指定pattern类型。
            } catch (Exception e) {
                try {
                    return new StdDateFormat().parse(source);//支持解析long类型的时间戳
                } catch (ParseException e1) {
                    throw new RuntimeException("日期格式非法：" + e);
                }
            }
        }
    };


    /**
     * JSON消息处理器
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        //设置解析JSON工具类
        ObjectMapper objectMapper = new ObjectMapper();
        //设置解析日期的工具类
        objectMapper.setDateFormat(dateFormat);
        //忽略未知属性 防止解析报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(list);
        return jsonConverter;
    }

    /**
     * XML消息处理器
     */
    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        MappingJackson2XmlHttpMessageConverter xmlConverter = new MappingJackson2XmlHttpMessageConverter();
        //设置解析XML的工具类
        XmlMapper xmlMapper = new XmlMapper();
        //设置解析日期的工具类
        xmlMapper.setDateFormat(dateFormat);
        //忽略未知属性 防止解析报错
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlConverter.setObjectMapper(xmlMapper);
        return xmlConverter;
    }

}
