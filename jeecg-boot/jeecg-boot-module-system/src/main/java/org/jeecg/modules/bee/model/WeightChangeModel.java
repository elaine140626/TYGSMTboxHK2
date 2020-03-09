package org.jeecg.modules.bee.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeightChangeModel implements Comparable<WeightChangeModel>{

    /**
     * 蜂箱id
     */
    private String bbid;

    /**
     * 重量变化率
     */
    private Double weightChange;

    /**
     * 时间
     */
    private Date recvTime;

    @Override
    public int compareTo(WeightChangeModel o) {
        if (this.weightChange > o.weightChange){
            return 1;
        }else{
            return -1;
        }
    }
}
