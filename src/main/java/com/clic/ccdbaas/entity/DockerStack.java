package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("t_docker_stack")
public class DockerStack implements Serializable {
    private String resId;

    private String id;

    private String name;

    private String cloudName;

    private String description;

    private Integer serviceCount;

    private String state;

    private String delFlag;

    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName == null ? null : cloudName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
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
        sb.append(", name=").append(name);
        sb.append(", cloudName=").append(cloudName);
        sb.append(", description=").append(description);
        sb.append(", serviceCount=").append(serviceCount);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}