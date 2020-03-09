package org.jeecg.modules.bee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.entity.BeeboxInfo;
import org.jeecg.modules.bee.entity.BeeboxInfoExample;
import org.jeecg.modules.bee.entity.BeeboxInfoKey;

public interface BeeboxInfoMapper {
    long countByExample(BeeboxInfoExample example);

    int deleteByExample(BeeboxInfoExample example);

    int deleteByPrimaryKey(BeeboxInfoKey key);

    int insert(BeeboxInfo record);

    int insertSelective(BeeboxInfo record);

    List<BeeboxInfo> selectByExample(BeeboxInfoExample example);

    BeeboxInfo selectByPrimaryKey(BeeboxInfoKey key);

    int updateByExampleSelective(@Param("record") BeeboxInfo record, @Param("example") BeeboxInfoExample example);

    int updateByExample(@Param("record") BeeboxInfo record, @Param("example") BeeboxInfoExample example);

    int updateByPrimaryKeySelective(BeeboxInfo record);

    int updateByPrimaryKey(BeeboxInfo record);
}