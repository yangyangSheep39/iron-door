package com.sheep.microserver.common.core.constant;


/**
 * @author yangyangSheep
 * @ClassName JWTConstant.java
 * @Description JWT的常量
 * @createTime 2021/2/27 18:34
 */
public interface JwtConstant {
    Integer EXPIRE_TIME = 604800;

    String ALGORITHM_HMAC256 = "phospherus-security-jwt-sign-@2021/02/27";

    String SUBJECT = "phospherus-JWT";

    String JWT_PREFIX = "JWT:TOKEN:";

    String AUTHORIZATION = "Authorization";

}
