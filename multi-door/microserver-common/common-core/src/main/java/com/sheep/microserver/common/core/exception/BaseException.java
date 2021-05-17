package com.sheep.microserver.common.core.exception;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangyangSheep
 * @ClassName BaseException.java
 * @Description 自定义异常
 * @createTime 2021/5/17 22:25
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3160241586346324994L;

    /**
     * 错误码
     */
    private int code;

    public BaseException() {

    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public BaseException(ErrorCodeEnum codeEnum, Object... args) {
        super(String.format(codeEnum.msg(), args));
        this.code = codeEnum.code();
    }

    public BaseException(ErrorCodeEnum codeEnum, String msg) {
        super(codeEnum.msg() + msg);
        this.code = codeEnum.code();
    }
}
