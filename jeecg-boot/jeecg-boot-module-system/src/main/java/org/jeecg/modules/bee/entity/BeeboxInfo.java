package org.jeecg.modules.bee.entity;

import java.io.Serializable;

/**
 * ums_beebox_info
 * @author 
 */
public class BeeboxInfo extends BeeboxInfoKey implements Serializable {
    private Double weightFrameRate;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 温度
     */
    private Double temper;

    private static final long serialVersionUID = 1L;

    public Double getWeightFrameRate() {
        return weightFrameRate;
    }

    public void setWeightFrameRate(Double weightFrameRate) {
        this.weightFrameRate = weightFrameRate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTemper() {
        return temper;
    }

    public void setTemper(Double temper) {
        this.temper = temper;
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
        BeeboxInfo other = (BeeboxInfo) that;
        return (this.getBbid() == null ? other.getBbid() == null : this.getBbid().equals(other.getBbid()))
            && (this.getRecvTime() == null ? other.getRecvTime() == null : this.getRecvTime().equals(other.getRecvTime()))
            && (this.getWeightFrameRate() == null ? other.getWeightFrameRate() == null : this.getWeightFrameRate().equals(other.getWeightFrameRate()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getTemper() == null ? other.getTemper() == null : this.getTemper().equals(other.getTemper()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBbid() == null) ? 0 : getBbid().hashCode());
        result = prime * result + ((getRecvTime() == null) ? 0 : getRecvTime().hashCode());
        result = prime * result + ((getWeightFrameRate() == null) ? 0 : getWeightFrameRate().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getTemper() == null) ? 0 : getTemper().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", weightFrameRate=").append(weightFrameRate);
        sb.append(", weight=").append(weight);
        sb.append(", temper=").append(temper);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}