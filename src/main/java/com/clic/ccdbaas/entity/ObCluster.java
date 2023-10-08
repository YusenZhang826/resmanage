package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_ob_cluster")
public class ObCluster implements Serializable {
    private String id;

    private String resId;

    private String paramValue;

    private List statusArr;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    private String obClusterId;

    private String name;

    private String architecture;

    private String type;

    private String obVersion;

    private Integer serverCount;

    private String status;

    private Integer tenantCount;

    private Date updateTime;

    private Date createTime;

    private String creator;

    private String ocpId;

    private String ocpName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObClusterId() {
        return obClusterId;
    }

    public void setObClusterId(String obClusterId) {
        this.obClusterId = obClusterId == null ? null : obClusterId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture == null ? null : architecture.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getObVersion() {
        return obVersion;
    }

    public void setObVersion(String obVersion) {
        this.obVersion = obVersion == null ? null : obVersion.trim();
    }

    public Integer getServerCount() {
        return serverCount;
    }

    public void setServerCount(Integer serverCount) {
        this.serverCount = serverCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getTenantCount() {
        return tenantCount;
    }

    public void setTenantCount(Integer tenantCount) {
        this.tenantCount = tenantCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", obClusterId=").append(obClusterId);
        sb.append(", name=").append(name);
        sb.append(", architecture=").append(architecture);
        sb.append(", type=").append(type);
        sb.append(", obVersion=").append(obVersion);
        sb.append(", serverCount=").append(serverCount);
        sb.append(", status=").append(status);
        sb.append(", tenantCount=").append(tenantCount);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}