package org.jeecg.modules.bee.service;

public interface ScheduleService {

    /**
     * 自动喂食
     */
    void autoFeedSchedule();

    /**
     * 自动加热
     */
    void autoHeatSchedule();

    /**
     * 判断操作是否超时
     */
    void operStatusSchedule();

    /**
     * 蜜蜂飞逃报警
     */
    void beeEscapeSchedule();

    /**
     * 巢门控制[控王档]
     */
    void autoKingCtrlSchedule();

    /**
     * 巢门控制[通风档]
     */
    void autoDraftGearSchedule();

    /**
     * 网络状态
     */
    void netStatusSchedule();

    /**
     * 心跳控制
     */
    void heartBeatSchedule();
}
