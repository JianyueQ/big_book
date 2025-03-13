package com.big_event.Exception;

import com.big_event.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result Handler(Exception e){
        //打印异常的堆栈跟踪信息到控制台
        //用于调试和记录异常信息
        e.printStackTrace();
        return Result.error(StringUtils.hasLength( e.getMessage() ) ? e.getMessage() : "操作失败");
    }
}
