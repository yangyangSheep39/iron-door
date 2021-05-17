package com.sheep.microserver.common.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheep.microserver.common.core.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangyangSheep
 * @ClassName ResponseUtil.java
 * @Description 返回内容通过Response进行返回
 * @createTime 2021/2/2 16:45
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, Result r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
