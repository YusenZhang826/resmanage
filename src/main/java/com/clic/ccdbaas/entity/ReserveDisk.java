package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.model.BaseEntity;

import java.util.Date;

@TableName(value = "t_reserve_disk")
public class ReserveDisk extends BaseEntity {
    @TableId
    private String resId;

    private Integer diskSize;

    private String remarks;

    private String uses;

    private String interfaceClass;

    private String mediaClass;

    private String serverId;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public Integer getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Integer diskSize) {
        this.diskSize = diskSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses == null ? null : uses.trim();
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass == null ? null : interfaceClass.trim();
    }

    public String getMediaClass() {
        return mediaClass;
    }

    public void setMediaClass(String mediaClass) {
        this.mediaClass = mediaClass == null ? null : mediaClass.trim();
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", diskSize=").append(diskSize);
        sb.append(", remarks=").append(remarks);
        sb.append(", uses=").append(uses);
        sb.append(", interfaceClass=").append(interfaceClass);
        sb.append(", mediaClass=").append(mediaClass);
        sb.append(", serverId=").append(serverId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}