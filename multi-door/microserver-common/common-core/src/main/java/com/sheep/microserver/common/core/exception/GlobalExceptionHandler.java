package com.sheep.microserver.common.core.exception;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yangyangSheep
 * @ClassName GlobalExceptionHandler.java
 * @Description 全局异常的抛出
 * @createTime 2021/5/17 22:25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 自定义异常
     */
    @ExceptionHandler(value = {BaseException.class})
    public Result responseException(BaseException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 无权限访问异常
     * security-common中的AccessDenied异常处理不会生效，异常会被全局异常捕捉到
     *
     * @param e 无权限访问异常
     * @return code编码 100016
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    public Result responseException(AccessDeniedException e) {
        e.printStackTrace();
        log.error("无权限访问异常，具体原因:{}", e.getMessage());
        return Result.error(ErrorCodeEnum.PC100016.code(), ErrorCodeEnum.PC100016.msg());
    }

    /**
     * 其他未知异常
     */
    @ExceptionHandler(value = {Exception.class})
    public Result exception(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
