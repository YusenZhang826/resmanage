package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("t_docker_env")
public class DockerEnv implements Serializable {
    private String resId;

    private String id;

    private String displayName;

    private String cloudName;

    private String rangeEnd;

    private String rangeStart;

    private Integer cpu;

    private String state;

    private String memory;

    private String delFlag;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public String getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(String rangeEnd) {
        this.rangeEnd = rangeEnd == null ? null : rangeEnd.trim();
    }

    public String getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(String rangeStart) {
        this.rangeStart = rangeStart == null ? null : rangeStart.trim();
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
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
        sb.append(", rangeEnd=").append(rangeEnd);
        sb.append(", rangeStart=").append(rangeStart);
        sb.append(", cpu=").append(cpu);
        sb.append(", state=").append(state);
        sb.append(", memory=").append(memory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}