package org.jeecg.modules.bee.service;

import org.jeecg.modules.bee.entity.UmsException;

import java.util.List;

public interface UmsExceptionService {

    /**
     * 插入异常信息
     * @param umsException
     */
    void insertException(UmsException umsException);

    /**
     *
     * @return
     */
    List<UmsException> selectByUid(String uid,Integer days);
}
