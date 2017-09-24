package com.hehe.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Spring AOP 使用简介：
 * <p>
 * {@link Aspect  1.切面}
 * {@link Pointcut 2.切入点. 匹配规则可提供给各种通知复用}
 * {@link Around 3.环绕通知 功能强大}
 * {@link Before 前置通知 在切入点前}
 * {@link After 后置通知 在切入点后}
 * {@link AfterReturning 在方法返回值后}
 * {@link AfterThrowing  在方法异常后}
 * <p>
 * </p>
 */

@Configuration
@Aspect //定义切面
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //切入点匹配规则
    @Pointcut("execution(* com.hehe.controller..*.*(..))")
    public void logging() {
    }

    //环绕通知
    @Around("logging()")  //等价于 @Around("execution(* com.hehe.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {

        //获取Request
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();

        //打印日志
        logger.info("请求地址 : " + request.getRequestURL());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));

        //执行目标对象
        Object result = null;
        long begin = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
            logger.info("返回数据 : " + result);
        } catch (Throwable throwable) {
            logger.error("报错信息：" + throwable.toString());
        }
        logger.info("合计耗时：" + (System.currentTimeMillis() - begin) + "ms");

        return result;
    }


}
