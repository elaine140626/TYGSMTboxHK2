package org.jeecg.modules.bee.service.impl;


import org.apache.catalina.User;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.HistoryOutput;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.mapper.HistoryOperMapper;
import org.jeecg.modules.bee.model.OperModel;
import org.jeecg.modules.bee.mqtt.MqttClientUtil;
import org.jeecg.modules.bee.service.BeeOutputService;
import org.jeecg.modules.bee.service.HistoryOperService;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.jeecg.modules.bee.socket.QueueUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class HistoryOperTransService {

    private static final Integer FEED_SUPP_TIME = 20;// 补充喂食 20min
    private static final Integer FEED_REWD_TIME = 5;// 奖励喂食 5min
    private static final Integer DOOR_CLEAN_TIME = 2;// 巢门 开+关 时间 2min


    @Resource
    private HistoryOperMapper historyOperMapper;

    @Resource
    private HistoryOperService historyOperService;

    @Resource
    private UserBeeboxService userBeeboxService;

    @Resource
    private BeeOutputService beeOutputService;

    @Transactional
    public void outputFrame(String bbid){
        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
        Integer frameNum = userBeebox.getFrameNum();
        if (frameNum == 0){
            return;
        }
        userBeebox.setFrameNum(frameNum - 1);// 产出1脾
        userBeeboxService.updateUserBeebox(userBeebox);

        HistoryOutput historyOutput = new HistoryOutput();
        historyOutput.setBbid(bbid);
        historyOutput.setFrame(1);
        historyOutput.setTime(new Date());
        beeOutputService.insertHistoryOutput(historyOutput);
    }

    /**
     * 喂食完成 / 清洗完成
     * @param bbid
     */
    @Transactional
    public void feedOrCleanComplete(String bbid, String oper) {
        HistoryOper historyOper = historyOperService.selectLastOperHistory(bbid,oper);
        if ("feed".equals(oper)){
            // [喂食中]
            if (historyOper.getFeedStatus() == 2){
                // 更新操作历史表 --> [喂食完成]
                historyOper.setFeedStatus(0);
                historyOperMapper.updateByPrimaryKeySelective(historyOper);
                // 更新蜂箱状态表 --> [正常]
                UserBeebox userBeebox = UserBeebox.builder().bbid(bbid).feeding(0).build();
                userBeeboxService.updateUserBeebox(userBeebox);
            }
        } else if("clean".equals(oper)){
            // [清洗中]
            if (historyOper.getCleanStatus() == 2){
                // 更新操作历史表 --> [清洗完成]
                historyOper.setCleanStatus(0);
                historyOperMapper.updateByPrimaryKeySelective(historyOper);
                // 更新蜂箱状态表 --> [正常]
                UserBeebox userBeebox = UserBeebox.builder().bbid(bbid).cleaning(0).build();
                userBeeboxService.updateUserBeebox(userBeebox);
            }
        }
    }

    /**
     * 清洗蜂箱
     * @param historyOper
     * @return
     */
    @Transactional(propagation = Propagation.NESTED,rollbackFor = {RuntimeException.class, Error.class})
    public OperModel cleanBoxService(String username ,HistoryOper historyOper) {
        // 获取蜂箱号
        String bbid = historyOper.getBbid();
        // 创建返回模板
        OperModel operModel = new OperModel();
        operModel.setBbid(bbid);
        // 查询当前是否在清洗中
        UserBeebox ub1 = userBeeboxService.selectUserBeeboxByBbid(bbid);
        Integer cleaning = ub1.getCleaning();
        if (ub1.getNetStatus() == 1){
            operModel.setErrorMessage("蜂箱["+bbid+"]未联网");
            return operModel;
        }
        if (cleaning == 0) {
            try {
                // 2019-12-24 添加  清洗超时判断
                HistoryOper lastOper = historyOperService.selectLastOperHistory(bbid, "clean");
                if (lastOper != null && lastOper.getCleanStatus() == 2 ){
                    // 上次清洗未结束
                    long time = lastOper.getCleanTime().getTime() + (lastOper.getCleanLong()+DOOR_CLEAN_TIME) * 60000;// 预计清洗结束时间 + 巢门开关时间
                    long end = new Date().getTime();
                    if (time < end){
                        lastOper.setCleanStatus(1);
                        historyOperMapper.updateByPrimaryKeySelective(lastOper);
                    }else{// 预计结束时间大于当前时间 --> 清洗未结束
                        // 当前正在清洗中
                        operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:正在清洗中");
                        return operModel;
                    }
                }

                // 定时任务
                Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
                        if (userBeebox.getCleaning() == 1){
                            try {
                                // 插入历史操作信息表
                                historyOperMapper.insertSelective(historyOper);
                            } catch (Exception e) {
                                operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:插入操作历史记录失败");
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            }
                        }
                    }
                },3*1000);//延迟3s判断继电器是否开启
//                try { // 替换成上面那段 23s判断继电器是否开启
//                    // 插入历史操作信息表
//                    historyOperMapper.insertSelective(historyOper);
//                } catch (Exception e) {
//                    operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:插入操作历史记录失败");
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                }
                /*try {
                    // 设置蜂箱状态-->[清洗中]
                    UserBeebox userBeebox = UserBeebox.builder().bbid(bbid).cleaning(1).build();
                    userBeeboxService.updateUserBeebox(userBeebox);
                } catch (Exception e) {
                    operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:更新状态失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }*/
                // 加入消息队列
                MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.CLEAN_OPEN+"\"}");
//                QueueUtil.addQueueOper(bbid, QueueUtil.CLEAN_OPEN);
                // 定时器设定清洗时长
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.CLEAN_CLOSE+"\"}");
//                        QueueUtil.addQueueOper(bbid, QueueUtil.CLEAN_CLOSE);
                    }
                },historyOper.getCleanLong() * 60000);//清洗时长
            } catch (Exception e) {
                operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:其他未知异常");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        } else {
            // 当前正在清洗中
            operModel.setErrorMessage("蜂箱["+bbid+"]清洗失败:正在清洗中");
        }
        return operModel;
    }


    /**
     * 喂食
     * @param username
     * @param historyOper
     * @param feedTime
     * @return
     */
    @Transactional(propagation = Propagation.NESTED,rollbackFor = {RuntimeException.class, Error.class})
    public OperModel feedBoxService(String username,HistoryOper historyOper,Integer feedTime) {
        // 获取蜂箱号
        String bbid = historyOper.getBbid();
        // 获取喂食命令
        Integer feedOrder = historyOper.getFeedOrder();
        // 创建返回模板
        OperModel operModel = new OperModel();
        operModel.setBbid(bbid);
        // 查询当前是否在喂食中
        UserBeebox ub = userBeeboxService.selectUserBeeboxByBbid(bbid);
        Integer feeding = ub.getFeeding();
        if (ub.getNetStatus() == 1){
            operModel.setErrorMessage("蜂箱["+bbid+"]未联网");
            return operModel;
        }
        if (feeding == 0) {// 未在喂食中

            try {
                // 2019-12-24 添加  喂食超时判断
                HistoryOper lastfeed = historyOperService.selectLastOperHistory(bbid, "feed");
//                Integer oldFeedOrder = null;
//                if (lastfeed != null) {
//                    oldFeedOrder = lastfeed.getFeedOrder();
//                }
                if (lastfeed != null && lastfeed.getFeedStatus() == 2 ){// 判断最后一次喂食状态是否是[喂食中]
                    // 上次喂食未结束
                    long time = lastfeed.getFeedTime().getTime();
                    time = time + feedTime * 60000;// 预计喂食结束时间
                    long end = new Date().getTime(); //当前时间
                    if (time < end){
                        lastfeed.setFeedStatus(1);
                        historyOperMapper.updateByPrimaryKeySelective(lastfeed);
                    }else{// 预计结束时间大于当前时间 --> 喂食未结束
                        // 当前正在喂食中
                        operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:正在喂食中");
                        return operModel;
                    }
                }

                // 定时任务
                Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
                        if (userBeebox.getFeeding() == 1){
                            try {
                                // 插入历史操作信息表
                                historyOperMapper.insertSelective(historyOper);
                            } catch (Exception e) {
                                operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:插入操作历史记录失败");
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            }
                        }
                    }
                },23*1000);//延迟23s判断继电器是否开启
                /*try { // 替换成上面那段 23s判断继电器是否开启
                    // 插入历史操作信息表
                    historyOperMapper.insertSelective(historyOper);
                } catch (Exception e) {
                    operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:插入操作历史记录失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }*/
               /* try { // 由MQTT返回消息处理
                    // 设置蜂箱状态-->[喂食中]
                    UserBeebox userBeebox = UserBeebox.builder().bbid(bbid).feeding(1).build();
                    userBeeboxService.updateUserBeebox(userBeebox);
                } catch (Exception e) {
                    operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:更新状态失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }*/
                // 加入消息队列
                if (feedOrder == null){
                    System.out.println("喂食命令:null");
                    operModel.setErrorMessage("喂食命令为空");
                    return operModel;
                }else if (feedOrder == 0){
                    MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.FEED_SUPP_OPEN+"\"}");
//                    QueueUtil.addQueueOper(bbid, QueueUtil.FEED_SUPP_OPEN);// 补充喂食
                }else{
                    MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.FEED_REWD_OPEN+"\"}");
//                    QueueUtil.addQueueOper(bbid, QueueUtil.FEED_REWD_OPEN);// 奖励喂食
                }

                // 定时器设定喂食时长
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (feedOrder == 0){
                            MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.FEED_SUPP_CLOSE+"\"}");
//                            QueueUtil.addQueueOper(bbid, QueueUtil.FEED_SUPP_CLOSE);
                        } else{
                            MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.FEED_REWD_CLOSE+"\"}");
//                            QueueUtil.addQueueOper(bbid, QueueUtil.FEED_REWD_CLOSE);
                        }
                    }
                },feedTime * 60000);//喂食时长

            } catch (Exception e) {
                operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:其他未知异常");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        } else {
            // 当前正在喂食中
            operModel.setErrorMessage("蜂箱["+bbid+"]喂食失败:正在喂食中");
        }
        return operModel;
    }

    /**
     * 加热
     * @param historyOper
     * @param oper 0关闭 1开启
     * @return
     */
    @Transactional(propagation = Propagation.NESTED,rollbackFor = {RuntimeException.class, Error.class})
    public OperModel heatBoxService(String username ,HistoryOper historyOper,Integer oper) {
        // 获取蜂箱号
        String bbid = historyOper.getBbid();
        // 创建返回模板
        OperModel operModel = new OperModel();
        operModel.setBbid(bbid);
        if (oper == 1){ // 开启加热
            try {
                /*
                try {
                    // 插入历史操作信息表
                    historyOperMapper.insertSelective(historyOper);
                } catch (Exception e) {
                    operModel.setErrorMessage("加热失败:插入操作历史记录失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                 */
                /*
                try {
                    // 设置蜂箱状态-->[加热中]
                    UserBeebox userBeebox = new UserBeebox();
                    userBeebox.setBbid(bbid);
                    userBeebox.setHeating(1);
                    userBeeboxService.updateUserBeebox(userBeebox);
                } catch (Exception e) {
                    operModel.setErrorMessage("加热失败:更新蜂箱状态失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                */
                // 查询最后一条加热记录
                HistoryOper lastHeat = historyOperService.selectLastOperHistory(bbid, "heat");

                if (lastHeat != null && lastHeat.getHeatStatus() == 2 && lastHeat.getHeatEndTemper() == null){
                    historyOperMapper.deleteByPrimaryKey(lastHeat.getId());
                }
                // 加入消息队列 开启加热
                MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.HEAT_OPEN+"\"}");
//                QueueUtil.addQueueOper(bbid, QueueUtil.HEAT_OPEN);
                // 定时任务
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
                        if (userBeebox.getHeating() == 1){
                            try {
                                // 插入历史操作信息表
                                historyOperMapper.insertSelective(historyOper);
                            } catch (Exception e) {
                                operModel.setErrorMessage("蜂箱["+bbid+"]加热失败:插入操作历史记录失败");
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            }
                        }
                        /*
                        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
                        if (userBeebox.getHeating() != 1){
                            // 未开启加热  -->  历史记录表设置为失败
                            historyOper.setHeatEndTemper(historyOper.getHeatStartTemper());
                            historyOper.setHeatEndtime(historyOper.getHeatStarttime());
                            historyOper.setHeatStatus(1);// 加热失败
                            historyOperService.updateHistoryOper(historyOper);
                        }
                        */
                    }
                },23*1000);//延迟23s判断继电器是否开启
            } catch (Exception e) {
                operModel.setErrorMessage("蜂箱["+bbid+"]加热失败:其他未知异常");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }else{// 关闭加热
            try {
                /*
                try {
                    // 更新历史操作信息表
                    historyOperMapper.updateByPrimaryKeySelective(historyOper);
                }
                catch (Exception e) {
                    operModel.setErrorMessage("停止加热失败:插入操作历史记录失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//
                }
                 */
                /*
                try {
                    // 设置蜂箱状态-->[未在加热中]
                    UserBeebox userBeebox = new UserBeebox();
                    userBeebox.setBbid(bbid);
                    userBeebox.setHeating(0);
                    userBeeboxService.updateUserBeebox(userBeebox);
                } catch (Exception e) {
                    operModel.setErrorMessage("停止加热失败:更新蜂箱状态失败");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                 */
                // 加入消息队列 关闭加热
                MqttClientUtil.publish_common(MqttClientUtil.TOPIC_PREFIX_DEVICE+"/"+username+"/server","{\"cmd\":\"R"+bbid+":"+QueueUtil.HEAT_CLOSE+"\"}");
//                QueueUtil.addQueueOper(bbid, QueueUtil.HEAT_CLOSE);
                // 定时任务
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(bbid);
                        if (userBeebox.getHeating() == 0 ){
                            // 更新历史操作信息表
                            historyOperMapper.updateByPrimaryKeySelective(historyOper);
                        }
                    }
                },23*1000);//延迟23s判断继电器是否关闭成功
            } catch (Exception e) {
                operModel.setErrorMessage("蜂箱["+bbid+"]停止加热失败:其他未知异常");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }
        return operModel;
    }


}
