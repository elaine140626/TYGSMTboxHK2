package org.jeecg.modules.bee.service.impl;

import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.bee.entity.HistoryOutput;
import org.jeecg.modules.bee.entity.SysConfig;
import org.jeecg.modules.bee.mapper.HistoryOutputMapper;
import org.jeecg.modules.bee.model.OutputWeightModel;
import org.jeecg.modules.bee.service.BeeOutputService;
import org.jeecg.modules.bee.service.BeeboxInfoService;
import org.jeecg.modules.bee.service.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BeeOutputServiceImpl implements BeeOutputService {

    @Resource
    private BeeboxInfoService beeboxInfoService;

    @Resource
    private SysConfigService sysConfigService;

    @Resource
    private HistoryOutputMapper historyOutputMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<HistoryOutput> selectByTimeRangeUid(String uid, String startDate, String endDate) {
        return historyOutputMapper.selectByTimeRangeUid(uid, startDate, endDate);
    }

    @Override
    public List<HistoryOutput> selectByTimeRangeBbid(String bbid) {
        return historyOutputMapper.selectByTimeRangeBbid(bbid);
    }

    /**
     * 开始统计 蜂蜜产出
     * @param uid
     */
    @Override
    @Transactional(propagation = Propagation.NESTED,rollbackFor = {RuntimeException.class, Error.class})
    public int startOutputHoney(String uid) {
        String redisKey = "prefix_output_honey_" + uid;
        redisUtil.del(redisKey);// 删除上一次统计信息
        // 获取当前蜂箱最新一次的蜂箱信息
        List<OutputWeightModel> outputWeightModels = beeboxInfoService.selectOutputWeightByUid(uid);
        if (outputWeightModels.size() <= 0){
            return -1;
        }
        // 设置用户产出状态[蜜统计中]
        SysConfig sysConfig = new SysConfig();
        sysConfig.setUid(uid);
        sysConfig.setOutputing(1);
        sysConfigService.updateConfig(sysConfig);
        // 存入当前蜂箱信息到redis中
        redisUtil.set(redisKey,outputWeightModels,43200);
        return 1;
    }

    /**
     * 结束统计蜂蜜产出
     * @param uid
     */
    @Override
    public List<HistoryOutput> endOutputHoney(String uid) {
        String redisKey = "prefix_output_honey_" + uid;
        List<String> bbids = new ArrayList<>();
        List<HistoryOutput> historyOutputs = new LinkedList<>();
        // 获取用户设定误差
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer outputIgnoreWeight = sysConfig.getOutputIgnoreWeight();
        // 获取redis中的开始统计时的蜂箱信息
        Object o = redisUtil.get(redisKey);
        List<OutputWeightModel> start = redisUtil.castList(o, OutputWeightModel.class);
        for (OutputWeightModel owm : start){
            bbids.add(owm.getBbid());
        }
        // 获取结束统计时的蜂箱信息
        List<OutputWeightModel> end = beeboxInfoService.selectNowBoxInfoByBbids(bbids);
        // 添加结束时的蜂箱信息到开始统计时的list中
        start.addAll(end);
        if (start.size() >= 2){
            // 按bbid排序
            Collections.sort(start, new Comparator<OutputWeightModel>() {
                public int compare(OutputWeightModel o1, OutputWeightModel o2) {
                    return o1.getBbid().compareTo(o2.getBbid());
                }
            });
            Date date = new Date();
            for (int i=0;i<start.size()-1;i++){
                OutputWeightModel owm1 = start.get(i);
                OutputWeightModel owm2 = start.get(i + 1);
                String bbid1 = owm1.getBbid();
                String bbid2 = owm2.getBbid();
                // 只取记录前两条
                if (bbid1.equals(bbid2)){
                    // 计算重量变化
                    double honey = 0;
                    honey = Math.abs(owm1.getWeight() - owm2.getWeight());
                    HistoryOutput historyOutput = new HistoryOutput();
                    historyOutput.setBbid(bbid1);
                    // 忽略范围内 设置产出蜜为0
                    if (honey < outputIgnoreWeight){
                        historyOutput.setHoney(0d);
                    }else{
                        historyOutput.setHoney(honey);
                    }
                    historyOutput.setTime(date);
                    historyOutputs.add(historyOutput);
                }
            }
        }
        // 蜂蜜产出无需确认
        // 设置用户产出状态[正常]
        sysConfig.setUid(uid);
        sysConfig.setOutputing(0);
        sysConfigService.updateConfig(sysConfig);
        // 删除redis中的缓存
        redisUtil.del(redisKey);

        return historyOutputs;
    }


    /**
     * 开始统计 脾数产出
     * @param uid
     */
    @Override
    public int startOutputFrame(String uid) {
        String redisKey = "prefix_output_frame_" + uid;
        redisUtil.del(redisKey);// 删除上一次统计信息
        // 获取当前蜂箱最新一次的蜂箱信息
        List<OutputWeightModel> outputWeightModels = beeboxInfoService.selectOutputWeightByUid(uid);
        if (outputWeightModels.size() <= 0){
            return -1;
        }
        // 设置产出状态[脾统计中]
        SysConfig sysConfig = new SysConfig();
        sysConfig.setUid(uid);
        sysConfig.setOutputing(2);
        sysConfigService.updateConfig(sysConfig);
        // 存入当前蜂箱信息到redis中
        redisUtil.set(redisKey,outputWeightModels,43200);
        return 1;
    }

    /**
     * 结束统计 脾数产出
     * @param uid
     * @return
     */
    @Override
    public List<HistoryOutput> endOutputFrame(String uid) {
        String redisKey = "prefix_output_frame_" + uid;
        List<String> bbids = new ArrayList<>();
        List<HistoryOutput> historyOutputs = new LinkedList<>();
        // 获取误差重量
        SysConfig sysConfig = sysConfigService.selectByUid(uid);
        Integer outputIgnoreWeight = sysConfig.getOutputIgnoreWeight();
        // 获取redis中 开始统计时的蜂箱信息
        Object o = redisUtil.get(redisKey);
        List<OutputWeightModel> start = redisUtil.castList(o, OutputWeightModel.class);
        for (OutputWeightModel owm : start){
            bbids.add(owm.getBbid());// 记录下开始统计时的蜂箱号
        }
        // 获取结束统计时的蜂箱信息
        List<OutputWeightModel> end = beeboxInfoService.selectNowBoxInfoByBbids(bbids);
        // 添加结束时的蜂箱信息到开始统计时的list中
        start.addAll(end);
        if (start.size() >= 2){
            // 按bbid排序
            Collections.sort(start, new Comparator<OutputWeightModel>() {
                public int compare(OutputWeightModel o1, OutputWeightModel o2) {
                    return o1.getBbid().compareTo(o2.getBbid());
                }
            });
            Date date = new Date();
            for (int i=0;i<start.size()-1;i++){
                OutputWeightModel owm1 = start.get(i);
                OutputWeightModel owm2 = start.get(i + 1);
                String bbid1 = owm1.getBbid();
                String bbid2 = owm2.getBbid();
                // 只取记录前两条
                if (bbid1.equals(bbid2)){
                    // 计算重量变化
                    int outFrame = 0;
                    // 产出框数 = |开始时重量 - 结束时重量| / (开始时重量/框数)
                    double wc = Math.abs(owm1.getWeight() - owm2.getWeight());
                    if (owm1.getWeightFrameRate() == 0 ){// 如果获取到的 重量/框数 为0     则用标准的重量/框数做除数
                        outFrame = (int) Math.round( wc / sysConfig.getStdWeight());
                    }else{
                        outFrame = (int) Math.round( wc / owm1.getWeightFrameRate());
                    }
                    HistoryOutput historyOutput = new HistoryOutput();
                    historyOutput.setBbid(bbid1);
                    if (wc < outputIgnoreWeight){
                        // 重量差 在忽略范围内 设置产出脾数为0
                        historyOutput.setFrame(0);
                    }else{
                        historyOutput.setFrame(outFrame);
                    }
                    historyOutput.setTime(date);
                    historyOutputs.add(historyOutput);
                }
            }
        }
        // 设置用户产出状态[脾统计结束未确认]
        sysConfig.setUid(uid);
        sysConfig.setOutputing(3);
        sysConfigService.updateConfig(sysConfig);

        return historyOutputs;
    }

    /**
     * 确认产出
     * @param historyOutputs
     * @param uid
     * @param type 1蜂蜜  2脾数
     */
    @Override
    public void confirmOutput(List<HistoryOutput> historyOutputs,String uid, Integer type) {
        String redisKey;
        if (type == 1){
            // 蜂蜜产出
            redisKey = "prefix_output_honey_" + uid;
        } else if (type == 2){
            // 脾数产出
            redisKey = "prefix_output_frame_" + uid;
        } else{
            throw new RuntimeException("类型错误");
        }
        // 设置用户产出状态[正常]
        SysConfig sysConfig = new SysConfig();
        sysConfig.setUid(uid);
        sysConfig.setOutputing(0);
        sysConfigService.updateConfig(sysConfig);
        // 删除redis中的缓存
        redisUtil.del(redisKey);
        // 存入产出历史表
        if (historyOutputs != null && historyOutputs.size() > 0){
            insertHistoryOutputList(historyOutputs);
        }
    }

    /**
     * 取消产出
     * @param uid
     * @param type
     */
    @Override
    public void cancelOutput(String uid, Integer type) {
        String redisKey;
        if (type == 1){
            // 蜂蜜产出
            redisKey = "prefix_output_honey_" + uid;
        } else if (type == 2){
            // 脾数产出
            redisKey = "prefix_output_frame_" + uid;
        } else{
            throw new RuntimeException("类型错误");
        }
        // 设置用户产出状态[正常]
        SysConfig sysConfig = new SysConfig();
        sysConfig.setUid(uid);
        sysConfig.setOutputing(0);
        sysConfigService.updateConfig(sysConfig);
        // 删除redis中的缓存
        redisUtil.del(redisKey);
    }

    @Override
    public void insertHistoryOutput(HistoryOutput historyOutput) {
        historyOutputMapper.insertSelective(historyOutput);
    }

    /**
     * 批量插入
     * @param historyOutputs
     */
    @Override
    public void insertHistoryOutputList(List<HistoryOutput> historyOutputs) {
        historyOutputMapper.insertMult(historyOutputs);
    }

}

// start
// T0001:12.46 12.34 0000\nT0002:12.46 12.34 0000\nT0003:23.46 12.34 0000\nT0005:12.46 12.34 0000\nT0006:12.46 18.34 0000\n

// end
// T0001:12.46 18.63 0000\nT0002:12.46 18.63 0000\nT0003:23.46 18.63 0000\nT0005:12.46 18.63 0000\nT0006:12.46 18.63 0000\n
