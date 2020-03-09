package org.jeecg.modules.bee.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OperModel implements Serializable {

    // 蜂箱号
    private String bbid;

    // 失败消息类型
    private String errorMessage;

}
