package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * time:2023/2/21 14:25
 * description:
 */

@Data
@NoArgsConstructor
@TableName("t_physicalserver")
public class PhysicalServer {

    @ExcelProperty("资源ID")
    @Excel(name = "资源ID")
    public String resId;
    @ExcelProperty("主IP")
    @Excel(name = "主IP")
    public String mainIp;
    @ExcelProperty("关联IP")
    @Excel(name = "关联IP")
    public String relateIp;
    @ExcelProperty("虚IP")
    @Excel(name = "虚IP")
    public String virtualIp;
    @ExcelProperty("网络区域")
    @Excel(name = "网络区域")
    public String networkArea;
    @ExcelProperty("主机名")
    @Excel(name = "主机名")
    public String name;
    @ExcelProperty("部署环境")
    @Excel(name = "部署环境")
    public String deployEnv;
    @ExcelProperty("用途描述")
    @Excel(name = "用途描述")
    public String usageDes;
    @ExcelProperty("类名")
    @Excel(name = "类名")
    public String class_Name;
    @ExcelProperty("操作系统")
    @Excel(name = "操作系统")
    public String deployOs;
    @ExcelProperty("资源分配代码")
    @Excel(name = "资源分配代码")
    public String resourceCode;
    @ExcelProperty("CPU(C)")
    @Excel(name = "CPU(C)")
    public String cpuCores;
    @ExcelProperty("Memory(G)")
    @Excel(name = "Memory(G)")
    public String memoryCapacity;
    @ExcelProperty("分配存储(G)")
    @Excel(name = "分配存储(G)")
    public String diskCapacity;
    @ExcelProperty("资源状态")
    @Excel(name = "资源状态", readConverterExp = "active=运行")
    public String status;
    @ExcelProperty("归属系统")
    @Excel(name = "归属系统")
    public String belongProduct;
    @ExcelProperty("产品属主单位")
    @Excel(name = "产品属主单位")
    public String belongCompany;
    @ExcelProperty("主机运行管理员")
    @Excel(name = "主机运行管理员")
    public String sysAdministrator;
    @ExcelProperty("主机管理员B")
    @Excel(name = "主机管理员B")
    public String sysAdminBackup;
    @ExcelProperty("主机干系人")
    @Excel(name = "主机干系人")
    public String stakeholder;
    @ExcelProperty("ping状态")
    @Excel(name = "ping状态")
    public String pingStatus;
    @ExcelProperty("hids状态")
    @Excel(name = "hids状态")
    public String hidsStatus;
    @ExcelProperty("zabbix状态")
    @Excel(name = "zabbix状态")
    public String zabbixStatus;
    @ExcelProperty("Os管理员用户")
    @Excel(name = "Os管理员用户")
    public String osAdminUser;
    @ExcelProperty("Os普通用户")
    @Excel(name = "Os普通用户")
    public String osNormalUser;
    @ExcelProperty("cpu架构")
    @Excel(name = "cpu架构")
    public String cpuArch;
    @ExcelProperty("记录ID")
    @Excel(name = "记录ID")
    public String nativeId;
    @ExcelProperty("主机运行团队")
    @Excel(name = "主机运行团队")
    public String sysAdminTeam;
    @ExcelProperty("最近修改时间")
    @Excel(name = "最近修改时间")
    public String last_Modified;
    @ExcelProperty("产品Token")
    @Excel(name = "产品Token")
    private String productToken;
    @ExcelProperty("物理宿主机")
    @Excel(name = "物理宿主机")
    private String locationCode;
    @ExcelProperty("宿主机带外IP")
    @Excel(name = "宿主机带外IP")
    private String mgmtIp;
    @ExcelProperty("sn")
    @Excel(name = "sn")
    private String sn;


    //    private List deployEnvArr;
    @ExcelProperty("地址")
    @Excel(name = "地址")
    private String ipAddress;
    @ExcelProperty("资源状态")
    @Excel(name = "资源状态")
    private String assetStatus;
    @ExcelProperty("应用Token")
    @Excel(name = "应用Token")
    private String appModToken;
    @ExcelProperty("应用名称")
    @Excel(name = "应用名称")
    private String appModName;
    @ExcelProperty("是否虚机")
    @Excel(name = "是否虚机")
    private String isPhysicalServer;
    @ExcelProperty("主机应用管理员")
    @Excel(name = "主机应用管理员")
    private String deployAdminA;
    @ExcelProperty("系统标识")
    @Excel(name = "系统标识")
    private String systemIndex;

    public String getIsPhysicalServer() {
        return isPhysicalServer;
    }

    public void setIsPhysicalServer(String isPhysicalServer) {
        this.isPhysicalServer = isPhysicalServer;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam;
    }

    public String getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(String last_Modified) {
        this.last_Modified = last_Modified;
    }


    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    public String getRelateIp() {
        return relateIp;
    }

    public void setRelateIp(String relateIp) {
        this.relateIp = relateIp;
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp;
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public String getUsageDes() {
        return usageDes;
    }

    public void setUsageDes(String usageDes) {
        this.usageDes = usageDes;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

    public String getDeployOs() {
        return deployOs;
    }

    public void setDeployOs(String deployOs) {
        this.deployOs = deployOs;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator;
    }

    public String getSysAdminBackup() {
        return sysAdminBackup;
    }

    public void setSysAdminBackup(String sysAdminBackup) {
        this.sysAdminBackup = sysAdminBackup;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public String getHidsStatus() {
        return hidsStatus;
    }

    public void setHidsStatus(String hidsStatus) {
        this.hidsStatus = hidsStatus;
    }

    public String getZabbixStatus() {
        return zabbixStatus;
    }

    public void setZabbixStatus(String zabbixStatus) {
        this.zabbixStatus = zabbixStatus;
    }

    public String getOsAdminUser() {
        return osAdminUser;
    }

    public void setOsAdminUser(String osAdminUser) {
        this.osAdminUser = osAdminUser;
    }

    public String getOsNormalUser() {
        return osNormalUser;
    }

    public void setOsNormalUser(String osNormalUser) {
        this.osNormalUser = osNormalUser;
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getMgmtIp() {
        return mgmtIp;
    }

    public void setMgmtIp(String mgmtIp) {
        this.mgmtIp = mgmtIp;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getAppModToken() {
        return appModToken;
    }

    public void setAppModToken(String appModToken) {
        this.appModToken = appModToken;
    }

    public String getAppModName() {
        return appModName;
    }

    public void setAppModName(String appModName) {
        this.appModName = appModName;
    }
}
