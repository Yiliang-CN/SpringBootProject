package cn.gxust.springboot.exception;

import cn.gxust.springboot.dto.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response ex(Exception e) {
        e.printStackTrace();    // 打印异常信息
        return Response.error("系统繁忙，请稍后重试...");
    }
}
