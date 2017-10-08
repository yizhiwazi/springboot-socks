package com.hehe.controller;

import com.hehe.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;

@RestController
public class HelloController {

    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArithmeticException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new NoSuchMethodException(),
                new SQLException()};

        if ((Math.random()) < 0.6) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }

    @GetMapping("/")
    public R index() throws Exception {
        randomException();//模拟测试环境,根据概率来抛出异常！
        return R.isOk().data(Arrays.asList("我是正常数据1！", "我是正常数据2！你在刷新试试！"));
    }

}
