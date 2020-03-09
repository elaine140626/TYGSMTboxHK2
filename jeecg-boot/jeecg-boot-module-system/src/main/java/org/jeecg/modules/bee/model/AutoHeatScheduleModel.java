package org.jeecg.modules.bee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoHeatScheduleModel {

    private String bbid;

    private String uid;

    private Double temper;

    private Date recvTime;

    /**
     * 自动加热开关 0关 1开
     */
    private Integer autoHeat;

    /**
     * 加热状态 0加热完成 1加热失败 2加热中
     */
    private Integer heatStatus;

    private Double heatLower;

    private Double heatUpper;

    /**
     * 加热中
     */
    private Integer heating;

    /**
     * 历史操作表id
     */
    private Integer operHisId;

}
