package org.jeecg.modules.bee.service;

import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.model.AutoFeedScheduleModel;
import org.jeecg.modules.bee.model.AutoHeatScheduleModel;
import org.jeecg.modules.bee.model.OutputWeightModel;
import org.jeecg.modules.bee.model.WeightChangeModel;

import java.util.List;

public interface BeeboxInfoService {

    void insertBeeboxInfo(BeeboxInfo beeboxInfo);

    /**
     * 查询所有蜂箱信息
     * @return
     */
    List<BeeboxInfo> selectAllBeeboxInfo();

    /**
     * 查询最近20s内蜂箱信息以及喂食重量 [自动喂食]
     * @return
     */
    List<AutoFeedScheduleModel> selectNowWFRAndFeedWeight();

    /**
     * 查询最近20s内蜂箱温度 [自动加热]
     * @return
     */
    List<AutoHeatScheduleModel> selectNowTemperInfo();

    /**
     * 查询最新一次蜂箱状态
     * @param uid
     * @param orderBy
     * @return
     */
    List<BeeboxInfo> selectNowBeeboxInfoByUid(String uid,String orderBy);

    /**
     * 查询最新一次蜂箱状态
     * @param bbid
     * @return
     */
    BeeboxInfo selectNowBeeboxInfoByBbid(String bbid);

    /**
     * 查询40s内最近两次的重量变化率
     * @param uid
     * @return
     */
    List<WeightChangeModel> selectWeightChangeByUid(String uid);

    /**
     * 查询240s内最新一次蜂箱信息
     * @param bbids
     * @return
     */
    List<OutputWeightModel> selectNowBoxInfoByBbids(List<String> bbids);

    /**
     * 查询240s内最新一次蜂箱信息
     * @param uid
     * @return
     */
    List<OutputWeightModel> selectOutputWeightByUid(String uid);

}
