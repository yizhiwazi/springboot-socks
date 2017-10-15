package com.hehe.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 主要用途：统一处理错误/异常(针对控制层)
 * 使用说明:
 * {@link ControllerAdvice 默认扫描路径：例如com.hehe.controller}
 * {@link ExceptionHandler 指定捕捉异常}
 * {@link ModelAndView 返回异常信息页(View)}
 * {@link ResponseBody 返回异常信息(JSON)}
 *
 * @author yizhiwazi
 */

@ControllerAdvice
public class GlobalErrorHandler {
    /**
     * 错误信息页
     */
    private final static String DEFAULT_ERROR_VIEW = "error";

    /**
     * 错误信息构建器
     */
    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;

    /**
     * 方式1：针对某类异常,返回指定的异常信息页(View).
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeExHandler(HttpServletRequest request, Throwable error) {
        return new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo", errorInfoBuilder.getErrorInfo(request, error));
    }

    /**
     * 方式2：针对某类异常,返回异常信息(JSON).
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo exHandler(HttpServletRequest request, Throwable error) {
        return errorInfoBuilder.getErrorInfo(request, error);
    }


}
