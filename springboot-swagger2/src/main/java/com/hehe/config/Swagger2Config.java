package com.hehe.config;

import io.swagger.annotations.*;
import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2使用说明：
 * <p>
 * {@link Api ：用在类上，说明该类的作用}
 * {@link ApiOperation ：用在方法上，说明方法的作用}
 * {@link ApiIgnore ：使用该注解忽略这个API}
 * {@link ApiImplicitParams ：用在方法上包含一组参数说明}
 * {@link ApiImplicitParam ：
 * 参数列表如下：
 * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数描述值
 * defaultValue：默认值
 * paramType：位置类型 [header,query,path,body,form]
 * }
 * <p>
 */
@Configuration //让Spring来加载该类配置
@EnableSwagger2 //启用Swagger2
public class Swagger2Config {

    //首页API信息
    private static final ApiInfo apiInfo = new ApiInfoBuilder()
            .termsOfServiceUrl("https://github.com/yizhiwazi")
            .title("XX系统接口文档")
            .description("about xx")
            .contact("yizhiwazi")
            .version("1.0")
            .build();

    //列表摘要
    @Bean
    public Docket oneapi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hehe.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
