package org.jeecg.modules.bee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.entity.UserBeebox;
import org.jeecg.modules.bee.entity.UserBeeboxExample;

public interface UserBeeboxMapper {
    long countByExample(UserBeeboxExample example);

    int deleteByExample(UserBeeboxExample example);

    int deleteByPrimaryKey(String bbid);

    int insert(UserBeebox record);

    int insertSelective(UserBeebox record);

    List<UserBeebox> selectByExample(UserBeeboxExample example);

    UserBeebox selectByPrimaryKey(String bbid);

    int updateByExampleSelective(@Param("record") UserBeebox record, @Param("example") UserBeeboxExample example);

    int updateByExample(@Param("record") UserBeebox record, @Param("example") UserBeeboxExample example);

    int updateByPrimaryKeySelective(UserBeebox record);

    int updateByPrimaryKey(UserBeebox record);
}