package com.sheep.microserver.common.security.response;

import com.sheep.microserver.common.core.enums.ErrorCodeEnum;
import com.sheep.microserver.common.core.result.Result;
import com.sheep.microserver.common.core.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangyangSheep
 * @ClassName UnauthEntryPoint.java
 * @Description 未授权统一处理类
 * @createTime 2021/2/2 17:21
 */
@Slf4j
@Component
public class AccessDenied implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.info("调用未授权自定义返回处理类");
        ResponseUtil.out(httpServletResponse, Result.error(ErrorCodeEnum.PC100016.code(), ErrorCodeEnum.PC100016.msg()));
    }
}
