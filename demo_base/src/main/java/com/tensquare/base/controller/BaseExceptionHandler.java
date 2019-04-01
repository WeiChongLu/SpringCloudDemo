package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 错误处理方法
     */
    @ExceptionHandler(Exception.class)    //@ExceptionHandler: 该方法需要处理哪种异常
    @ResponseBody
    public Result handlerMsg(Exception e){
        return new Result(false, StatusCode.ERROR,"执行失败："+e.getMessage());
    }
}
