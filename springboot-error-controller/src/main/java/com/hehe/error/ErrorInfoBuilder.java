package com.hehe.error;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * 主要通途: 构建错误信息(ErrorInfo)
 * 设计说明:
 * 1.单独抽出获取错误信息的逻辑,让错误控制器{@link GlobalErrorController}更专注于业务。
 * 2.读取配置信息{@link ErrorProperties} 例如是否打印堆栈轨迹等。
 *
 * @author yizhiwazi
 */
@Component
public class ErrorInfoBuilder {

    /**
     * 错误配置(ErrorConfiguration)
     */
    private ErrorProperties errorProperties;

    public ErrorProperties getErrorProperties() {
        return errorProperties;
    }

    public void setErrorProperties(ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
    }

    /**
     * 错误构造器 (ErrorConstructor) 传递配置属性：server.xx -> server.error.xx
     */
    public ErrorInfoBuilder(ServerProperties serverProperties) {
        this.errorProperties = serverProperties.getError();
    }

    /**
     * 获取错误信息.(ErrorInfo)
     */
    public ErrorInfo getErrorInfo(HttpServletRequest request) {
        return getErrorInfo(request, getError(request));
    }

    public ErrorInfo getErrorInfo(HttpServletRequest request, Throwable error) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setTime(LocalDateTime.now().toString().replace('T', ' '));
        errorInfo.setUrl(request.getRequestURL().toString());
        errorInfo.setError(error.toString());
        errorInfo.setStatusCode(getHttpStatus(request).value());
        errorInfo.setReasonPhrase(getHttpStatus(request).getReasonPhrase());
        errorInfo.setStackTrace(getStackTraceInfo(error, isIncludeStackTrace(request)));
        return errorInfo;
    }

    /**
     * 获取错误(Exception/Error)
     */
    public Throwable getError(HttpServletRequest request) {

        Throwable error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        if (error != null) {//当获取错误非空,取出RootCause.
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        } else { //当获取错误为null,此时我们设置错误信息即可.
            String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
            if (StringUtils.isEmpty(message)) {
                HttpStatus status = getHttpStatus(request);
                message = status.value() + status.getReasonPhrase();
            }
            error = new Throwable(message);
        }
        return error;
    }


    /**
     * 获取通信状态(HttpStatus)
     *
     * @see AbstractErrorController #getHttpStatus
     */
    public HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


    /**
     * 获取堆栈轨迹
     *
     * @see DefaultErrorAttributes  #addStackTrace
     */
    public String getStackTraceInfo(Throwable error, boolean flag) {
        if (!flag) {
            return "omitted";
        }
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

    /**
     * 判断是否包含堆栈轨迹.
     *
     * @see BasicErrorController #isIncludeStackTrace
     */
    public boolean isIncludeStackTrace(HttpServletRequest request) {

        //读取错误配置(server.error.include-stacktrace=NEVER)
        IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();

        //情况1：若IncludeStacktrace为ALWAYS
        if (includeStacktrace == IncludeStacktrace.ALWAYS) {
            return true;
        }
        //情况2：若请求参数含有trace
        if (includeStacktrace == IncludeStacktrace.ON_TRACE_PARAM) {
            String parameter = request.getParameter("trace");
            return parameter != null && !"false".equals(parameter.toLowerCase());
        }
        //情况3：其它情况
        return false;
    }


}
