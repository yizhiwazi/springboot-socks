package com.hehe.config;

import com.hehe.entity.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用途：捕捉各类异常 并返回错误信息JSON
 *
 * PS：若出现包含关系 则更精细的异常会被拦截.如果没有特殊业务，则默认合并处理就好了。
 *
 * @author  yizhiwazi
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //运行异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public R nullPointer(HttpServletRequest req, Exception e)  {
        return R.isFail(e).data(req.getRequestURL());
    }

    //默认异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R defaultHandler(HttpServletRequest req, Exception e)  {
        return R.isFail(e).data(req.getRequestURL());
    }


}
