package org.jeecg.modules.bee.service.impl;

import com.alibaba.fastjson.JSONObject;
import net.sf.saxon.functions.DynamicContextAccessor;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.UmsException;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.getui.AppPush;
import org.jeecg.modules.bee.mapper.BeeboxInfoShowMapper;
import org.jeecg.modules.bee.model.*;
import org.jeecg.modules.bee.mqtt.MqttClientUtil;
import org.jeecg.modules.bee.service.*;
import org.jeecg.modules.bee.socket.QueueUtil;
import org.jeecg.modules.message.websocket.WebSocket;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private BeeboxInfoService beeboxInfoService;

    @Resource
    private BeeboxInfoShowMapper beeboxInfoShowMapper;

    @Resource
    private HistoryOperService historyOperService;

    @Resource
    private HistoryOperTransService historyOperTransService;

    @Resource
    private UmsExceptionService umsExceptionService;

    @Resource
    private SysConfigService sysConfigService;

    @Resource
    private ISysUserService sysUserService;

    @Resource
    private WebSocket webSocket;

    @Resource
    private UserBeeboxService userBeeboxService;

    /**
     * 自动喂食schedule
     */
    @Override
    public void autoFeedSchedule() {
        // 查询所有蜂箱信息
        List<AutoFeedScheduleModel> autoFeedScheduleModels = beeboxInfoService.selectNowWFRAndFeedWeight();
        // 设置喂食参数 [自动] [喂食中] [补充]
        HistoryOper historyOper = new HistoryOper();
        historyOper.setFeedTime(new Date());
        historyOper.setFeedType(0);
        historyOper.setFeedStatus(2);
        historyOper.setFeedOrder(0);
        for (AutoFeedScheduleModel box : autoFeedScheduleModels){
            //  开启自动喂食 && 当前蜂箱[重量/框数]低于用户设定临界值 && 当日没有自动喂食过  && 不在产出统计中[0]
            if (box.getAutoFeed() == 1 && box.getWeightFrame() < box.getFeedWeight() && box.getFeedStatus() == 1  && box.getOutputing() == 0) {
                historyOper.setBbid(box.getBbid());
                historyOper.setUid(box.getUid());
                // 获取用户名
                SysUser sysUser = sysUserService.getById(box.getUid());
                String username = sysUser.getUsername();
                // 开启喂食
                historyOperTransService.feedBoxService(username,historyOper,box.getFeedSuppLong());
            }
        }
    }

    /**
     * 自动加热schedule
     */
    @Override
    public void autoHeatSchedule() {
        // 查询所有蜂箱信息
        List<AutoHeatScheduleModel> autoHeatScheduleModels = beeboxInfoService.selectNowTemperInfo();
        // 设置开始加热参数 [加热中] [开始时间]
        HistoryOper historyOper = new HistoryOper();
        Date date = new Date();
        for (AutoHeatScheduleModel box : autoHeatScheduleModels){
            // 开始加热
            // 开启[自动加热] && 当前蜂箱温度 < 用户设定下限温度 && 不在[加热中]
            if (box.getAutoHeat() == 1 && box.getTemper() < box.getHeatLower() && box.getHeating() != 1) {
                historyOper.setBbid(box.getBbid());
                historyOper.setUid(box.getUid());
                historyOper.setHeatStartTemper(box.getTemper());
                historyOper.setHeatStarttime(date);
                historyOper.setHeatStatus(2);
                // 获取用户名
                SysUser sysUser = sysUserService.getById(box.getUid());
                String username = sysUser.getUsername();
                // 开始加热
                historyOperTransService.heatBoxService(username,historyOper,1);
            // 停止加热
            // 开启[自动加热] && 当前蜂箱温度 >= 用户设定下限温度 && 在[加热中]
            }else if (box.getAutoHeat() == 1 && box.getTemper() >= box.getHeatUpper() && box.getHeating() == 1){
                historyOper.setId(box.getOperHisId());
                historyOper.setBbid(box.getBbid());
                historyOper.setUid(box.getUid());
                historyOper.setHeatEndTemper(box.getTemper());
                historyOper.setHeatEndtime(date);
                historyOper.setHeatStatus(0);
                // 获取用户名
                SysUser sysUser = sysUserService.getById(box.getUid());
                String username = sysUser.getUsername();
                // 停止加热
                historyOperTransService.heatBoxService(username,historyOper,0);
            }
        }
    }

    @Override
    public void operStatusSchedule() {
        // 查询所有蜂箱最后一次操作记录
        List<HistoryOper> historyOpers = historyOperService.selectEveryLastOperHistory();
        for (HistoryOper ho : historyOpers){
            if (ho.getCleanStatus() != null){
                Date cleanTime = ho.getCleanTime();
                Integer cleanLong = ho.getCleanLong();
                long expireTime = cleanTime.getTime() + (cleanLong + 2) * 60000 ;// 过期时间: n+2 分钟  包括巢门上下移位
                // 当前时间大于清洗最长时间 关闭清洗
                if (expireTime < System.currentTimeMillis()){
                    // 清洗失败
                    ho.setCleanStatus(1);
                    historyOperService.updateHistoryOper(ho);
                }
            } else if (ho.getFeedStatus() != null){
                Date feedTime = ho.getFeedTime();
                Integer feedOrder = ho.getFeedOrder();
                Long expireTime = null;
                if (feedOrder == 0){
                    // 补充喂食
                    expireTime = feedTime.getTime() + 60000 * 20;// 过期时间: 20分钟  = 20*60s
                } else if (feedOrder == 1){
                    // 奖励喂食
                    expireTime = feedTime.getTime() + 30000 * 15;// 过期时间: 7.5分钟 = 15*30s
                }
                if (expireTime != null){
                    // 5分钟内没有更新喂食状态到成功 则喂食失败
                    if (expireTime < System.currentTimeMillis()){
                        // 喂食失败
                        ho.setFeedStatus(1);
                        historyOperService.updateHistoryOper(ho);
                    }
                }

            }
        }
    }

    /**
     * 蜜蜂飞逃报警
     */
    @Override
    public void beeEscapeSchedule() {
        // 查询所有蜂箱重量 + 用户uid + 用户cid
        List<EscapeScheduleModel> escapeScheduleModels = beeboxInfoShowMapper.selectAllWeightUidCid();
        boolean flag = false;
        if (escapeScheduleModels.size() >= 2) {// 至少包含两条数据
            for (int i = 0; i < escapeScheduleModels.size() - 1; i++) {
                EscapeScheduleModel esm1 = escapeScheduleModels.get(i);
                EscapeScheduleModel esm2 = escapeScheduleModels.get(i + 1);
                String bbid1 = esm1.getBbid();
                String bbid2 = esm2.getBbid();
                // 只取记录前两条
                if (bbid1.equals(bbid2)) {
                    // 计算重量差
                    if (!flag) {
                        flag = true;
                        // 重量差 > 用户设定的逃跑重量
                        if (esm2.getWeight() - esm1.getWeight() > esm1.getEscapeWeight()) {
                            try {
                                // 飞逃报警
                                String cid = esm1.getCid();
                                Date date = new Date();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                String exceptionTime = simpleDateFormat.format(date);
                                // ws
                                JSONObject obj = new JSONObject();
                                obj.put("cmd", "escape");
                                obj.put("userId", esm1.getUid());
                                obj.put("msgId", bbid1);
                                obj.put("msgCode", 200);
                                obj.put("msgTxt", "蜂箱[" + bbid1 + "]于 " + exceptionTime + " 发生飞逃");
                                webSocket.sendOneMessage(esm1.getUid(),obj.toJSONString());
                                // 单推 飞逃 2h过期
                                AppPush.pushToSingle("飞逃报警", "蜂箱[" + bbid1 + "]于 " + exceptionTime + " 发生飞逃", 120, cid);
                                System.out.println(bbid1 + ":飞逃 " + exceptionTime);
                                // 记录到异常信息表
                                UmsException umsException = new UmsException();
                                umsException.setBbid(bbid1);
                                umsException.setUid(esm1.getUid());
                                umsException.setEvent("飞逃");
                                umsException.setTime(date);
                                umsExceptionService.insertException(umsException);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    flag = false;
                }
            }
        }
    }

    /**
     * 巢门控制 [控王]
     */
    @Override
    public void autoKingCtrlSchedule() {
        List<AutoKingCtrlScheduleModel> kcsms = beeboxInfoShowMapper.selectWFRKingCtrlRate();
        for (AutoKingCtrlScheduleModel kcsm : kcsms){
            Double weightFrame = kcsm.getWeightFrame();
            Double kingCtrlRate = kcsm.getKingCtrlRate();
            Double stdWeight = kcsm.getStdWeight();
            // 重量/脾数      < 标准重量/脾数   的50%  50%由用户设定
            if (weightFrame < stdWeight*kingCtrlRate){
                String bbid = kcsm.getBbid();
                SysUser sysUser = sysUserService.getById(kcsm.getUid());
                String username = sysUser.getUsername();
                MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.DOOR_KING+"\"}");
//                QueueUtil.addQueueOper(bbid,QueueUtil.DOOR_KING);
            }
        }
    }

    /**
     * 巢门控制 [通风]
     */
    @Override
    public void autoDraftGearSchedule() {
        List<AutoDraftGearScheduleModel> dgsms = beeboxInfoShowMapper.selectAllWFRDecreseRate();
        if (dgsms.size() >= 2) {// 至少包含两条数据
            Integer draftInterval = dgsms.get(0).getDraftInterval();
            int count = draftInterval / 20 ;  // 2
            int nowCount = 0;
            for (int i = 0; i < dgsms.size() - 1; i++) {
                AutoDraftGearScheduleModel dgsm1 = dgsms.get(i);
                AutoDraftGearScheduleModel dgsm2 = dgsms.get(i+1);
                String bbid1 = dgsm1.getBbid();
                String bbid2 = dgsm2.getBbid();
                if (bbid1.equals(bbid2)){
                    // 瞬间（连续 20*n s内）重量/脾数   减少20%以上
                    if (dgsm1.getWeightFrame() < dgsm2.getWeightFrame() * (1-dgsm2.getDecreseRate())){
                        // 记录一次
                        nowCount++;
                    }
                    if (nowCount == count){
                        // 发送巢门控制 [通风] 指令
                        SysUser sysUser = sysUserService.getById(dgsm1.getUid());
                        String username = sysUser.getUsername();
                        MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid1+":"+QueueUtil.DOOR_DRAFT+"\"}");
//                        QueueUtil.addQueueOper(bbid1,QueueUtil.DOOR_DRAFT);// 通风
                    }
                } else {
                    nowCount  = 0;
                    count = dgsm2.getDraftInterval() / 20;
                }
            }
        }
    }

    /**
     * 判断蜂箱是否联网
     */
    @Override
    public void netStatusSchedule() {
        List<UserBeebox> bbids = userBeeboxService.selectAllBbids();
        for (UserBeebox userBeebox : bbids){
            // 是否存在 000X:99
            String bbid = userBeebox.getBbid();
            boolean operExists = QueueUtil.setOper.contains(bbid+":"+QueueUtil.BOX_HEART_BEAT);
            if (operExists){
                // 若Set中存在
                userBeebox.setNetStatus(1);// 断网
            } else {
                userBeebox.setNetStatus(0);// 联网
            }
            QueueUtil.setOper.add(bbid+":"+QueueUtil.BOX_HEART_BEAT);
//            QueueUtil.addQueueOper(bbid,QueueUtil.BOX_HEART_BEAT);
        }
        userBeeboxService.updateUserBeeboxMult(bbids);
    }

    /**
     * 发送
     */
    @Override
    public void heartBeatSchedule() {

    }
}
