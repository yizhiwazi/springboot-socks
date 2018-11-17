package com.hehe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

    /**
     * 设置全局跨域
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //默认拦截路径
                registry.addMapping("/**")

                        //表示允许那些原始域进行跨域访问，这里"*"表示允许任意网站，实际开发建议修改为配置项。
                        .allowedOrigins("*")

                        //表示是否允许客户端发送Cookie等凭证信息，这里"true"表示支持发送，涉及登陆此处必须开启。
                        .allowCredentials(true)

                        //表示允许原始域发起哪些请求方式，这里"*"表示支持GET/POST等全部提交方式。
                        .allowedMethods("*")

                        //表示允许原始域携带哪些请求头 这里"*"表示支持全部请求头
                        .allowedHeaders("*")

                        //表示允许暴露哪些响应头，这里特指那些非简单的头部信息，所以用"*"无效。
                        .exposedHeaders(HttpHeaders.AUTHORIZATION);
            }
        };
    }
}
