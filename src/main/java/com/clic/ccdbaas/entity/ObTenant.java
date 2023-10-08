package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_ob_tenant")
public class ObTenant implements Serializable {
    private String id;

    private String regionId;

    private String resId;

    private List statusArr;

    private String paramValue;

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

    private String obTenantId;

    private String clusterId;

    private String name;

    private String clusterName;

    private String readOnly;

    private String locked;

    private String mode;

    private int minCpuCoreCount;
    private int maxCpuCoreCount;
    private Double maxMemorySize;
    private Double minMemorySize;

    private String obProxyAddress;
    private int obProxyPort;
    private String connectionString;

    private String linkUrl;


    private Integer tenantZoneNum;

    private String tenantCpu;
/*  private Double cpuNum;

    private Double memorySize;*/


    private String tenantMem;

    private String primaryZone;

    private Date updateTime;

    private Date createTime;

    private String status;

    private String description;

    private String  ocpId;

    private String ocpName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getObTenantId() {
        return obTenantId;
    }

    public void setObTenantId(String obTenantId) {
        this.obTenantId = obTenantId == null ? null : obTenantId.trim();
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId == null ? null : clusterId.trim();
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName == null ? null : clusterName.trim();
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly == null ? null : readOnly.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public Integer getTenantZoneNum() {
        return tenantZoneNum;
    }

    public void setTenantZoneNum(Integer tenantZoneNum) {
        this.tenantZoneNum = tenantZoneNum;
    }

    public String getTenantCpu() {
        return tenantCpu;
    }

    public void setTenantCpu(String tenantCpu) {
        this.tenantCpu = tenantCpu == null ? null : tenantCpu.trim();
    }

    public String getTenantMem() {
        return tenantMem;
    }

    public void setTenantMem(String tenantMem) {
        this.tenantMem = tenantMem == null ? null : tenantMem.trim();
    }

    public String getPrimaryZone() {
        return primaryZone;
    }

    public void setPrimaryZone(String primaryZone) {
        this.primaryZone = primaryZone == null ? null : primaryZone.trim();
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




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", regionId=").append(regionId);
        sb.append(", obTenantId=").append(obTenantId);
        sb.append(", clusterId=").append(clusterId);
        sb.append(", name=").append(name);
        sb.append(", clusterName=").append(clusterName);
        sb.append(", readOnly=").append(readOnly);
        sb.append(", locked=").append(locked);
        sb.append(", mode=").append(mode);
        sb.append(", tenantZoneNum=").append(tenantZoneNum);
        sb.append(", tenantCpu=").append(tenantCpu);
        sb.append(", tenantMem=").append(tenantMem);
        sb.append(", primaryZone=").append(primaryZone);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}