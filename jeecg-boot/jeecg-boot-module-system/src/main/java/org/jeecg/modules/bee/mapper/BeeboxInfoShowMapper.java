package org.jeecg.modules.bee.mapper;

import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.model.*;

import java.util.List;

public interface BeeboxInfoShowMapper {

    /**
     * 查询40s内最新一次蜂箱信息
     * @param uid 用户id
     * @return
     */
    List<BeeboxInfo> selectNowBoxInfoByUid(String uid,String orderBy);

    /**
     * 查询40s内最新一次蜂箱信息
     * @param bbid 蜂箱号
     * @return
     */
    List<BeeboxInfo> selectNowBoxInfoByBbid(String bbid);

    /**
     * 查询240s内最新一次的蜂箱信息
     * @param bbids
     * @return
     */
    List<OutputWeightModel> selectOutputWeightByBbids(List<String> bbids);

    /**
     * 查询240s内最新一次的蜂箱信息
     * @param uid
     * @return
     */
    List<OutputWeightModel> selectOutputWeightByUid(String uid);


    /**
     * 查询40s内最新一次蜂箱信息
     * @return
     */
    List<BeeboxInfo> selectNowBoxInfo();

    /**
     * 查询最近20s内蜂箱信息以及临界喂食重量 [自动喂食]
     * @return
     */
    List<AutoFeedScheduleModel> selectNowWFRAndFeedWeight();

    /**
     * 查询最近20s内蜂箱温度以及自动加热开关 [自动加热]
     * @return
     */
    List<AutoHeatScheduleModel> selectNowTemperInfo();

    /**
     * 查询60s内最近两次蜂箱信息
     * @param uid
     * @return
     */
    List<BeeboxInfo> selectBoxInfoIn40sByUid(String uid);

    /**
     * 查询60s内最近两次蜂箱信息
     * @return
     */
    List<BeeboxInfo> selectAllBoxInfoIn40s();

    /**
     * 蜜蜂飞逃schedule
     * @return
     */
    List<EscapeScheduleModel> selectAllWeightUidCid();

    /**
     * 自动巢门控制 [控王]
     * @return
     */
    List<AutoKingCtrlScheduleModel> selectWFRKingCtrlRate();

    /**
     * 自动巢门控制 [通风]
     * @return
     */
    List<AutoDraftGearScheduleModel> selectAllWFRDecreseRate();
}
