package com.chill.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/*
* 全局异常
* */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /*异常处理
    * */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result exceptionHandler(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());

        if(e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("服务器异常");
    }
    /*自定义异常处理
    * */
    @ExceptionHandler(CustomException.class)
    public Result exceptionHandler(CustomException e) {
        return Result.error(e.getMessage());
    }
}
