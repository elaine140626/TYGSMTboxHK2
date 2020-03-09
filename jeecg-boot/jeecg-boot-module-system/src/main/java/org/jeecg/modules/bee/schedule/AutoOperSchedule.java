package org.jeecg.modules.bee.schedule;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bee.service.ScheduleService;
import org.jeecg.modules.bee.socket.QueueUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Configuration
@EnableScheduling
@Slf4j
public class AutoOperSchedule {

    @Resource
    private ScheduleService scheduleService;

    @Scheduled(cron = "0/40 * * * * ?")
    private void autoHeatSchedule(){
        log.info("定时任务:自动加热");
        scheduleService.autoHeatSchedule();
    }

    /**
     *
     * 每60s判断是否需要喂食或控制巢门
     */
    @Scheduled(cron = "0 */1 21-23 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    private void autoFeedSchedule(){
        log.info("定时任务:自动喂食");
        scheduleService.autoFeedSchedule();
    }

    /**
     * 每60s判断巢门状态 [控王][通风]
     */
    @Scheduled(cron = "0 */1 * * * ?")
    private void autoDraftGearSchedule(){
//        log.info("定时任务:巢门控制");
//        scheduleService.autoKingCtrlSchedule();// 控王档
//        scheduleService.autoDraftGearSchedule();// 通风档
    }

    /**
     * 每1分钟判断操作超时
     */
    @Scheduled(cron = "0 */1 * * * ?")
    private void operTimeOut(){
        scheduleService.operStatusSchedule();
    }

    /**
     * 每10s发一次心跳
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void BeeSchedule(){
//        scheduleService.netStatusSchedule();
    }



}
