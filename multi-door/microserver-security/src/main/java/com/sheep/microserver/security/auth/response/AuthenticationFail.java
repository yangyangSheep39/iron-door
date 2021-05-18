package com.sheep.microserver.security.auth.response;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.core.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangyangSheep
 * @ClassName AuthenticationSuccess.java
 * @Description 自定义登录失败后的json返回
 * @createTime 2021/2/27 15:51
 */
@Slf4j
@Component
public class AuthenticationFail implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("调用登录失败自定义返回处理类");
        //账户锁定
        if (e instanceof LockedException) {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100017.code(), ErrorCodeEnum.PC100017.msg()));
        }
        //账户或者密码错误
        else if (e instanceof BadCredentialsException) {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100012.code(), ErrorCodeEnum.PC100012.msg()));
        }
        //账户禁用
        else if (e instanceof DisabledException) {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100014.code(), ErrorCodeEnum.PC100014.msg()));
        }
        //账户过期
        else if (e instanceof AccountExpiredException) {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100020.code(), ErrorCodeEnum.PC100020.msg()));
        }
        //密码过期
        else if (e instanceof CredentialsExpiredException) {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100021.code(), ErrorCodeEnum.PC100021.msg()));
        }
        //登录失败
        else {
            ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100030.code(), ErrorCodeEnum.PC100030.msg()));
        }
    }
}
