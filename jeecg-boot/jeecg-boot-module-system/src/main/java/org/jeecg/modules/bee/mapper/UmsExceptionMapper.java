package org.jeecg.modules.bee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.entity.UmsException;
import org.jeecg.modules.bee.entity.UmsExceptionExample;

public interface UmsExceptionMapper {
    long countByExample(UmsExceptionExample example);

    int deleteByExample(UmsExceptionExample example);

    int insert(UmsException record);

    int insertSelective(UmsException record);

    List<UmsException> selectByExample(UmsExceptionExample example);

    int updateByExampleSelective(@Param("record") UmsException record, @Param("example") UmsExceptionExample example);

    int updateByExample(@Param("record") UmsException record, @Param("example") UmsExceptionExample example);
}