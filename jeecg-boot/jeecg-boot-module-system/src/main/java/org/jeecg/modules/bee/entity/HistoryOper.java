package org.jeecg.modules.bee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * ums_history_oper
 * @author 
 */
public class HistoryOper implements Serializable {
    /**
     * 标识
     */
    @JsonIgnore
    private Integer id;

    /**
     * 蜂箱id
     */
    private String bbid;

    /**
     * 用户id
     */
    @JsonIgnore
    private String uid;

    /**
     * 清洗时间
     */
    private Date cleanTime;

    /**
     * 清洗时长
     */
    private Integer cleanLong;

    /**
     * 清洗状态 (0清洗成功 1清洗失败 2清洗中)
     */
    private Integer cleanStatus;

    /**
     * 加热开始
     */
    private Date heatStarttime;

    /**
     * 加热结束时间
     */
    private Date heatEndtime;

    /**
     * 加热开始温度
     */
    private Double heatStartTemper;

    /**
     * 加热结束温度
     */
    private Double heatEndTemper;

    /**
     * 加热状态 (0加热成功 1加热失败 2加热中)
     */
    private Integer heatStatus;

    /**
     * 喂食时间
     */
    private Date feedTime;

    /**
     * 喂食状态 (0喂食成功 1喂食失败 2喂食中)
     */
    private Integer feedStatus;

    /**
     * 喂食类型(0自动 1手动)
     */
    private Integer feedType;

    /**
     * 喂食命令 0补充  1奖励
     */
    private Integer feedOrder;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBbid() {
        return bbid;
    }

    public void setBbid(String bbid) {
        this.bbid = bbid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(Date cleanTime) {
        this.cleanTime = cleanTime;
    }

    public Integer getCleanLong() {
        return cleanLong;
    }

    public void setCleanLong(Integer cleanLong) {
        this.cleanLong = cleanLong;
    }

    public Integer getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(Integer cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    public Date getHeatStarttime() {
        return heatStarttime;
    }

    public void setHeatStarttime(Date heatStarttime) {
        this.heatStarttime = heatStarttime;
    }

    public Date getHeatEndtime() {
        return heatEndtime;
    }

    public void setHeatEndtime(Date heatEndtime) {
        this.heatEndtime = heatEndtime;
    }

    public Double getHeatStartTemper() {
        return heatStartTemper;
    }

    public void setHeatStartTemper(Double heatStartTemper) {
        this.heatStartTemper = heatStartTemper;
    }

    public Double getHeatEndTemper() {
        return heatEndTemper;
    }

    public void setHeatEndTemper(Double heatEndTemper) {
        this.heatEndTemper = heatEndTemper;
    }

    public Integer getHeatStatus() {
        return heatStatus;
    }

    public void setHeatStatus(Integer heatStatus) {
        this.heatStatus = heatStatus;
    }

    public Date getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(Date feedTime) {
        this.feedTime = feedTime;
    }

    public Integer getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(Integer feedStatus) {
        this.feedStatus = feedStatus;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }

    public Integer getFeedOrder() {
        return feedOrder;
    }

    public void setFeedOrder(Integer feedOrder) {
        this.feedOrder = feedOrder;
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
        HistoryOper other = (HistoryOper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBbid() == null ? other.getBbid() == null : this.getBbid().equals(other.getBbid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getCleanTime() == null ? other.getCleanTime() == null : this.getCleanTime().equals(other.getCleanTime()))
            && (this.getCleanLong() == null ? other.getCleanLong() == null : this.getCleanLong().equals(other.getCleanLong()))
            && (this.getCleanStatus() == null ? other.getCleanStatus() == null : this.getCleanStatus().equals(other.getCleanStatus()))
            && (this.getHeatStarttime() == null ? other.getHeatStarttime() == null : this.getHeatStarttime().equals(other.getHeatStarttime()))
            && (this.getHeatEndtime() == null ? other.getHeatEndtime() == null : this.getHeatEndtime().equals(other.getHeatEndtime()))
            && (this.getHeatStartTemper() == null ? other.getHeatStartTemper() == null : this.getHeatStartTemper().equals(other.getHeatStartTemper()))
            && (this.getHeatEndTemper() == null ? other.getHeatEndTemper() == null : this.getHeatEndTemper().equals(other.getHeatEndTemper()))
            && (this.getHeatStatus() == null ? other.getHeatStatus() == null : this.getHeatStatus().equals(other.getHeatStatus()))
            && (this.getFeedTime() == null ? other.getFeedTime() == null : this.getFeedTime().equals(other.getFeedTime()))
            && (this.getFeedStatus() == null ? other.getFeedStatus() == null : this.getFeedStatus().equals(other.getFeedStatus()))
            && (this.getFeedType() == null ? other.getFeedType() == null : this.getFeedType().equals(other.getFeedType()))
            && (this.getFeedOrder() == null ? other.getFeedOrder() == null : this.getFeedOrder().equals(other.getFeedOrder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBbid() == null) ? 0 : getBbid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCleanTime() == null) ? 0 : getCleanTime().hashCode());
        result = prime * result + ((getCleanLong() == null) ? 0 : getCleanLong().hashCode());
        result = prime * result + ((getCleanStatus() == null) ? 0 : getCleanStatus().hashCode());
        result = prime * result + ((getHeatStarttime() == null) ? 0 : getHeatStarttime().hashCode());
        result = prime * result + ((getHeatEndtime() == null) ? 0 : getHeatEndtime().hashCode());
        result = prime * result + ((getHeatStartTemper() == null) ? 0 : getHeatStartTemper().hashCode());
        result = prime * result + ((getHeatEndTemper() == null) ? 0 : getHeatEndTemper().hashCode());
        result = prime * result + ((getHeatStatus() == null) ? 0 : getHeatStatus().hashCode());
        result = prime * result + ((getFeedTime() == null) ? 0 : getFeedTime().hashCode());
        result = prime * result + ((getFeedStatus() == null) ? 0 : getFeedStatus().hashCode());
        result = prime * result + ((getFeedType() == null) ? 0 : getFeedType().hashCode());
        result = prime * result + ((getFeedOrder() == null) ? 0 : getFeedOrder().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bbid=").append(bbid);
        sb.append(", uid=").append(uid);
        sb.append(", cleanTime=").append(cleanTime);
        sb.append(", cleanLong=").append(cleanLong);
        sb.append(", cleanStatus=").append(cleanStatus);
        sb.append(", heatStarttime=").append(heatStarttime);
        sb.append(", heatEndtime=").append(heatEndtime);
        sb.append(", heatStartTemper=").append(heatStartTemper);
        sb.append(", heatEndTemper=").append(heatEndTemper);
        sb.append(", heatStatus=").append(heatStatus);
        sb.append(", feedTime=").append(feedTime);
        sb.append(", feedStatus=").append(feedStatus);
        sb.append(", feedType=").append(feedType);
        sb.append(", feedOrder=").append(feedOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}