package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@ExcelIgnoreUnannotated
@TableName(value = "t_cloud_bms")
public class CloudBms implements Serializable {
    private String resId;

    private String mainIp;

    private String relateIp;

    private String virtualIp;

    private String networkArea;

    private String name;

    private String deployEnv;

    private String usageDes;

    private String class_Name;

    private String deployOs;

    private String resourceCode;

    private String cpuCores;

    private String memoryCapacity;

    private String diskCapacity;

    private String boardType;

    private String status;

    private String belongProduct;

    private String belongCompany;

    private String sysAdministrator;

    private String sysAdminBackup;

    private String stakeholder;

    private String pingStatus;

    private String hidsStatus;

    private String zabbixStatus;

    private String cpuArch;

    private String nativeId;

    private String sysAdminTeam;

    private String last_Modified;

    private String productToken;

    private String locationCode;

    private String bmcIp;

    private String sn;

    private String assetStatus;

    private String bizRegionId;

    private String bizregionName;

    private String azoneId;

    private String azoneName;

    private String ownerId;

    private String ownerName;

    private String ownerType;

    private String regionId;

    private String regionName;

    private String resourcePoolId;

    private String resourcePoolName;

    private String resourcePoolType;

    private String podId;

    private String podName;

    private String vmId;

    private String physicalServerId;

    private String bmsgwClusterId;

    private String instanceUuid;

    private String deployAdminA;

    private String deployAdminB;

    private Integer fourA;

    private Integer antivirusSoftware;

    private String osAdminUser;

    private String osNormalUser;

    private String properties;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp == null ? null : mainIp.trim();
    }

    public String getRelateIp() {
        return relateIp;
    }

    public void setRelateIp(String relateIp) {
        this.relateIp = relateIp == null ? null : relateIp.trim();
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp == null ? null : virtualIp.trim();
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea == null ? null : networkArea.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv == null ? null : deployEnv.trim();
    }

    public String getUsageDes() {
        return usageDes;
    }

    public void setUsageDes(String usageDes) {
        this.usageDes = usageDes == null ? null : usageDes.trim();
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name == null ? null : class_Name.trim();
    }

    public String getDeployOs() {
        return deployOs;
    }

    public void setDeployOs(String deployOs) {
        this.deployOs = deployOs == null ? null : deployOs.trim();
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public String getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(String cpuCores) {
        this.cpuCores = cpuCores == null ? null : cpuCores.trim();
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity == null ? null : memoryCapacity.trim();
    }

    public String getDiskCapacity() {
        return diskCapacity;
    }

    public void setDiskCapacity(String diskCapacity) {
        this.diskCapacity = diskCapacity == null ? null : diskCapacity.trim();
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType == null ? null : boardType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct == null ? null : belongProduct.trim();
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany == null ? null : belongCompany.trim();
    }

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator == null ? null : sysAdministrator.trim();
    }

    public String getSysAdminBackup() {
        return sysAdminBackup;
    }

    public void setSysAdminBackup(String sysAdminBackup) {
        this.sysAdminBackup = sysAdminBackup == null ? null : sysAdminBackup.trim();
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder == null ? null : stakeholder.trim();
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus == null ? null : pingStatus.trim();
    }

    public String getHidsStatus() {
        return hidsStatus;
    }

    public void setHidsStatus(String hidsStatus) {
        this.hidsStatus = hidsStatus == null ? null : hidsStatus.trim();
    }

    public String getZabbixStatus() {
        return zabbixStatus;
    }

    public void setZabbixStatus(String zabbixStatus) {
        this.zabbixStatus = zabbixStatus == null ? null : zabbixStatus.trim();
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch == null ? null : cpuArch.trim();
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId == null ? null : nativeId.trim();
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam == null ? null : sysAdminTeam.trim();
    }

    public String getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(String last_Modified) {
        this.last_Modified = last_Modified == null ? null : last_Modified.trim();
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken == null ? null : productToken.trim();
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public String getBmcIp() {
        return bmcIp;
    }

    public void setBmcIp(String bmcIp) {
        this.bmcIp = bmcIp == null ? null : bmcIp.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus == null ? null : assetStatus.trim();
    }

    public String getBizRegionId() {
        return bizRegionId;
    }

    public void setBizRegionId(String bizRegionId) {
        this.bizRegionId = bizRegionId == null ? null : bizRegionId.trim();
    }

    public String getBizregionName() {
        return bizregionName;
    }

    public void setBizregionName(String bizregionName) {
        this.bizregionName = bizregionName == null ? null : bizregionName.trim();
    }

    public String getAzoneId() {
        return azoneId;
    }

    public void setAzoneId(String azoneId) {
        this.azoneId = azoneId == null ? null : azoneId.trim();
    }

    public String getAzoneName() {
        return azoneName;
    }

    public void setAzoneName(String azoneName) {
        this.azoneName = azoneName == null ? null : azoneName.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getResourcePoolId() {
        return resourcePoolId;
    }

    public void setResourcePoolId(String resourcePoolId) {
        this.resourcePoolId = resourcePoolId == null ? null : resourcePoolId.trim();
    }

    public String getResourcePoolName() {
        return resourcePoolName;
    }

    public void setResourcePoolName(String resourcePoolName) {
        this.resourcePoolName = resourcePoolName == null ? null : resourcePoolName.trim();
    }

    public String getResourcePoolType() {
        return resourcePoolType;
    }

    public void setResourcePoolType(String resourcePoolType) {
        this.resourcePoolType = resourcePoolType == null ? null : resourcePoolType.trim();
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId == null ? null : podId.trim();
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName == null ? null : podName.trim();
    }

    public String getVmId() {
        return vmId;
    }

    public void setVmId(String vmId) {
        this.vmId = vmId == null ? null : vmId.trim();
    }

    public String getPhysicalServerId() {
        return physicalServerId;
    }

    public void setPhysicalServerId(String physicalServerId) {
        this.physicalServerId = physicalServerId == null ? null : physicalServerId.trim();
    }

    public String getBmsgwClusterId() {
        return bmsgwClusterId;
    }

    public void setBmsgwClusterId(String bmsgwClusterId) {
        this.bmsgwClusterId = bmsgwClusterId == null ? null : bmsgwClusterId.trim();
    }

    public String getInstanceUuid() {
        return instanceUuid;
    }

    public void setInstanceUuid(String instanceUuid) {
        this.instanceUuid = instanceUuid == null ? null : instanceUuid.trim();
    }

    public String getDeployAdminA() {
        return deployAdminA;
    }

    public void setDeployAdminA(String deployAdminA) {
        this.deployAdminA = deployAdminA == null ? null : deployAdminA.trim();
    }

    public String getDeployAdminB() {
        return deployAdminB;
    }

    public void setDeployAdminB(String deployAdminB) {
        this.deployAdminB = deployAdminB == null ? null : deployAdminB.trim();
    }

    public Integer getFourA() {
        return fourA;
    }

    public void setFourA(Integer fourA) {
        this.fourA = fourA;
    }

    public Integer getAntivirusSoftware() {
        return antivirusSoftware;
    }

    public void setAntivirusSoftware(Integer antivirusSoftware) {
        this.antivirusSoftware = antivirusSoftware;
    }

    public String getOsAdminUser() {
        return osAdminUser;
    }

    public void setOsAdminUser(String osAdminUser) {
        this.osAdminUser = osAdminUser == null ? null : osAdminUser.trim();
    }

    public String getOsNormalUser() {
        return osNormalUser;
    }

    public void setOsNormalUser(String osNormalUser) {
        this.osNormalUser = osNormalUser == null ? null : osNormalUser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", mainIp=").append(mainIp);
        sb.append(", relateIp=").append(relateIp);
        sb.append(", virtualIp=").append(virtualIp);
        sb.append(", networkArea=").append(networkArea);
        sb.append(", name=").append(name);
        sb.append(", deployEnv=").append(deployEnv);
        sb.append(", usageDes=").append(usageDes);
        sb.append(", class_Name=").append(class_Name);
        sb.append(", deployOs=").append(deployOs);
        sb.append(", resourceCode=").append(resourceCode);
        sb.append(", cpuCores=").append(cpuCores);
        sb.append(", memoryCapacity=").append(memoryCapacity);
        sb.append(", diskCapacity=").append(diskCapacity);
        sb.append(", boardType=").append(boardType);
        sb.append(", status=").append(status);
        sb.append(", belongProduct=").append(belongProduct);
        sb.append(", belongCompany=").append(belongCompany);
        sb.append(", sysAdministrator=").append(sysAdministrator);
        sb.append(", sysAdminBackup=").append(sysAdminBackup);
        sb.append(", stakeholder=").append(stakeholder);
        sb.append(", pingStatus=").append(pingStatus);
        sb.append(", hidsStatus=").append(hidsStatus);
        sb.append(", zabbixStatus=").append(zabbixStatus);
        sb.append(", cpuArch=").append(cpuArch);
        sb.append(", nativeId=").append(nativeId);
        sb.append(", sysAdminTeam=").append(sysAdminTeam);
        sb.append(", last_Modified=").append(last_Modified);
        sb.append(", productToken=").append(productToken);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", bmcIp=").append(bmcIp);
        sb.append(", sn=").append(sn);
        sb.append(", assetStatus=").append(assetStatus);
        sb.append(", bizRegionId=").append(bizRegionId);
        sb.append(", bizregionName=").append(bizregionName);
        sb.append(", azoneId=").append(azoneId);
        sb.append(", azoneName=").append(azoneName);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", ownerType=").append(ownerType);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionName=").append(regionName);
        sb.append(", resourcePoolId=").append(resourcePoolId);
        sb.append(", resourcePoolName=").append(resourcePoolName);
        sb.append(", resourcePoolType=").append(resourcePoolType);
        sb.append(", podId=").append(podId);
        sb.append(", podName=").append(podName);
        sb.append(", vmId=").append(vmId);
        sb.append(", physicalServerId=").append(physicalServerId);
        sb.append(", bmsgwClusterId=").append(bmsgwClusterId);
        sb.append(", instanceUuid=").append(instanceUuid);
        sb.append(", deployAdminA=").append(deployAdminA);
        sb.append(", deployAdminB=").append(deployAdminB);
        sb.append(", fourA=").append(fourA);
        sb.append(", antivirusSoftware=").append(antivirusSoftware);
        sb.append(", osAdminUser=").append(osAdminUser);
        sb.append(", osNormalUser=").append(osNormalUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}