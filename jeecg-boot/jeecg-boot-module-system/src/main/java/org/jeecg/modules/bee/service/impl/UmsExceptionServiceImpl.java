package org.jeecg.modules.bee.service.impl;

import org.jeecg.modules.bee.entity.UmsException;
import org.jeecg.modules.bee.entity.UmsExceptionExample;
import org.jeecg.modules.bee.mapper.UmsExceptionMapper;
import org.jeecg.modules.bee.service.UmsExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UmsExceptionServiceImpl implements UmsExceptionService {

    @Resource
    private UmsExceptionMapper umsExceptionMapper;

    /**
     * 插入异常信息
     * @param umsException
     */
    @Override
    public void insertException(UmsException umsException) {
        umsExceptionMapper.insert(umsException);
    }

    @Override
    public List<UmsException> selectByUid(String uid, Integer days) {
        Date date = new Date();
        date.setTime(date.getTime() - days*86400000); // n天
        UmsExceptionExample umsExceptionExample = new UmsExceptionExample();
        umsExceptionExample.createCriteria().andUidEqualTo(uid).andTimeGreaterThan(date);
        return umsExceptionMapper.selectByExample(umsExceptionExample);
    }
}
