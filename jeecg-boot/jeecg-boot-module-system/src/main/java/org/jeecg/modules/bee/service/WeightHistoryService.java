package org.jeecg.modules.bee.service;


import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.model.WeightFrameModel;

import java.util.List;

public interface WeightHistoryService {

    /**
     * 查询历史重量变化率
     * @param bbid      蜂箱号
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param time      时间点
     * @return
     */
    List<WeightChangeModel> selectWeightChangeByTime(String bbid,String startDate,String endDate,String time);

    /**
     * 查询历史重量/框数
     * @param bbid      蜂箱号
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param time      时间点
     * @return
     */
    List<WeightFrameModel> selectWeightFrameByTime(String bbid,String startDate,String endDate,String time);

    /**
     * 插入重量变化率历史表
     * @param weightChangeModels
     */
    void insertWeightChangeHistory(List<WeightChangeModel> weightChangeModels);

    /**
     * 插入重量/框数历史表
     * @param weightFrameModels
     */
    void insertWeightFrameHistory(List<WeightFrameModel> weightFrameModels);

    /**
     * 查询所有重量变化率 并 插入重量变化率历史表
     * 定时器使用
     * @return
     */
    void weightChangeSchedule(Integer hour,Integer min,Integer sec);

    /**
     * 查询所有重量变化率 并 插入重量变化率历史表
     * 定时器使用
     * @return
     */
    void weightFrameSchedule(Integer hour,Integer min,Integer sec);
}
