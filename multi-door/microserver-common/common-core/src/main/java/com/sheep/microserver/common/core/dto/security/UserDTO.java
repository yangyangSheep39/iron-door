package com.sheep.microserver.common.core.dto.security;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @author fyc
 * @since 2021-02-03
 */
@Data
public class UserDTO {

    /**
     * 姓名
     */

    private String name;

    /**
     * 员工类型：1：前端 2：Java 3：.net
     */
    private Integer userType;

    /**
     * 员工等级：1：初级 2：中级 3：高级
     */
    private Integer userLevel;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String secret;

    /**
     * 汉得工号
     */
    private Long handeAccess;

    /**
     * 汉得密码
     */
    private String handeSecret;

    /**
     * 汉得邮箱
     */
    private String handeEmail;

    /**
     * 汉得邮箱密码
     */
    private String handeEmailSecret;

    /**
     * 大华工号
     */
    private String dahuaAccess;

    /**
     * 大华密码
     */
    private String dahuaSecret;

    /**
     * 调休时长 单位：小时
     */
    private Double vacationTime;

    /**
     * 入职时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datesEmployed;

    /**
     * 所属部门
     */
    private String deptName;

    /**
     * 年月 员工打卡情况过滤参数
     */
    private String yearAndMonth;

    private Integer projectState;

    private String startDate;
    private String endDate;

    /**
     * 权限角色集合
     */
    private List<String> securityRoles;
}