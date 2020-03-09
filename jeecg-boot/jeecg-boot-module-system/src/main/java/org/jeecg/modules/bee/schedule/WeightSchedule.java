package org.jeecg.modules.bee.schedule;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.jeecg.modules.bee.service.ScheduleService;
import org.jeecg.modules.bee.service.WeightHistoryService;
import org.jeecg.modules.message.websocket.WebSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Configuration
@EnableScheduling
@Slf4j
public class WeightSchedule {

    @Resource
    private WeightHistoryService weightHistoryService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private WebSocket webSocket;

    /**
     * 每30s判断飞逃
     */
    @Scheduled(cron = "0/20 * * * * ?")
    private void escapeSchedule(){
        log.info("定时任务:飞逃监测");
        scheduleService.beeEscapeSchedule();
    }

    /**
     * 每天6点记录蜂箱重量信息
     * [重量/框数]
     * [重量变化率]
     */
    @Scheduled(cron = "0 0 6 * * ?")
    private void weightHistorySchedule6(){
        weightHistoryService.weightChangeSchedule(6,0,0);
        weightHistoryService.weightFrameSchedule(6,0,0);
        log.info("定时任务:插入重量历史表");
    }

    /**
     * 每天12点记录蜂箱重量信息
     * [重量/框数]
     * [重量变化率]
     */
    @Scheduled(cron = "0 0 12 * * ?")
    private void weightHistorySchedule12(){
        weightHistoryService.weightChangeSchedule(12,0,0);
        weightHistoryService.weightFrameSchedule(12,0,0);
        log.info("定时任务:插入重量历史表");
    }

    /**
     * 每天18点记录蜂箱重量信息
     * [重量/框数]
     * [重量变化率]
     */
    @Scheduled(cron = "0 0 18 * * ?")
    private void weightHistorySchedule18(){
        weightHistoryService.weightChangeSchedule(18,0,0);
        weightHistoryService.weightFrameSchedule(18,0,0);
        log.info("定时任务:插入重量历史表");
    }

}
