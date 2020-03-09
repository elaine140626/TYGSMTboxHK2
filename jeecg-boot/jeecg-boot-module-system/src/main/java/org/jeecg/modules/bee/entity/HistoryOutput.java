package org.jeecg.modules.bee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * ums_history_output
 * @author 
 */
public class HistoryOutput implements Serializable {
    /**
     * 标识 
     */
    @JsonIgnore
    private Integer id;

    /**
     * 蜂箱号
     */
    private String bbid;

    /**
     * 产出框数
     */
    private Integer frame;

    /**
     * 产出蜂蜜
     */
    private Double honey;

    /**
     * 产出时间
     */
    private Date time;

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

    public Integer getFrame() {
        return frame;
    }

    public void setFrame(Integer frame) {
        this.frame = frame;
    }

    public Double getHoney() {
        return honey;
    }

    public void setHoney(Double honey) {
        this.honey = honey;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        HistoryOutput other = (HistoryOutput) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBbid() == null ? other.getBbid() == null : this.getBbid().equals(other.getBbid()))
            && (this.getFrame() == null ? other.getFrame() == null : this.getFrame().equals(other.getFrame()))
            && (this.getHoney() == null ? other.getHoney() == null : this.getHoney().equals(other.getHoney()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBbid() == null) ? 0 : getBbid().hashCode());
        result = prime * result + ((getFrame() == null) ? 0 : getFrame().hashCode());
        result = prime * result + ((getHoney() == null) ? 0 : getHoney().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
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
        sb.append(", frame=").append(frame);
        sb.append(", honey=").append(honey);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}