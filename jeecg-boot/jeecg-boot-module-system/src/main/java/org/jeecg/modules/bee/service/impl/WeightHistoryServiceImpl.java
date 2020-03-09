package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.mapper.BeeboxInfoShowMapper;
import org.jeecg.modules.bee.mapper.WeightHistoryMapper;
import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.model.WeightFrameModel;
import org.jeecg.modules.bee.service.WeightHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeightHistoryServiceImpl implements WeightHistoryService {

    @Resource
    private WeightHistoryMapper weightHistoryMapper;

    @Resource
    private BeeboxInfoShowMapper beeboxInfoShowMapper;

    /**
     * 查询历史重量变化率
     * @param bbid      蜂箱号
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param time      时间点
     * @return
     */
    @Override
    public List<WeightChangeModel> selectWeightChangeByTime(String bbid, String startDate, String endDate, String time) {
        return weightHistoryMapper.selectWeightChangeByTime(bbid,startDate,endDate,time);
    }

    /**
     * 查询历史重量/框数
     * @param bbid      蜂箱号
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param time      时间点
     * @return
     */
    @Override
    public List<WeightFrameModel> selectWeightFrameByTime(String bbid, String startDate, String endDate, String time) {
        return weightHistoryMapper.selectWeightFrameByTime(bbid,startDate,endDate,time);
    }

    @Override
    public void insertWeightChangeHistory(List<WeightChangeModel> weightChangeModels) {
        weightHistoryMapper.insertWeightChangeHistory(weightChangeModels);
    }

    @Override
    public void insertWeightFrameHistory(List<WeightFrameModel> weightFrameModels) {
        weightHistoryMapper.insertWeightFrameHistory(weightFrameModels);
    }

    /**
     * 查询所有重量变化率 并 插入重量变化率历史表
     * 定时器使用
     * @return
     */
    @Override
    public void weightChangeSchedule(Integer hour,Integer min,Integer sec) {
        List<WeightChangeModel> resList = new LinkedList<>();
        // 查询所有蜂箱最新的两条信息
        List<BeeboxInfo> beeboxInfos = beeboxInfoShowMapper.selectAllBoxInfoIn40s();
        boolean flag = false;
        // 设置接收时间为整点
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,hour,min,sec);
        Date setDate = calendar.getTime();

        if (beeboxInfos.size() >= 2) {// 至少包含两条数据
            for (int i = 0 ;i<beeboxInfos.size()-1; i++){
                BeeboxInfo bi1 = beeboxInfos.get(i);
                BeeboxInfo bi2 = beeboxInfos.get(i+1);
                String bbid1 = bi1.getBbid();
                String bbid2 = bi2.getBbid();
                // 只取记录前两条
                if (bbid1.equals(bbid2)){
                    // 计算重量变化率
                    if (!flag){
                        flag = true;
                        // 重量变化率（当前时刻测量重量-上一时刻测量重量）/上一时刻测量重量
                        double wc = 0;
                        try {
                            wc = (bi1.getWeight() - bi2.getWeight()) / bi2.getWeight();
                        } catch (Exception e) {
                            wc = 0;
                        }
                        WeightChangeModel weightChangeModel = WeightChangeModel.builder().bbid(bbid1).weightChange(wc).recvTime(setDate).build();
                        resList.add(weightChangeModel);
                    }
                } else {
                    flag = false;
                }
            }
            // 插入历史表
            insertWeightChangeHistory(resList);
        }

    }

    @Override
    public void weightFrameSchedule(Integer hour, Integer min, Integer sec) {
        List<WeightFrameModel> resList = new LinkedList<>();
        // 查询所有蜂箱最新的两条信息
        List<BeeboxInfo> beeboxInfos = beeboxInfoShowMapper.selectNowBoxInfo();
        boolean flag = false;
        // 设置接收时间为整点
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,hour,min,sec);
        Date setDate = calendar.getTime();

        for (BeeboxInfo bi : beeboxInfos){
            WeightFrameModel wfm = WeightFrameModel.builder().bbid(bi.getBbid()).weightFrame(bi.getWeight()).recvTime(setDate).build();
            resList.add(wfm);
        }
        // 插入历史表
        if (resList.size() > 0){
            insertWeightFrameHistory(resList);
        }
    }
}
