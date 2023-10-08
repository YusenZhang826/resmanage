package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_file_store")
public class FileStore {
    private String resId;
    private String nativeId;
    private Date createAt;
    private String shareProto;
    private String ownerName;
    private String tenantName;
    private String sharePath;
    private Date last_Modified;
    private String resourcePoolName;
    private Integer size;
    private String name;
    private String vdcName;
    private String projectId;
    private String bizRegionName;
    private String resourcePoolType;
    private String status;
    private String ips;
    private Integer deployEnv;
    private Integer usedSize;

    public Integer getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(Integer deployEnv) {
        this.deployEnv = deployEnv;
    }

    public Integer getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(Integer usedSize) {
        this.usedSize = usedSize;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getShareProto() {
        return shareProto;
    }

    public void setShareProto(String shareProto) {
        this.shareProto = shareProto;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getSharePath() {
        return sharePath;
    }

    public void setSharePath(String sharePath) {
        this.sharePath = sharePath;
    }

    public Date getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(Date last_Modified) {
        this.last_Modified = last_Modified;
    }

    public String getResourcePoolName() {
        return resourcePoolName;
    }

    public void setResourcePoolName(String resourcePoolName) {
        this.resourcePoolName = resourcePoolName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVdcName() {
        return vdcName;
    }

    public void setVdcName(String vdcName) {
        this.vdcName = vdcName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBizRegionName() {
        return bizRegionName;
    }

    public void setBizRegionName(String bizRegionName) {
        this.bizRegionName = bizRegionName;
    }

    public String getResourcePoolType() {
        return resourcePoolType;
    }

    public void setResourcePoolType(String resourcePoolType) {
        this.resourcePoolType = resourcePoolType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
