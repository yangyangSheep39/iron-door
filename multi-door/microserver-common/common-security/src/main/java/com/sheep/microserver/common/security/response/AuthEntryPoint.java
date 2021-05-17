package com.sheep.microserver.common.security.response;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.core.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangyangSheep
 * @ClassName AuthEntryPoint.java
 * @Description 未登录处理配置类
 * @createTime 2021/3/10 17:05
 */
@Slf4j
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("调用未登录自定义处理类");
        ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100015.code(), ErrorCodeEnum.PC100015.msg()));
    }
}
