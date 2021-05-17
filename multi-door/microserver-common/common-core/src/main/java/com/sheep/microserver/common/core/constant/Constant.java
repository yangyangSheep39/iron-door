package com.sheep.microserver.common.core.constant;

import java.time.LocalTime;

/**
 * <p> 常量 </p>
 * 2021/2/3
 *
 * @author Ethereal
 * @version v1.0.0
 */

public class Constant {
    // 主库
    public static final String MASTER = "master";
    // 从库
    public static final String SLAVE = "slave";
    // 下划线
    public static final String UNDER_LINE = "_";
    // 考勤中正常状态的符号
    public static final String NORMAL_SYMBOL = "√";
    // 日期格式  年-月-日
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    // 日期格式  年-月-日 时:分:秒
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static final LocalTime AM_GO_WORK_TIME = LocalTime.of(8, 30);
    public static final LocalTime AM_OFF_WORK_TIME = LocalTime.of(12, 00);
    public static final LocalTime PM_GO_WORK_TIME = LocalTime.of(13, 00);
    public static final LocalTime PM_OFF_WORK_TIME = LocalTime.of(17, 30);

    // --------------------------考勤-打卡相关-----------------------------

    // ------------------上班打卡最早时间  及  最晚时间----------------------
    // 上班打卡生效时间
    public static final LocalTime EFFECTIVE_TIME_PUNCH_IN = LocalTime.of(6, 0);
    // 上班打卡失效时间
    public static final LocalTime DISABLED_TIME_PUNCH_IN = LocalTime.of(18, 0);
    // ------------------下班打卡最早时间  及  最晚时间----------------------
    // 下班打卡生效时间
    public static final LocalTime EFFECTIVE_TIME_PUNCH_OUT = LocalTime.of(9, 0);
    // 下班打卡失效时间
    public static final LocalTime DISABLED_TIME_PUNCH_OUT = LocalTime.of(23, 59,59);


    // 项目剩余人天阈值
    public static final Double PROJECT_RESIDUE_DURATION_THRESHOLD = 20D;

    /**
     * 正斜杠
     */
    public static final String BACK_SLASH = "/";

    /**
     * 文件服务器桶名称
     */
    public static final String BUCKET_NAME = "hande";
}
