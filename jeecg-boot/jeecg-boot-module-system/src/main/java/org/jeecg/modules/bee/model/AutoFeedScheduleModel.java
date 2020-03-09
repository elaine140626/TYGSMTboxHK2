package org.jeecg.modules.bee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoFeedScheduleModel {

    /**
     * 蜂箱号
     */
    private String bbid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 喂食重量 临界重量/框数
     */
    private Integer feedWeight;

    /**
     * 时间
     */
    private Date recvTime;

    /**
     * 重量/框数
     */
    private Double weightFrame;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 自动喂食开关
     * 0关  1开
     */
    private Integer autoFeed;

    /**
     * 喂食状态
     * 0喂食成功 1喂食失败(今日未喂食过) 2喂食中
     */
    private Integer feedStatus;

    /**
     * 产出统计状态
     * 产出统计中 0正常 1蜜统计中 2脾统计中 3脾统计结束未确认
     */
    private Integer outputing;

    /**
     * 是否喂食中 0正常 1喂食中
     */
    private Integer feeding;

    /**
     * 自动喂食时长
     */
    private Integer feedSuppLong;
}
