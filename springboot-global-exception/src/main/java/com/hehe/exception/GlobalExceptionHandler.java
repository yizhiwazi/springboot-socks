package com.hehe.exception;

import com.hehe.entity.R;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 主要用途：用于捕捉全局异常(针对控制层),并返回指定的异常信息页(View)或异常信息(Json)。
 * <p>
 * 使用说明:
 * {@link ControllerAdvice 默认扫描路径：例如com.hehe.controller}
 * {@link ExceptionHandler 指定异常：例如RuntimeException及其子类}
 * {@link ResponseBody 返回JSON}
 *
 * @author yizhiwazi
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String EX_FALLBACK_VIEW = "exception";//指定异常信息页

    /**
     * 方式1：返回指定的异常信息页(View)
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExHandler(HttpServletRequest req, Exception ex, Model model) {
        model.addAttribute("exUrl", req.getRequestURL().toString());
        model.addAttribute("exMsg", ex.toString());
        return EX_FALLBACK_VIEW;
    }

    /**
     * 方式2：返回指定的异常信息(Json)
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R defaultExHandler(HttpServletRequest req, Exception e) {
        return R.isFail(e).data("全局异常JSON：服务异常,请稍后再试！！:" + req.getRequestURL());
    }
}
