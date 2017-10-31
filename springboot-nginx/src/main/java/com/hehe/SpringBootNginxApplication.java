package com.hehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
@RequestMapping("/user/login/*")
public class SpringBootNginxApplication {

    //在拦截器打印访问URL
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
                        if(response.getStatus()/100>=4){
                            System.err.println("访问URL:"+request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));
                        }else {
                            System.out.println("访问URL:"+request.getRequestURI());
                        }
                    }
                });
            }
        };
    }


    //提供验证码
    @RequestMapping("verifyCode")
    public String verifyCode(HttpServletRequest request) {
        request.getSession().setAttribute("verifyCode", "N7GX");
        return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
    }

    //核对验证码
    @RequestMapping("checkVerifyCode")
    public String checkVerifyCode(HttpServletRequest request) {
        return request.getSession().getId() + ":" + request.getSession().getAttribute("verifyCode");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNginxApplication.class, args);
    }
}
