package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.SysConfig;
import org.jeecg.modules.bee.mapper.SysConfigMapper;
import org.jeecg.modules.bee.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    /**
     * 获取蜂箱全局设定信息
     * @param uid 用户id
     * @return SysConfig
     */
    @Override
    public SysConfig selectByUid(String uid) {
        return sysConfigMapper.selectByPrimaryKey(uid);
    }

    /**
     * 新增蜂箱全局设定
     * 创建用户时调用
     * @param uid 用户id
     */
    @Override
    public void insertConfig(String uid) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setUid(uid);
        sysConfig.setSavetime1("06:00:00");
        sysConfig.setSavetime2("12:00:00");
        sysConfig.setSavetime3("18:00:00");
        sysConfig.setEscapeWeight(1000); // 逃跑重量 单位g
        sysConfig.setBoxLowWeight(500); // 蜂箱状态 低重量时的重量 红色
        sysConfig.setBoxNormalWeight(2000); // 蜂箱状态 正常的重量 绿色
        sysConfig.setDecreseRate(0.2); // 减少百分比
        sysConfig.setDraftInterval(40); // 时间间隔量 (通风档)
        sysConfig.setHeatLower(5d); // 加热下限温度 默认5度
        sysConfig.setHeatUpper(20d); // 加热上限温度 默认20度
        sysConfig.setHisShowDays(3);// 历史记录显示多少天 (蜂箱信息)
        sysConfig.setKingCtrlRate(0.5); // 标准重量/脾数值的百分比 (控王的重量/框数比)
        sysConfig.setOutputIgnoreWeight(100);// 产出误差重量
        sysConfig.setStdWeight(2000); // 标准脾重 g
        sysConfig.setCleanLong(5);// 默认清洗时长 5分钟
        sysConfig.setHintTemper(25d);// 温度提示线 25度
        sysConfig.setHintWeightChange(0d);// 重量变化率提示线
        sysConfig.setHintWeightFrame(2000d);// 重量/框数提示线
        sysConfigMapper.insertSelective(sysConfig);
    }

    /**
     * 删除全局设置
     * 用户注销时调用
     * @param uid
     */
    @Override
    public void deleteConfig(String uid) {
        sysConfigMapper.deleteByPrimaryKey(uid);
    }

    /**
     * 修改全局设定
     * @param sysconfig
     */
    @Override
    public void updateConfig(SysConfig sysconfig) {
        sysConfigMapper.updateByPrimaryKeySelective(sysconfig);
    }
}
