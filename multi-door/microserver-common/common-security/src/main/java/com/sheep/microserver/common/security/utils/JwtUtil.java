package com.sheep.microserver.common.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import com.sheep.microserver.common.core.constant.JwtConstant;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangyangSheep
 * @ClassName JwtUtil.java
 * @Description JWT工具类
 * @createTime 2021/2/27 18:32
 */
public class JwtUtil {

    /**
     * 生成jwt
     */
    public static String createJwt(String securityUser, List<String> authStrs) {
        //颁发时间
        Date createTime = new Date();
        //过期时间
        Calendar now = Calendar.getInstance();
        //设置未来的时间
        now.set(Calendar.SECOND, JwtConstant.EXPIRE_TIME);
        Date expireTime = now.getTime();
        //header
        HashMap<String, Object> header = Maps.newHashMap();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        //载体
        String sign = JWT.create()
                .withHeader(header)
                .withIssuedAt(createTime)
                .withExpiresAt(expireTime)
                .withSubject(JwtConstant.SUBJECT)
                .withClaim("securityUser", securityUser)
                .withClaim("auths", authStrs)
                //签名
                .sign(Algorithm.HMAC256(JwtConstant.ALGORITHM_HMAC256));
        return sign;
    }

    /**
     * 检验jwt
     */
    public static Boolean decodeJwt(String jwtStr) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtConstant.ALGORITHM_HMAC256)).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(jwtStr);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析用户信息
     */
    public static HashMap<String, Object> getUserInfo(String jwtStr) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtConstant.ALGORITHM_HMAC256)).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(jwtStr);
            Claim username = verify.getClaim("securityUser");
            String s = username.asString();
            Claim auths = verify.getClaim("auths");
            List<String> authList = auths.asList(String.class);
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("securityUser", s);
            map.put("auths", authList);
            return map;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
