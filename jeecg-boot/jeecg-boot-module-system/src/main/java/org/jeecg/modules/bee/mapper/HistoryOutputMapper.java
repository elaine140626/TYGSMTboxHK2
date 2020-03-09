package org.jeecg.modules.bee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bee.entity.HistoryOper;
import org.jeecg.modules.bee.entity.HistoryOutput;
import org.jeecg.modules.bee.entity.HistoryOutputExample;

public interface HistoryOutputMapper {
    long countByExample(HistoryOutputExample example);

    int deleteByExample(HistoryOutputExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HistoryOutput record);

    int insertSelective(HistoryOutput record);

    List<HistoryOutput> selectByExample(HistoryOutputExample example);

    HistoryOutput selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HistoryOutput record, @Param("example") HistoryOutputExample example);

    int updateByExample(@Param("record") HistoryOutput record, @Param("example") HistoryOutputExample example);

    int updateByPrimaryKeySelective(HistoryOutput record);

    int updateByPrimaryKey(HistoryOutput record);

    /**
     * 批量插入
     * @param historyOutputs
     */
    void insertMult(@Param("historyOutputs") List<HistoryOutput> historyOutputs);

    /**
     * 按时间范围查找
     * @param uid
     * @param startDate
     * @param endDate
     * @return
     */
    List<HistoryOutput> selectByTimeRangeUid(String uid , String startDate, String endDate);

    /**
     * 按时间范围查找
     * @param bbid
     * @return
     */
    List<HistoryOutput> selectByTimeRangeBbid(String bbid);
}