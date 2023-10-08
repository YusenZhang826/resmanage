package com.clic.ccdbaas.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.Annotation.Alias;
import com.clic.ccdbaas.Annotation.EnumNote;
import com.clic.ccdbaas.model.BaseEntity;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_cloudvm")
@Table(name = "t_cloudvm")
public class CloudVmare extends BaseEntity {
    //   @Excel( name = "ID")
    @Alias(columnName = "name", alias = "名称")
    @Excel(name = "名称")
    private String name;
    @Alias(columnName = "mainIp", alias = "主机IP")
    @Excel(name = "ip地址")
    private String mainIp;
    @Alias(columnName = "relatedIp", alias = "相关IP")
    @Excel(name = "相关ip")
    private String relateIp;
    @Alias(columnName = "virtualIp", alias = "虚IP")
    @Excel(name = "虚ip")
    private String virtualIp;
    private String id;
    @Excel(name = "资源类型", readConverterExp = "CLOUD_VM_NOVA=弹性云服务器,SYS_X86Server=物理服务器,SYS_PhysicalHost=宿主机")
    @EnumNote(code = {"CLOUD_VM_NOVA", "SYS_X86Server", "SYS_PhysicalHost"}, value = {"弹性云服务器", "物理机", "宿主机"})
    private String className;
    @Alias(columnName = "last_Modified", alias = "最后修改时间", businessType = "DateTime")
    @Excel(name = "最近修改时间")
//    @DateTimeFormat("yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String last_Modified;
    @Alias(columnName = "nativeId", alias = "原ID")
    @Excel(name = "原ID")
    private String nativeId;
    @Alias(columnName = "resId", alias = "资源ID")
    @Excel(name = "资源ID")
    private String resId;
    private String regionName;
    @Alias(columnName = "usageDes", alias = "用途描述")
    @Excel(name = "用途描述")
    private String usageDes;
    @Alias(columnName = "productToken", alias = "产品Token")
    private String productToken;
    @Alias(columnName = "sysAdministrator", alias = "主机管理员A")
    @Excel(name = "主机管理员A")
    private String sysAdministrator;
    @Alias(columnName = "sysAdminBackup", alias = "主机管理员B")
    @Excel(name = "主机管理员B")
    private String sysAdminBackup;
    @Alias(columnName = "stakeholder", alias = "干系人")
    @Excel(name = "干系人")
    private String stakeholder;
    @Alias(columnName = "deployOs", alias = "操作系统")
    @Excel(name = "部署操作系统")
    private String deployOs;
    private String ipAddress;
    @Alias(columnName = "sysAdminTeam", alias = "主机运行团队")
    @Excel(name = "主机运行团队")
    private String sysAdminTeam;
    @Alias(columnName = "belongProduct", alias = "归属产品")
    @Excel(name = "归属产品")
    private String belongProduct;
    /*   @Excel( name = "归属产品")
       private String belongCompany;*/
    @Alias(columnName = "deployEnv", alias = "部署环境", businessType = "enum")
    @EnumNote(code = {"0", "1"}, value = {"生产", "测试"})
    @Excel(name = "部署环境", readConverterExp = "0=生产,1=测试")
    private String deployEnv;
    private String memorySize;
    @Alias(columnName = "hidsStatus", alias = "Hids状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2", "3", "4"}, value = {"在线", "离线", "停用", "未接入", "白名单"})
    @Excel(name = "hids状态")
    private String hidsStatus;
    @Alias(columnName = "zabbixStatus", alias = "Zabbix状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2", "3"}, value = {"在线", "离线", "未接入", "白名单"})
    @Excel(name = "zabbix状态")
    private String zabbixStatus;
    @Alias(columnName = "pingStatus", alias = "Ping状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2"}, value = {"在线", "离线", "白名单"})
    @Excel(name = "ping状态")
    private String pingStatus;
    @Excel(name = "位置编码")
    private String locationCode;
    @ExcelProperty("分配BMC IP")
    @Excel(name = "分配BMC IP")
    private String bmcIp;
    @Excel(name = "产品属主单位")
    private String belongCompany;
    @Alias(columnName = "memorySize", alias = "内存（MB）")
    @Excel(name = "内存大小")
    private String memoryCapacity;
    @Alias(columnName = "cpuCoreNum", alias = "VCPU（个）")
    @Excel(name = "CPU")
    private String cpuCoreNum;
    @Alias(columnName = "cpuArch", alias = "CPU架构")
    @Excel(name = "cpu架构")
    private String cpuArch;
    @Alias(columnName = "networkArea", alias = "网络区域")
    @Excel(name = "网络逻辑区域")
    private String networkArea;
    @Alias(columnName = "azoneId", alias = "分区ID")
    private String azoneId;
    @Alias(columnName = "status", alias = "状态", businessType = "enum")
    @EnumNote(code = {"deleted", "active", "error", "fault_resuming", "offline", "recycling", "removed", "starting", "stopping", "shutting_down", "unknown", "verify_resize", "other", "storage_migrating", "hibernated", "hibernating", "live_volume_migrating_fail", "live_volume_migrating", "migrating", "building", "create_failed", "create_success", "reboot", "resize", "booting", "initial", "shutdowning"},
            value = {"已删除", "运行中", "故障", "恢复中", "离线", "回收中", "软删除", "启动中", "停止中", "删除中", "未知", "更新规格校验中", "其他", "磁盘迁移中", "已休眠", "休眠中", "跨存储热迁移失败", "跨存储热迁移中", "迁移中", "创建中", "创建失败", "创建成功", "重启中", "修改中", "上电中", "初始化", "下电中"})
    @Excel(name = "状态", readConverterExp = "active=运行中,deleted=已删除,error=故障,fault_resuming=故障恢复中,offline=离线（关机）,recycling=回收中,removed=软删除,starting=启动中,stopping=停止中,shutting_down=删除中,unknown=未知,verify_resize=更新规格校验中,other=其他,storage_migrating=磁盘迁移中,hibernated=已休眠,live_volume_migrating_fail=跨存储热迁移失败,live_volume_migrating=跨存储热迁移中,migrating=迁移中,building=创建中,create_failed=创建失败,create_success=创建成功,reboot=重启中,resize=修改中,booting=上电中,initial=初始化,shutdowning=下电中")
    private String status;
    @Alias(columnName = "azoneName", alias = "分区名称")
    @Excel(name = "分区名称")
    private String azoneName;
    private String physicalHostId;
    @Alias(columnName = "osAdminUser", alias = "OS管理员")
    @Excel(name = "Os管理员用户")
    private String osAdminUser;
    @Alias(columnName = "osNormalUser", alias = "OS普通用户")
    @Excel(name = "Os普通用户")
    private String osNormalUser;
    @Alias(columnName = "resourceCode", alias = "资源分配代码")
    private String resourceCode;
    @Alias(columnName = "clusterName", alias = "集群名称")
    private String clusterName;
    @Alias(columnName = "diskCapacity", alias = "硬盘（GB）")
    @Excel(name = "磁盘大小")
    private String diskCapacity;

    private String ifVdesk;
    @Alias(columnName = "bizRegionName", alias = "region名称")
    @Excel(name = "region名称")
    private String bizRegionName;
    @Alias(columnName = "projectName", alias = "项目名称")
    @Excel(name = "项目名称")
    private String projectName;
    @Alias(columnName = "createUser", alias = "创建者")
    @Excel(name = "创建人")
    private String createUser;

    private List resIdArr;

    private String ownerType;
    private String tenantType;
    private String extraSpecs;
    private String ownerId;
    private String deviceName;
    private String createdAt;
    private String diskSize;
    private String powerState;
    private String appId;
    private String confirmStatus;
    private String bizRegionId;
    private String class_Id;
    private String originalState;
    private String hostId;
    private String hypervisorType;
    private String tags;
    private String is_Local;
    private String class_Name;
    private String resourcePoolId;
    private String isVirtual;
    private String projectId;
    private String resourcePoolType;
    private String logicalRegionName;
    private String flavorId;
    private String clusterId;
    private String dataPlane;
    private String ownerName;
    private String tenantName;
    private String vdcId;
    private String resourcePoolName;
    private String keystoneId;
    private String logicalRegionId;
    private String regionId;
    private Date createTime;
    private String tenantId;
    private String vdcName;
    private String launchedAt;
    private String relatedIp;
    private String osVersion;
    private String appModToken;
    private String appModName;
    private String imageId;
    private String azoneInfo;
    private String userId;

    public CloudVmare(String mainIp, String resId) {
        this.mainIp = mainIp;
        this.resId = resId;
    }

    public CloudVmare() {

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

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea;
    }

    public String getPhysicalHostId() {
        return physicalHostId;
    }

    public void setPhysicalHostId(String physicalHostId) {
        this.physicalHostId = physicalHostId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIfVdesk() {
        return ifVdesk;
    }

    public void setIfVdesk(String ifVdesk) {
        this.ifVdesk = ifVdesk;
    }

    public String getBizRegionName() {
        return bizRegionName;
    }

    public void setBizRegionName(String bizRegionName) {
        this.bizRegionName = bizRegionName;
    }

    public String getDiskCapacity() {
        return diskCapacity;
    }

    public void setDiskCapacity(String diskCapacity) {
        this.diskCapacity = diskCapacity;
    }

    private String paramValue;
    private List statusArr;

    private List deployEnvArr;

    private List bizRegionNameArr;

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }


    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    private String privateIps;

    public String getUsageDes() {
        return usageDes;
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch;
    }

    public void setUsageDes(String usageDes) {
        this.usageDes = usageDes;
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

    public String getDeployOs() {
        return deployOs;
    }

    public void setDeployOs(String deployOs) {
        this.deployOs = deployOs;
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam;
    }

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct;
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }


    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }


    public String getLast_Modified() {
        return last_Modified;
    }

    public String getClassName() {
        return class_Name;
    }

    public void setLast_Modified(String last_Modified) {

        this.last_Modified = last_Modified;
    }


    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }


    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAzoneId() {
        return azoneId;
    }

    public void setAzoneId(String azoneId) {
        this.azoneId = azoneId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAzoneName() {
        return azoneName;
    }

    public void setAzoneName(String azoneName) {
        this.azoneName = azoneName;
    }


    public String getPrivateIps() {
        return privateIps;
    }

    public void setPrivateIps(String privateIps) {
        this.privateIps = privateIps;
    }


    public String getCpuCoreNum() {
        return cpuCoreNum;
    }

    public void setCpuCoreNum(String cpuCoreNum) {
        this.cpuCoreNum = cpuCoreNum;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(List statusArr) {
        this.statusArr = statusArr;
    }


    public CloudVmare(String resId) {
        this.resId = resId;
    }

    @Alias(columnName = "deployAdminA", alias = "部署管理员A")
    @Excel(name = "部署管理员A")
    // 部署管理员A
    private String deployAdminA;
    // 部署管理员B
    @Alias(columnName = "deployAdminB", alias = "部署管理员B")
    @Excel(name = "部署管理员B")
    private String deployAdminB;
    // 是否接入4A字段（0已接入、1未接入）
    @Alias(columnName = "fourA", alias = "是否接入4A", businessType = "enum")
    @EnumNote(code = {"0", "1"}, value = {"已接入", "未接入"})
    @Excel(name = "4A")
    private Integer fourA;
    // 防病毒软件安全情况（0天擎在线，1天擎离线，2SEP在线，3SEP离线,4未接入，5白名单）
    @Alias(columnName = "antivirusSoftware", alias = "防病毒软件安全情况", businessType = "enum")
    @EnumNote(code = {"0", "1", "2", "3", "4", "5"}, value = {"天擎在线", "天擎离线", "SEP在线", "SEP离线", "未接入", "白名单"})
    @Excel(name = "防病毒软件")
    private Integer antivirusSoftware;
}
