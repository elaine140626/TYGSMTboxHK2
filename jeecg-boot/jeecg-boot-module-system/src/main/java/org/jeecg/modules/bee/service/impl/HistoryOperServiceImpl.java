package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.*;
import org.jeecg.modules.bee.mapper.HistoryOperMapper;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.mqtt.MqttClientUtil;
import org.jeecg.modules.bee.service.BeeboxInfoService;
import org.jeecg.modules.bee.service.HistoryOperService;
import org.jeecg.modules.bee.service.SysConfigService;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.jeecg.modules.bee.socket.QueueUtil;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class HistoryOperServiceImpl implements HistoryOperService {

    @Resource
    private BeeboxInfoService beeboxInfoService;

    @Resource
    private HistoryOperMapper historyOperMapper;

    @Resource
    private SysConfigService sysConfigService;

    @Resource
    private UserBeeboxService userBeeboxService;

    @Resource
    private HistoryOperTransService historyOperTransService;

    @Resource
    private ISysUserService sysUserService;
    /**
     * 更新操作状态
     * @param historyOper
     */
    @Override
    public void updateHistoryOper(HistoryOper historyOper) {
        historyOperMapper.updateByPrimaryKeySelective(historyOper);
    }

    /**
     * 查询所有蜂箱最后一次喂食记录
     * @return
     */
    @Override
    public List<HistoryOper> selectEveryLastOperHistory() {
        return historyOperMapper.selectEveryLastOperHistory();
    }

    /**
     * 查询最后一次操作记录
     * @param bbid
     * @param oper
     * @return
     */
    @Override
    public HistoryOper selectLastOperHistory(String bbid,String oper) {
        HistoryOperExample historyOperExample = new HistoryOperExample();
        // 喂食
        List<HistoryOper> historyOpers = null;
        if ("feed".equals(oper)){
            historyOperExample.createCriteria().andBbidEqualTo(bbid).andFeedStatusIsNotNull();
            historyOperExample.setOrderByClause("feed_time desc");
            historyOperExample.setLimit(1);
            historyOpers = historyOperMapper.selectByExample(historyOperExample);
        // 加热
        }else if ("heat".equals(oper)){
            historyOperExample.createCriteria().andBbidEqualTo(bbid).andHeatStatusIsNotNull();
            historyOperExample.setOrderByClause("heat_starttime desc");
            historyOperExample.setLimit(1);
            historyOpers = historyOperMapper.selectByExample(historyOperExample);
        // 清洗
        }else if ("clean".equals(oper)){
            historyOperExample.createCriteria().andBbidEqualTo(bbid).andCleanStatusIsNotNull();
            historyOperExample.setOrderByClause("clean_time desc");
            historyOperExample.setLimit(1);
            historyOpers = historyOperMapper.selectByExample(historyOperExample);
        }
        if (historyOpers != null && historyOpers.size() > 0){
            return historyOpers.get(0);
        }
        return null;
    }

    /**
     * n天之内用户蜂箱的操作记录
     * @param uid
     * @return
     */
    @Override
    public List<HistoryOper> selectHistoryOperByUid(String uid) {

        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer hisShowDays = sysConfig.getHisShowDays();
        Date date = new Date();
        if (hisShowDays != null && hisShowDays > 0){
            date.setTime(date.getTime()-hisShowDays*24*60*60*1000);// n天前的时间
        }else{
            return null;
        }
        return historyOperMapper.selectHistoryOperByUid(uid,date);
    }

    /**
     * n天内蜂箱的操作记录
     * @param uid
     * @param bbid
     * @return
     */
    @Override
    public List<HistoryOper> selectHistoryOperByBbid(String uid,String bbid) {
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer hisShowDays = sysConfig.getHisShowDays();
        Date date = new Date();
        if (hisShowDays != null && hisShowDays > 0){
            date.setTime(date.getTime()- (long) hisShowDays*24*60*60*1000);// n天前的时间
        }else{
            return null;
        }
        return historyOperMapper.selectHistoryOperByBbid(bbid,date);
    }

    /**
     * 批量清洗蜂箱
     * @param uid
     * @param bbids
     * @return
     */
    @Override
    public List<OperModel> cleanMultBox(String uid, List<String> bbids) {
        List<OperModel> operModels = new ArrayList<>();
        // 获取用户名
        SysUser sysUser = sysUserService.getById(uid);
        String username = sysUser.getUsername();
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer cleanLong = sysConfig.getCleanLong();// 获取清洗时长
        Date date = new Date();
        for (String bbid : bbids){
            HistoryOper historyOper = new HistoryOper();
            historyOper.setUid(uid);
            historyOper.setBbid(bbid);
            historyOper.setCleanLong(cleanLong);
            historyOper.setCleanStatus(2);// 清洗中
            historyOper.setCleanTime(date);
            OperModel operModel = historyOperTransService.cleanBoxService(username,historyOper);
            operModels.add(operModel);
        }
        return operModels;
    }

    /**
     * 批量喂食
     * @param uid
     * @param bbids
     * @param feedOrder 0补充喂食  1奖励喂食
     * @param feedType 0自动 1手动
     * @param feedLong 喂食时长
     * @return
     */
    @Override
    public List<OperModel> feedMultBox(String uid, List<String> bbids ,Integer feedOrder,Integer feedType,Integer feedLong) {
        List<OperModel> operModels = new ArrayList<>();
        Date date = new Date();
        SysUser sysUser = sysUserService.getById(uid);
        String username = sysUser.getUsername();
        for (String bbid : bbids){
            HistoryOper historyOper = new HistoryOper();
            historyOper.setUid(uid);
            historyOper.setBbid(bbid);
            historyOper.setFeedTime(date);
            historyOper.setFeedType(feedType); // 喂食类型
            historyOper.setFeedOrder(feedOrder);// 喂食命令
            historyOper.setFeedStatus(2);// [喂食中]
            OperModel operModel = historyOperTransService.feedBoxService(username,historyOper,feedLong);
            operModels.add(operModel);
        }
        return operModels;
    }

    /**
     * 批量巢门控制
     * @param uid
     * @param bbids
     * @param doorStatus 0清洗 1控王 2正常 3通风
     * @return
     */
    @Override
    public List<OperModel> doorMultBox(String uid, List<String> bbids, Integer doorStatus) {
        List<OperModel> operModels = new ArrayList<>();
//        UserBeebox userBeebox = new UserBeebox();
//        userBeebox.setDoorStatus(doorStatus);
        // 获取用户名
        SysUser sysUser = sysUserService.getById(uid);
        String username = sysUser.getUsername();

        OperModel operModel = new OperModel();
        for (String bbid : bbids){
            operModel.setErrorMessage(null);
            UserBeebox ub = userBeeboxService.selectUserBeeboxByBbid(bbid);
            operModel.setBbid(bbid);
            // 若蜂箱正在清洗中，无法控制巢门
            if (ub.getNetStatus() == 1){
                operModel.setErrorMessage("蜂箱未联网");
                continue;
            }
            if (ub.getCleaning() == 1){
                operModel.setErrorMessage("蜂箱正在清洗中");
                continue;
            }
            ub.setDoorStatus(doorStatus);
            operModel = userBeeboxService.updateUserBeebox(ub);
            operModels.add(operModel);

//            userBeebox.setBbid(bbid);
//            OperModel operModel = null;
//            operModel = userBeeboxService.updateUserBeebox(userBeebox);
//            operModels.add(operModel);
            // 加入消息队列 巢门控制
            MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+(doorStatus+11)+"\"}");
//            QueueUtil.addQueueOper(bbid,doorStatus+11+"");
        }
        return operModels;
    }

    /**
     * 单独产出脾
     * @param bbid
     */
    @Override
    public void outputFrame(String bbid) {
        historyOperTransService.outputFrame(bbid);
    }
}
