package com.hehe.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hehe.entity.R;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {ErrorController 标记接口 用于渲染错误/异常详情}
 * {AbstractErrorController 抽象模板}
 * {BasicErrorController 默认实现 }
 */
@Controller
@RequestMapping("${server.error.path:/error}")
public class GlobalErrorController extends AbstractErrorController {

    /**
     * 统一错误信息页（View-Url）
     */
    private final static String EX_FALLBACK_VIEW = "exception";

    /**
     * 错误的配置属性（从配置文件中读取server.error开头的属性）
     */
    private ErrorProperties errorProperties;

    /**
     * 自定义构造器
     */
    public GlobalErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties = serverProperties.getError();
    }

    /**
     * 获取错误的访问路径（从配置文件中读取server.error.path的属性 默认值为/error）
     */
    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }


    /**
     * 情况1：若请求头的预期产生文本/页面类型（浏览器默认行为），则返回指定的错误信息页(View)
     */
    @RequestMapping(produces = "text/html")
    public String errorHtml(HttpServletRequest req, Throwable ex, Model model) {

        model.addAttribute("exUrl", req.getRequestURL());//问题地址
        model.addAttribute("exMsg", ex.toString());//问题信息
        model.addAttribute("exStatusInfo", getHttpStatusJson(req) );//Http状态
        return EX_FALLBACK_VIEW;
    }


    /**
     * 情况2：其它客户端请求 则返回详细的错误信息（JSON）。
     */
    @RequestMapping
    @ResponseBody
    public R error(HttpServletRequest req) {
        return R.isOk().data(getErrorAttributes(req, isIncludeStackTrace(req)));
    }

    /**
     * 根据请求对象和媒介来写判断是否返回堆栈轨迹(Stacktrace)。
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request) {
        //从配置文件中读取server.error.include-stacktrace的属性 默认值为NEVER
        IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();

        if (includeStacktrace == IncludeStacktrace.ALWAYS) {//若IncludeStacktrace为ALWAYS 则开启
            return true;
        }
        if (includeStacktrace == IncludeStacktrace.ON_TRACE_PARAM) {//若请求参数含有trace=true 则开启
            return getTraceParameter(request);
        }
        return false; //其余情况均返回堆栈追踪信息。
    }

    public String getHttpStatusJson(HttpServletRequest req) {
        HttpStatus httpStatus =getStatus(req);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("status",httpStatus.value());
        map.put("type",httpStatus.getReasonPhrase());
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


}
