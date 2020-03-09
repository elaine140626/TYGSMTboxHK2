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
public class EscapeScheduleModel {

    private String bbid;

    private Date recvTime;

    private Double weight;

    private String uid;

    private String cid;

    private Integer escapeWeight;
}
