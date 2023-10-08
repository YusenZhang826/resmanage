package com.clic.ccdbaas.entity.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResourceRecycling {
    private String resId;
    private String workOrder;
    private String name;
    private String mainIp;
    private String class_Name;
    private String cpuCores;
    private String memoryCapacity;
    private String diskCapacity;
    private String belongProduct;
    private String belongCompany;
    private String sysAdminTeam;
    private String sysAdministrator;
    private String deployOs;
    private String deployEnv;
    private String virtualIp;
    private String relatedIp;
    private String cpuArch;
    private String resourceCode;
    private String usageDes;
    private String networkArea;
    private Date last_Modified;
    private String sn;
    private String locationCode;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

    public String getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(String cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getDiskCapacity() {
        return diskCapacity;
    }

    public void setDiskCapacity(String diskCapacity) {
        this.diskCapacity = diskCapacity;
    }

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct;
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam;
    }

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator;
    }

    public String getDeployOs() {
        return deployOs;
    }

    public void setDeployOs(String deployOs) {
        this.deployOs = deployOs;
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp;
    }

    public String getRelatedIp() {
        return relatedIp;
    }

    public void setRelatedIp(String relatedIp) {
        this.relatedIp = relatedIp;
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getUsageDes() {
        return usageDes;
    }

    public void setUsageDes(String usageDes) {
        this.usageDes = usageDes;
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea;
    }

    public Date getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(Date last_Modified) {
        this.last_Modified = last_Modified;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
