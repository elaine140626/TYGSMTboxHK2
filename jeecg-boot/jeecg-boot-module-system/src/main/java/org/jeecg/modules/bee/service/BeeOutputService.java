package org.jeecg.modules.bee.service;

import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.HistoryOperExample;
import org.jeecg.modules.bee.entity.HistoryOutput;

import java.util.List;

/**
 * 产出统计service
 */
public interface BeeOutputService {

    /**
     * 开始统计蜂蜜产出
     * @param uid
     */
    int startOutputHoney(String uid);

    /**
     * 结束统计蜂蜜产出
     * @param uid
     */
    List<HistoryOutput> endOutputHoney(String uid);

    /**
     * 开始统计框数产出
     * @param uid
     */
    int startOutputFrame(String uid);

    /**
     * 结束统计框数产出
     * @param uid
     */
    List<HistoryOutput> endOutputFrame(String uid);

    /**
     * 插入历史产出统计中
     */
    void insertHistoryOutput(HistoryOutput historyOutput);

    /**
     * 批量插入历史统计中
     * @param historyOutputs
     */
    void insertHistoryOutputList(List<HistoryOutput> historyOutputs);

    /**
     * 获取时间范围内的产出记录
     * @return
     */
    List<HistoryOutput> selectByTimeRangeUid(String uid,String startTime,String endTime);

    /**
     * 获取时间范围内单个蜂箱的产出记录
     * @return
     */
    List<HistoryOutput> selectByTimeRangeBbid(String bbid);


    /**
     * 确认产出
     * @param historyOutputs
     * @param uid
     * @param type 1蜂蜜  2脾数
     */
    void confirmOutput(List<HistoryOutput> historyOutputs,String uid,Integer type);

    /**
     * 取消产出
     * @param uid
     * @param type
     */
    void cancelOutput(String uid,Integer type);
}
