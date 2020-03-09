package org.jeecg.modules.bee.entity;

import java.io.Serializable;

/**
 * ums_sys_config
 * @author 
 */
public class SysConfig implements Serializable {
    /**
     * 用户id
     */
    private String uid;

    /**
     * 长期保存 早
     */
    private String savetime1;

    /**
     * 长期保存 中
     */
    private String savetime2;

    /**
     * 长期保存 晚
     */
    private String savetime3;

    /**
     * 喂食重量(临界重量/脾数)  单位g
     */
    private Integer feedWeight;

    /**
     * 逃跑重量  单位g
     */
    private Integer escapeWeight;

    /**
     * 加热下限温度 默认5度
     */
    private Double heatLower;

    /**
     * 加热上限温度 默认20度
     */
    private Double heatUpper;

    /**
     * 标准重量/脾数值的百分比 (控王的重量/框数比)
     */
    private Double kingCtrlRate;

    /**
     * 时间间隔量 (通风档)
     */
    private Integer draftInterval;

    /**
     * 减少百分比
     */
    private Double decreseRate;

    /**
     * 标准脾重 g
     */
    private Integer stdWeight;

    /**
     * 蜂箱状态 低重量时的重量 红色
     */
    private Integer boxLowWeight;

    /**
     * 蜂箱状态 正常的重量  绿色
     */
    private Integer boxNormalWeight;

    /**
     * 历史记录显示多少天 (蜂箱信息)
     */
    private Integer hisShowDays;

    /**
     * 产出误差重量
     */
    private Integer outputIgnoreWeight;

    /**
     * 清洗时长
     */
    private Integer cleanLong;

    /**
     * 温度提示线
     */
    private Double hintTemper;

    /**
     * 温度框数比提示线
     */
    private Double hintWeightFrame;

    /**
     * 重量变化率提示线
     */
    private Double hintWeightChange;

    /**
     * 产出统计中 0正常 1蜜统计中 2脾统计中 3脾统计结束未确认
     */
    private Integer outputing;

    /**
     * 自动喂食开始时间  0-23
     */
    private Integer autoFeedStartHour;

    /**
     * 自动喂食结束时间   0-23
     */
    private Integer autoFeedEndHour;

    /**
     * 奖励喂食时长(min)
     */
    private Integer feedRewordLong;

    /**
     * 补充喂食时长(min)
     */
    private Integer feedSuppLong;

    private static final long serialVersionUID = 1L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSavetime1() {
        return savetime1;
    }

    public void setSavetime1(String savetime1) {
        this.savetime1 = savetime1;
    }

    public String getSavetime2() {
        return savetime2;
    }

    public void setSavetime2(String savetime2) {
        this.savetime2 = savetime2;
    }

    public String getSavetime3() {
        return savetime3;
    }

    public void setSavetime3(String savetime3) {
        this.savetime3 = savetime3;
    }

    public Integer getFeedWeight() {
        return feedWeight;
    }

    public void setFeedWeight(Integer feedWeight) {
        this.feedWeight = feedWeight;
    }

    public Integer getEscapeWeight() {
        return escapeWeight;
    }

    public void setEscapeWeight(Integer escapeWeight) {
        this.escapeWeight = escapeWeight;
    }

    public Double getHeatLower() {
        return heatLower;
    }

    public void setHeatLower(Double heatLower) {
        this.heatLower = heatLower;
    }

    public Double getHeatUpper() {
        return heatUpper;
    }

    public void setHeatUpper(Double heatUpper) {
        this.heatUpper = heatUpper;
    }

    public Double getKingCtrlRate() {
        return kingCtrlRate;
    }

    public void setKingCtrlRate(Double kingCtrlRate) {
        this.kingCtrlRate = kingCtrlRate;
    }

    public Integer getDraftInterval() {
        return draftInterval;
    }

    public void setDraftInterval(Integer draftInterval) {
        this.draftInterval = draftInterval;
    }

    public Double getDecreseRate() {
        return decreseRate;
    }

    public void setDecreseRate(Double decreseRate) {
        this.decreseRate = decreseRate;
    }

    public Integer getStdWeight() {
        return stdWeight;
    }

    public void setStdWeight(Integer stdWeight) {
        this.stdWeight = stdWeight;
    }

    public Integer getBoxLowWeight() {
        return boxLowWeight;
    }

    public void setBoxLowWeight(Integer boxLowWeight) {
        this.boxLowWeight = boxLowWeight;
    }

    public Integer getBoxNormalWeight() {
        return boxNormalWeight;
    }

    public void setBoxNormalWeight(Integer boxNormalWeight) {
        this.boxNormalWeight = boxNormalWeight;
    }

    public Integer getHisShowDays() {
        return hisShowDays;
    }

    public void setHisShowDays(Integer hisShowDays) {
        this.hisShowDays = hisShowDays;
    }

    public Integer getOutputIgnoreWeight() {
        return outputIgnoreWeight;
    }

    public void setOutputIgnoreWeight(Integer outputIgnoreWeight) {
        this.outputIgnoreWeight = outputIgnoreWeight;
    }

    public Integer getCleanLong() {
        return cleanLong;
    }

    public void setCleanLong(Integer cleanLong) {
        this.cleanLong = cleanLong;
    }

    public Double getHintTemper() {
        return hintTemper;
    }

    public void setHintTemper(Double hintTemper) {
        this.hintTemper = hintTemper;
    }

    public Double getHintWeightFrame() {
        return hintWeightFrame;
    }

    public void setHintWeightFrame(Double hintWeightFrame) {
        this.hintWeightFrame = hintWeightFrame;
    }

    public Double getHintWeightChange() {
        return hintWeightChange;
    }

    public void setHintWeightChange(Double hintWeightChange) {
        this.hintWeightChange = hintWeightChange;
    }

    public Integer getOutputing() {
        return outputing;
    }

    public void setOutputing(Integer outputing) {
        this.outputing = outputing;
    }

    public Integer getAutoFeedStartHour() {
        return autoFeedStartHour;
    }

    public void setAutoFeedStartHour(Integer autoFeedStartHour) {
        this.autoFeedStartHour = autoFeedStartHour;
    }

    public Integer getAutoFeedEndHour() {
        return autoFeedEndHour;
    }

    public void setAutoFeedEndHour(Integer autoFeedEndHour) {
        this.autoFeedEndHour = autoFeedEndHour;
    }

    public Integer getFeedRewordLong() {
        return feedRewordLong;
    }

    public void setFeedRewordLong(Integer feedRewordLong) {
        this.feedRewordLong = feedRewordLong;
    }

    public Integer getFeedSuppLong() {
        return feedSuppLong;
    }

    public void setFeedSuppLong(Integer feedSuppLong) {
        this.feedSuppLong = feedSuppLong;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysConfig other = (SysConfig) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getSavetime1() == null ? other.getSavetime1() == null : this.getSavetime1().equals(other.getSavetime1()))
            && (this.getSavetime2() == null ? other.getSavetime2() == null : this.getSavetime2().equals(other.getSavetime2()))
            && (this.getSavetime3() == null ? other.getSavetime3() == null : this.getSavetime3().equals(other.getSavetime3()))
            && (this.getFeedWeight() == null ? other.getFeedWeight() == null : this.getFeedWeight().equals(other.getFeedWeight()))
            && (this.getEscapeWeight() == null ? other.getEscapeWeight() == null : this.getEscapeWeight().equals(other.getEscapeWeight()))
            && (this.getHeatLower() == null ? other.getHeatLower() == null : this.getHeatLower().equals(other.getHeatLower()))
            && (this.getHeatUpper() == null ? other.getHeatUpper() == null : this.getHeatUpper().equals(other.getHeatUpper()))
            && (this.getKingCtrlRate() == null ? other.getKingCtrlRate() == null : this.getKingCtrlRate().equals(other.getKingCtrlRate()))
            && (this.getDraftInterval() == null ? other.getDraftInterval() == null : this.getDraftInterval().equals(other.getDraftInterval()))
            && (this.getDecreseRate() == null ? other.getDecreseRate() == null : this.getDecreseRate().equals(other.getDecreseRate()))
            && (this.getStdWeight() == null ? other.getStdWeight() == null : this.getStdWeight().equals(other.getStdWeight()))
            && (this.getBoxLowWeight() == null ? other.getBoxLowWeight() == null : this.getBoxLowWeight().equals(other.getBoxLowWeight()))
            && (this.getBoxNormalWeight() == null ? other.getBoxNormalWeight() == null : this.getBoxNormalWeight().equals(other.getBoxNormalWeight()))
            && (this.getHisShowDays() == null ? other.getHisShowDays() == null : this.getHisShowDays().equals(other.getHisShowDays()))
            && (this.getOutputIgnoreWeight() == null ? other.getOutputIgnoreWeight() == null : this.getOutputIgnoreWeight().equals(other.getOutputIgnoreWeight()))
            && (this.getCleanLong() == null ? other.getCleanLong() == null : this.getCleanLong().equals(other.getCleanLong()))
            && (this.getHintTemper() == null ? other.getHintTemper() == null : this.getHintTemper().equals(other.getHintTemper()))
            && (this.getHintWeightFrame() == null ? other.getHintWeightFrame() == null : this.getHintWeightFrame().equals(other.getHintWeightFrame()))
            && (this.getHintWeightChange() == null ? other.getHintWeightChange() == null : this.getHintWeightChange().equals(other.getHintWeightChange()))
            && (this.getOutputing() == null ? other.getOutputing() == null : this.getOutputing().equals(other.getOutputing()))
            && (this.getAutoFeedStartHour() == null ? other.getAutoFeedStartHour() == null : this.getAutoFeedStartHour().equals(other.getAutoFeedStartHour()))
            && (this.getAutoFeedEndHour() == null ? other.getAutoFeedEndHour() == null : this.getAutoFeedEndHour().equals(other.getAutoFeedEndHour()))
            && (this.getFeedRewordLong() == null ? other.getFeedRewordLong() == null : this.getFeedRewordLong().equals(other.getFeedRewordLong()))
            && (this.getFeedSuppLong() == null ? other.getFeedSuppLong() == null : this.getFeedSuppLong().equals(other.getFeedSuppLong()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getSavetime1() == null) ? 0 : getSavetime1().hashCode());
        result = prime * result + ((getSavetime2() == null) ? 0 : getSavetime2().hashCode());
        result = prime * result + ((getSavetime3() == null) ? 0 : getSavetime3().hashCode());
        result = prime * result + ((getFeedWeight() == null) ? 0 : getFeedWeight().hashCode());
        result = prime * result + ((getEscapeWeight() == null) ? 0 : getEscapeWeight().hashCode());
        result = prime * result + ((getHeatLower() == null) ? 0 : getHeatLower().hashCode());
        result = prime * result + ((getHeatUpper() == null) ? 0 : getHeatUpper().hashCode());
        result = prime * result + ((getKingCtrlRate() == null) ? 0 : getKingCtrlRate().hashCode());
        result = prime * result + ((getDraftInterval() == null) ? 0 : getDraftInterval().hashCode());
        result = prime * result + ((getDecreseRate() == null) ? 0 : getDecreseRate().hashCode());
        result = prime * result + ((getStdWeight() == null) ? 0 : getStdWeight().hashCode());
        result = prime * result + ((getBoxLowWeight() == null) ? 0 : getBoxLowWeight().hashCode());
        result = prime * result + ((getBoxNormalWeight() == null) ? 0 : getBoxNormalWeight().hashCode());
        result = prime * result + ((getHisShowDays() == null) ? 0 : getHisShowDays().hashCode());
        result = prime * result + ((getOutputIgnoreWeight() == null) ? 0 : getOutputIgnoreWeight().hashCode());
        result = prime * result + ((getCleanLong() == null) ? 0 : getCleanLong().hashCode());
        result = prime * result + ((getHintTemper() == null) ? 0 : getHintTemper().hashCode());
        result = prime * result + ((getHintWeightFrame() == null) ? 0 : getHintWeightFrame().hashCode());
        result = prime * result + ((getHintWeightChange() == null) ? 0 : getHintWeightChange().hashCode());
        result = prime * result + ((getOutputing() == null) ? 0 : getOutputing().hashCode());
        result = prime * result + ((getAutoFeedStartHour() == null) ? 0 : getAutoFeedStartHour().hashCode());
        result = prime * result + ((getAutoFeedEndHour() == null) ? 0 : getAutoFeedEndHour().hashCode());
        result = prime * result + ((getFeedRewordLong() == null) ? 0 : getFeedRewordLong().hashCode());
        result = prime * result + ((getFeedSuppLong() == null) ? 0 : getFeedSuppLong().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", savetime1=").append(savetime1);
        sb.append(", savetime2=").append(savetime2);
        sb.append(", savetime3=").append(savetime3);
        sb.append(", feedWeight=").append(feedWeight);
        sb.append(", escapeWeight=").append(escapeWeight);
        sb.append(", heatLower=").append(heatLower);
        sb.append(", heatUpper=").append(heatUpper);
        sb.append(", kingCtrlRate=").append(kingCtrlRate);
        sb.append(", draftInterval=").append(draftInterval);
        sb.append(", decreseRate=").append(decreseRate);
        sb.append(", stdWeight=").append(stdWeight);
        sb.append(", boxLowWeight=").append(boxLowWeight);
        sb.append(", boxNormalWeight=").append(boxNormalWeight);
        sb.append(", hisShowDays=").append(hisShowDays);
        sb.append(", outputIgnoreWeight=").append(outputIgnoreWeight);
        sb.append(", cleanLong=").append(cleanLong);
        sb.append(", hintTemper=").append(hintTemper);
        sb.append(", hintWeightFrame=").append(hintWeightFrame);
        sb.append(", hintWeightChange=").append(hintWeightChange);
        sb.append(", outputing=").append(outputing);
        sb.append(", autoFeedStartHour=").append(autoFeedStartHour);
        sb.append(", autoFeedEndHour=").append(autoFeedEndHour);
        sb.append(", feedRewordLong=").append(feedRewordLong);
        sb.append(", feedSuppLong=").append(feedSuppLong);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}