package org.jeecg.modules.bee.service;

import org.jeecg.modules.bee.entity.SysConfig;

public interface SysConfigService {

    /**
     * 获取蜂箱全局设定信息
     * @param uid 用户id
     * @return SysConfig
     */
    SysConfig selectByUid(String uid);

    /**
     * 新增蜂箱全局设定
     * @param uid 用户id
     */
    void insertConfig(String uid);

    /**
     * 删除全局设置
     * 用户注销时调用
     * @param uid
     */
    void deleteConfig(String uid);

    /**
     * 修改全局设定
     * @param sysconfig
     */
    void updateConfig(SysConfig sysconfig);
}
