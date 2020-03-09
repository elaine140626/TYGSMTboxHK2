package org.jeecg.modules.bee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ums_user_beebox
 * @author 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBeebox implements Serializable {
    /**
     * 蜂箱编号
     */
    private String bbid;

    /**
     * 用户编号
     */
    private String uid;

    /**
     * 蜂箱状态 (0正常 绿色   1中等 黄色   2 低重量 红色)
     */
    private Integer boxStatus;

    /**
     * 蜂王状态
     */
    private String kingStatus;

    /**
     * 框数
     */
    private Integer frameNum;

    /**
     * 是否联网(0联网  1断网)
     */
    private Integer netStatus;

    /**
     * 巢门状态( 0清洗 1控王  2正常 3通风)
     */
    private Integer doorStatus;

    /**
     * 自动喂食 (0关闭 1开启)
     */
    private Integer autoFeed;

    /**
     * 自动加热 (0关闭 1开启)
     */
    private Integer autoHeat;

    /**
     * 0 正常 1 喂食中
     */
    private Integer feeding;

    /**
     * 0 正常 1 清洗中
     */
    private Integer cleaning;

    /**
     * 0 正常 1 加热中
     */
    private Integer heating;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

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

    public Integer getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Integer boxStatus) {
        this.boxStatus = boxStatus;
    }

    public String getKingStatus() {
        return kingStatus;
    }

    public void setKingStatus(String kingStatus) {
        this.kingStatus = kingStatus;
    }

    public Integer getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(Integer frameNum) {
        this.frameNum = frameNum;
    }

    public Integer getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(Integer netStatus) {
        this.netStatus = netStatus;
    }

    public Integer getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(Integer doorStatus) {
        this.doorStatus = doorStatus;
    }

    public Integer getAutoFeed() {
        return autoFeed;
    }

    public void setAutoFeed(Integer autoFeed) {
        this.autoFeed = autoFeed;
    }

    public Integer getAutoHeat() {
        return autoHeat;
    }

    public void setAutoHeat(Integer autoHeat) {
        this.autoHeat = autoHeat;
    }

    public Integer getFeeding() {
        return feeding;
    }

    public void setFeeding(Integer feeding) {
        this.feeding = feeding;
    }

    public Integer getCleaning() {
        return cleaning;
    }

    public void setCleaning(Integer cleaning) {
        this.cleaning = cleaning;
    }

    public Integer getHeating() {
        return heating;
    }

    public void setHeating(Integer heating) {
        this.heating = heating;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        UserBeebox other = (UserBeebox) that;
        return (this.getBbid() == null ? other.getBbid() == null : this.getBbid().equals(other.getBbid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getBoxStatus() == null ? other.getBoxStatus() == null : this.getBoxStatus().equals(other.getBoxStatus()))
            && (this.getKingStatus() == null ? other.getKingStatus() == null : this.getKingStatus().equals(other.getKingStatus()))
            && (this.getFrameNum() == null ? other.getFrameNum() == null : this.getFrameNum().equals(other.getFrameNum()))
            && (this.getNetStatus() == null ? other.getNetStatus() == null : this.getNetStatus().equals(other.getNetStatus()))
            && (this.getDoorStatus() == null ? other.getDoorStatus() == null : this.getDoorStatus().equals(other.getDoorStatus()))
            && (this.getAutoFeed() == null ? other.getAutoFeed() == null : this.getAutoFeed().equals(other.getAutoFeed()))
            && (this.getAutoHeat() == null ? other.getAutoHeat() == null : this.getAutoHeat().equals(other.getAutoHeat()))
            && (this.getFeeding() == null ? other.getFeeding() == null : this.getFeeding().equals(other.getFeeding()))
            && (this.getCleaning() == null ? other.getCleaning() == null : this.getCleaning().equals(other.getCleaning()))
            && (this.getHeating() == null ? other.getHeating() == null : this.getHeating().equals(other.getHeating()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBbid() == null) ? 0 : getBbid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getBoxStatus() == null) ? 0 : getBoxStatus().hashCode());
        result = prime * result + ((getKingStatus() == null) ? 0 : getKingStatus().hashCode());
        result = prime * result + ((getFrameNum() == null) ? 0 : getFrameNum().hashCode());
        result = prime * result + ((getNetStatus() == null) ? 0 : getNetStatus().hashCode());
        result = prime * result + ((getDoorStatus() == null) ? 0 : getDoorStatus().hashCode());
        result = prime * result + ((getAutoFeed() == null) ? 0 : getAutoFeed().hashCode());
        result = prime * result + ((getAutoHeat() == null) ? 0 : getAutoHeat().hashCode());
        result = prime * result + ((getFeeding() == null) ? 0 : getFeeding().hashCode());
        result = prime * result + ((getCleaning() == null) ? 0 : getCleaning().hashCode());
        result = prime * result + ((getHeating() == null) ? 0 : getHeating().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bbid=").append(bbid);
        sb.append(", uid=").append(uid);
        sb.append(", boxStatus=").append(boxStatus);
        sb.append(", kingStatus=").append(kingStatus);
        sb.append(", frameNum=").append(frameNum);
        sb.append(", netStatus=").append(netStatus);
        sb.append(", doorStatus=").append(doorStatus);
        sb.append(", autoFeed=").append(autoFeed);
        sb.append(", autoHeat=").append(autoHeat);
        sb.append(", feeding=").append(feeding);
        sb.append(", cleaning=").append(cleaning);
        sb.append(", heating=").append(heating);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}