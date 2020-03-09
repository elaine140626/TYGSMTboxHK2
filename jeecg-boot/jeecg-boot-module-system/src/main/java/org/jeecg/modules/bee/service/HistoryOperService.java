package org.jeecg.modules.bee.service;

import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.model.OperModel;

import java.util.List;

public interface HistoryOperService {

    /**
     * 查询所有蜂箱最后一次操作记录
     * @return
     */
    List<HistoryOper> selectEveryLastOperHistory();

    /**
     * 查询最后一次喂食记录
     * @return
     */
    HistoryOper selectLastOperHistory(String bbid,String oper);

    /**
     * n天之内用户蜂箱的操作记录
     * @param uid
     * @return
     */
    List<HistoryOper> selectHistoryOperByUid(String uid);

    /**
     * n天内蜂箱的操作记录
     * @param bbid
     * @return
     */
    List<HistoryOper> selectHistoryOperByBbid(String uid,String bbid);

    /**
     * 批量清洗蜂箱
     * @param uid
     * @param bbids
     * @return
     */
    List<OperModel> cleanMultBox(String uid ,List<String> bbids);


    /**
     * 批量喂食
     * @param uid
     * @param bbids
     * @param feedOrder 0补充喂食  1奖励喂食
     * @param feedType 0自动 1手动
     * @param feedLong 喂食时长
     * @return
     */
    List<OperModel> feedMultBox(String uid, List<String> bbids ,Integer feedOrder,Integer feedType,Integer feedLong);



    /**
     * 批量巢门控制
     * @return
     */
    List<OperModel>  doorMultBox(String uid, List<String> bbids, Integer doorStatus);

    /**
     * 更新操作状态
     * @param historyOper
     */
    void updateHistoryOper(HistoryOper historyOper);

    /**
     * 单独产出脾
     */
    void outputFrame(String bbid);


    /**
     * 清洗蜂箱
     * @param historyOper
     * @return
     */
//    OperModel cleanBoxService(HistoryOper historyOper);
}
