package com.hehe.common;

import java.sql.SQLException;

public class ExceptionUtils {

    //根据概率随机抛出异常
    public static void randomException() throws Exception {
        Exception[] exceptions = {
                new NullPointerException(),
                new ArithmeticException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new NoSuchMethodException(),
                new SQLException()};

        if ((Math.random()) < 0.5) {
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            // 程序继续运行
        }

    }

}
