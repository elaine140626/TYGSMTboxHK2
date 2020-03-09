package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.mapper.BeeboxInfoMapper;
import org.jeecg.modules.bee.mapper.BeeboxInfoShowMapper;
import org.jeecg.modules.bee.model.AutoFeedScheduleModel;
import org.jeecg.modules.bee.model.AutoHeatScheduleModel;
import org.jeecg.modules.bee.model.OutputWeightModel;
import org.jeecg.modules.bee.model.WeightChangeModel;
import org.jeecg.modules.bee.service.BeeboxInfoService;
import org.jeecg.modules.bee.service.UserBeeboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BeeboxInfoServiceImpl implements BeeboxInfoService {

    @Resource
    private BeeboxInfoMapper beeboxInfoMapper;

    @Resource
    private BeeboxInfoShowMapper beeboxInfoShowMapper;

    @Resource
    private UserBeeboxService userBeeboxService;

    @Override
    public void insertBeeboxInfo(BeeboxInfo beeboxInfo) {
        UserBeebox userBeebox = userBeeboxService.selectUserBeeboxByBbid(beeboxInfo.getBbid());
        Integer frameNum = userBeebox.getFrameNum();
        if (frameNum == 0){
            // 若框数为0 则重量与框数比设置为0
            beeboxInfo.setWeightFrameRate(0d);
        }else{
            beeboxInfo.setWeightFrameRate(beeboxInfo.getWeight() / frameNum);
        }
        beeboxInfoMapper.insert(beeboxInfo);
    }

    /**
     * 查询所有蜂箱信息
     * @return
     */
    @Override
    public List<BeeboxInfo> selectAllBeeboxInfo() {
        return beeboxInfoShowMapper.selectNowBoxInfo();
    }

    /**
     * 查询最近20s内蜂箱信息以及喂食重量 [自动喂食]
     * @return
     */
    @Override
    public List<AutoFeedScheduleModel> selectNowWFRAndFeedWeight() {
        return beeboxInfoShowMapper.selectNowWFRAndFeedWeight();
    }


    /**
     * /[自动加热]
     * @return
     */
    @Override
    public List<AutoHeatScheduleModel> selectNowTemperInfo() {
        return beeboxInfoShowMapper.selectNowTemperInfo();
    }

    /**
     * 查询40s内最新一次蜂箱信息
     * 40s保险用
     * @param uid
     * @param orderBy
     * @return
     */
    @Override
    public List<BeeboxInfo> selectNowBeeboxInfoByUid(String uid,String orderBy) {
        return beeboxInfoShowMapper.selectNowBoxInfoByUid(uid,orderBy);
    }

    @Override
    public BeeboxInfo selectNowBeeboxInfoByBbid(String bbid) {
        List<BeeboxInfo> beeboxInfos = beeboxInfoShowMapper.selectNowBoxInfoByBbid(bbid);
        if (beeboxInfos.size() > 0){
            return beeboxInfos.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<OutputWeightModel> selectNowBoxInfoByBbids(List<String> bbids) {
        return beeboxInfoShowMapper.selectOutputWeightByBbids(bbids);
    }

    @Override
    public List<OutputWeightModel> selectOutputWeightByUid(String uid) {
        return beeboxInfoShowMapper.selectOutputWeightByUid(uid);
    }

    /**
     * 查询40s内最近两次的重量变化率
     * @param uid
     * @return
     */
    @Override
    public List<WeightChangeModel> selectWeightChangeByUid(String uid) {
        List<WeightChangeModel> resList = new LinkedList<>();
        List<BeeboxInfo> beeboxInfos = beeboxInfoShowMapper.selectBoxInfoIn40sByUid(uid);
        boolean flag = false;
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
                        WeightChangeModel weightChangeModel = WeightChangeModel.builder().bbid(bbid1).weightChange(wc).build();
                        resList.add(weightChangeModel);
                    }
                } else {
                    flag = false;
                }
            }
            Collections.sort(resList);
        }else{
            return null;
        }

        return resList;
    }


}
