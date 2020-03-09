package org.jeecg.modules.bee.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ums_beebox_info
 * @author 
 */
public class BeeboxInfoKey implements Serializable {
    /**
     * 蜂箱id
     */
    private String bbid;

    /**
     * 接收时间
     */
    private Date recvTime;

    private static final long serialVersionUID = 1L;

    public String getBbid() {
        return bbid;
    }

    public void setBbid(String bbid) {
        this.bbid = bbid;
    }

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
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
        BeeboxInfoKey other = (BeeboxInfoKey) that;
        return (this.getBbid() == null ? other.getBbid() == null : this.getBbid().equals(other.getBbid()))
            && (this.getRecvTime() == null ? other.getRecvTime() == null : this.getRecvTime().equals(other.getRecvTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBbid() == null) ? 0 : getBbid().hashCode());
        result = prime * result + ((getRecvTime() == null) ? 0 : getRecvTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bbid=").append(bbid);
        sb.append(", recvTime=").append(recvTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}