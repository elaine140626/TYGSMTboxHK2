package org.jeecg.modules.bee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutputWeightModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 蜂箱号
     */
    private String bbid;

    /**
     * 时间
     */
//    private Date recv_time;

    /**
     * 重量/框数
     */
    private Double weightFrameRate;

    /**
     * 重量
     */
    private Double weight;


}
