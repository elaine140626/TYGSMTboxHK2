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
public class WeightFrameModel implements Comparable<WeightFrameModel>{

    /**
     * 蜂箱id
     */
    private String bbid;

    /**
     * 重量/框数
     */
    private Double weightFrame;

    /**
     * 时间
     */
    private Date recvTime;

    @Override
    public int compareTo(WeightFrameModel o) {
        if (this.weightFrame > o.weightFrame){
            return 1;
        }else{
            return -1;
        }
    }
}
