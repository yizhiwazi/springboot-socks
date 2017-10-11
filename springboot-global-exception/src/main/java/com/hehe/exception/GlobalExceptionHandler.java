package com.hehe.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hehe.entity.R;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

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

//@ControllerAdvice
public class GlobalExceptionHandler {
    private final static String EX_FALLBACK_VIEW = "exception";//指定异常信息页

    /**
     * 方式1：返回指定的异常信息页(View)
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExHandler(HttpServletRequest req, Exception ex, Model model) {
        model.addAttribute("exUrl", req.getRequestURL().toString());//问题地址
        model.addAttribute("exMsg", ex.toString());//问题信息
        model.addAttribute("exStatusInfo", getHttpStatusJson(req) );//Http状态
        return EX_FALLBACK_VIEW;
    }

    /**
     * 方式2：返回指定的异常信息(Json)
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R defaultExHandler(HttpServletRequest req, Exception ex) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("exUrl", req.getRequestURL().toString());
        map.put("exMsg", ex.toString());
        map.put("exStatusInfo",getHttpStatusJson(req) );
        return R.isOk().data(map);
    }

    public String getHttpStatusJson(HttpServletRequest req) {
        HttpStatus httpStatus =getHttpStatus(req);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("status",httpStatus.value());
        map.put("type",httpStatus.getReasonPhrase());
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        try {
            return statusCode!=null?HttpStatus.valueOf(statusCode):HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
