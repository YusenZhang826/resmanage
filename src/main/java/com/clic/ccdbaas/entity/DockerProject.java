package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("t_docker_project")
public class DockerProject implements Serializable {
    private String resId;

    private String id;

    private String displayName;

    private String cloudName;

    private String environmentId;

    private String ownerStr;

    private String state;

    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName == null ? null : cloudName.trim();
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
        this.environmentId = environmentId == null ? null : environmentId.trim();
    }

    public String getOwnerStr() {
        return ownerStr;
    }

    public void setOwnerStr(String ownerStr) {
        this.ownerStr = ownerStr == null ? null : ownerStr.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", id=").append(id);
        sb.append(", displayName=").append(displayName);
        sb.append(", cloudName=").append(cloudName);
        sb.append(", environmentId=").append(environmentId);
        sb.append(", ownerStr=").append(ownerStr);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}