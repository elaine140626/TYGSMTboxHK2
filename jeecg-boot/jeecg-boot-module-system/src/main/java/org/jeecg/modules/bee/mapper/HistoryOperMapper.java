package org.jeecg.modules.bee.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.HistoryOperExample;

public interface HistoryOperMapper {
    long countByExample(HistoryOperExample example);

    int deleteByExample(HistoryOperExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HistoryOper record);

    int insertSelective(HistoryOper record);

    List<HistoryOper> selectByExample(HistoryOperExample example);

    HistoryOper selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HistoryOper record, @Param("example") HistoryOperExample example);

    int updateByExample(@Param("record") HistoryOper record, @Param("example") HistoryOperExample example);

    int updateByPrimaryKeySelective(HistoryOper record);

    int updateByPrimaryKey(HistoryOper record);

    /**
     * 查询所有蜂箱最后一次喂食记录
     * @return
     */
    List<HistoryOper> selectEveryLastOperHistory();

    /**
     * 查询蜂箱历史操作记录
     * @param bbid
     * @param time
     * @return
     */
    List<HistoryOper> selectHistoryOperByBbid(String bbid, Date time);

    /**
     * 查询蜂箱历史操作记录
     * @param uid
     * @param time
     * @return
     */
    List<HistoryOper> selectHistoryOperByUid(String uid,Date time);
}