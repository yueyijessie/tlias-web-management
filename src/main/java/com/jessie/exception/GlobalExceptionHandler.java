package com.jessie.exception;

import com.jessie.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 全局异常处理
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace(); //打印堆栈中的异常信息
        return Result.error("对不起，操作失败，请联系管理员");
    }

}
