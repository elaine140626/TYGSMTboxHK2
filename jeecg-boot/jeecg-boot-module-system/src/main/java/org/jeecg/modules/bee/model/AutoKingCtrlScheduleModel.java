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
public class AutoKingCtrlScheduleModel {

    private String bbid;

    private Date recvTime;

    private Double weightFrame;

    private String uid;

    private Double kingCtrlRate;

    private Double stdWeight;
}
