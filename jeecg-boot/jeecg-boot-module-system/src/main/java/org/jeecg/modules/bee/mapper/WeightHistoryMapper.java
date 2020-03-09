package org.jeecg.modules.bee.mapper;


import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.model.WeightFrameModel;

import java.util.List;

public interface WeightHistoryMapper {

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
     * @param WeightChangeModels
     */
    void insertWeightChangeHistory(@Param("weightChangeModels") List<WeightChangeModel> WeightChangeModels);

    /**
     * 插入重量/框数历史表
     * @param weightFrameModels
     */
    void insertWeightFrameHistory(@Param("weightFrameModels") List<WeightFrameModel> weightFrameModels);
}
