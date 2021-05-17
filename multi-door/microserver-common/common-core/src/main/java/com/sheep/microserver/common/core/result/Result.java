package com.sheep.microserver.common.core.result;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyangSheep
 * @ClassName Result.java
 * @Description 结果集
 * @createTime 2021/5/17 19:35
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1344273071770324083L;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 状态码
     */
    private int code;
    /**
     * 具体信息
     */
    private String msg;
    /**
     * 总条数
     */
    private long total;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result ok() {
        Result result = new Result();
        result.setCode(ErrorCodeEnum.PC100200.code());
        result.setMsg(ErrorCodeEnum.PC100200.msg());
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.setCode(ErrorCodeEnum.PC100200.code());
        result.setMsg(ErrorCodeEnum.PC100200.msg());
        result.setData(data);
        return result;
    }

    public static Result ok(Object data, Long total) {
        Result result = new Result();
        result.setCode(ErrorCodeEnum.PC100200.code());
        result.setMsg(ErrorCodeEnum.PC100200.msg());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public static Result ok(Object data, ErrorCodeEnum error) {
        Result result = new Result();
        result.setCode(error.code());
        result.setMsg(error.msg());
        result.setData(data);
        return result;
    }

    public static Result error(ErrorCodeEnum error) {
        Result result = new Result();
        result.setCode(error.code());
        result.setMsg(error.msg());
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ErrorCodeEnum error, String msg) {
        Result result = new Result();
        result.setCode(error.code());
        result.setMsg(error.msg() + msg);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ErrorCodeEnum.PC100500.code());
        result.setMsg(ErrorCodeEnum.PC100500.msg());
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ErrorCodeEnum.PC100500.code());
        result.setMsg(ErrorCodeEnum.PC100500.msg() + msg);
        return result;
    }


    public Result setTotal(Long total) {
        this.total = null == total ? 0 : total;
        return this;
    }

}
