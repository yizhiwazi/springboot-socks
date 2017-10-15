package com.hehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author yizhiwazi
 */
@SpringBootApplication
@RestController
public class ErrorHandlerApplication {

    /**
     * 模拟用户数据访问
     */
    @GetMapping("/")
    public List index() throws Exception {
        //概率指数
        double probability = 0.6;
        if ((Math.random()) < probability) {
            throw new NullPointerException("空指针");
        }
        return Arrays.asList("用户数据1！", "用户数据2！你在刷新试试！");
    }

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlerApplication.class, args);
    }
}
