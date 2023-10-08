package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_mc_physicalhost")
public class McPhysicalHost {
    @Excel(name = "名称")
    @ExcelProperty("名称")
    private String name;
    @Excel(name = "资源ID")
    @ExcelProperty("资源ID")
    private String resId;

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    @ExcelProperty("网络位置")
    @Excel(name = "网络位置")
    private String networkArea;
    @ExcelProperty("部署环境")
    @Excel(name = "部署环境", readConverterExp = "0=生产,1=测试")
    private String deployEnv;
    @ExcelProperty("用途描述")
    @Excel(name = "用途描述")
    private String usageDes;
    @Excel(name = "资源类型", readConverterExp = "CLOUD_VM_NOVA=弹性云服务器,SYS_X86Server=物理服务器,SYS_PhysicalHost=宿主机")
    @ExcelProperty("资源类型")
    private String class_Name;
    @ExcelProperty("操作系统")
    @Excel(name = "操作系统")
    private String deployOs;
    @ExcelProperty("资源分配代码")
    @Excel(name = "资源分配代码")
    private String resourceCode;
    @ExcelProperty("VCPU")
    @Excel(name = "VCPU")
    private String totalVcpuCores;
    private String totalCpu;
    @Excel(name = "内存")
    @ExcelProperty("内存")
    private String totalMemory;
    @ExcelProperty("硬盘")
    @Excel(name = "硬盘")
    private String totalDiskSizeMB;
    @Excel(name = "状态", readConverterExp = "active=运行中,deleted=已删除,error=故障,fault_resuming=故障恢复中,offline=离线（关机）,recycling=回收中,removed=软删除,starting=启动中,stopping=停止中,shutting_down=删除中,unknown=未知,verify_resize=更新规格校验中,other=其他,storage_migrating=磁盘迁移中,hibernated=已休眠,live_volume_migrating_fail=跨存储热迁移失败,live_volume_migrating=跨存储热迁移中,migrating=迁移中,building=创建中,_failed=创建失败,create_success=创建成功,reboot=重启中,resize=修改中,booting=上电中,initial=初始化,shutdowning=下电中")
    @ExcelProperty("状态")
    private String status;
    @ExcelProperty("归属产品")
    @Excel(name = "归属产品")
    private String belongProduct;
    @ExcelProperty("产品属主单位")
    @Excel(name = "产品属主单位")
    private String belongCompany;
    @ExcelProperty("主机管理员A")
    @Excel(name = "主机管理员A")
    private String sysAdministrator;
    @ExcelProperty("主机管理员B")
    @Excel(name = "主机管理员B")
    private String sysAdminBackup;
    private String stakeholder;

    private String productToken;


    @Excel(name = "Ping状态")
    @ExcelProperty("Ping状态")
    private String pingStatus;
    @ExcelProperty("Hids状态")
    @Excel(name = "Hids状态")
    private String hidsStatus;
    @ExcelProperty("Zabbix状态")
    @Excel(name = "Zabbix状态")
    private String zabbixStatus;
    @ExcelProperty("Os管理员用户")
    @Excel(name = "Os管理员用户")
    private String osAdminUser;
    @ExcelProperty("Os普通用户")
    @Excel(name = "Os普通用户")
    private String osNormalUser;
    @Excel(name = "主机Ip")
    @ExcelProperty("主机Ip")
    private String mainIp;

    @ExcelProperty("最后修改时间")
    @Excel(name = "最后修改时间")
    private String last_Modified;
    @ExcelProperty("主机运行团队")
    @Excel(name = "主机运行团队")
    private String sysAdminTeam;
    @ExcelProperty("原Id")
    @Excel(name = "原Id")
    private String nativeId;
    @ExcelProperty("cpu架构")
    @Excel(name = "cpu架构")
    private String cpuArch;
    private List statusArr;
    private String paramValue;

    private List deployEnvArr;
    @Excel(name = "Ip")
    private String ipAddress;
    private String serialNumber;
    private String appModToken;
    private String appModName;
    private String hostId;
    private String vcdbId;
    private String virtualIp;
    private String relatedIp;
    private String regionId;
    private String azoneId;
    private String azoneName;
    private String bizRegionId;
    private String clusterId;
    private String clusterName;
    private int allocatedVcpuCores;
    private int freeVcpuCores;
    private double allocatedVmemoryMB;
    private double freeVmemoryMB;
    private double allocatedDiskSizeMB;
    private double freeDiskSizeMB;

    @ExcelProperty("设备分类")
    @ColumnWidth(20)
    private String deviceAssort;
    @ExcelProperty("序列号")
    @ColumnWidth(20)
    private String sn;
    @ExcelProperty("分配状态")
    @ColumnWidth(20)
    private String allocateStatus;
    @ExcelProperty("维护部门")
    @ColumnWidth(20)
    private String standTeam;
    @ExcelProperty("设备厂商")
    @ColumnWidth(20)
    private String manufacturer;
    @ExcelProperty("设备型号")
    @ColumnWidth(20)
    private String deviceModel;
    @ExcelProperty("资产状态")
    @ColumnWidth(20)
    private String assetStatus;
    @ExcelProperty("资产归属")
    @ColumnWidth(20)
    private String assetBelong;
    @ExcelProperty("SAP号")
    @ColumnWidth(20)
    private String SAPNum;
    @ExcelProperty("新SAP号")
    @ColumnWidth(20)
    private String NewSAPNum;
    @ExcelProperty("采购合同号")
    @ColumnWidth(20)
    private String purchaseContract;
    @ExcelProperty("维保合同号")
    @ColumnWidth(20)
    private String warrantyContract;
    @ExcelProperty("维保开始日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date warrantyStartTime;
    @ExcelProperty("维保结束日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date warrantyEndTime;
    @ExcelProperty("维保厂商")
    @ColumnWidth(20)
    private String warrantyManufacturer;
    @ExcelProperty("维保备注")
    @ColumnWidth(20)
    private String warrantyRemark;
    @ExcelProperty("CPU厂商")
    @ColumnWidth(20)
    private String cpuManufacturer;
    @ExcelProperty("CPU型号")
    @ColumnWidth(20)
    private String cpuModel;
    @ExcelProperty("物理CPU个数(个)")
    @ColumnWidth(20)
    private String cpuCount;
    @ExcelProperty("单个CPU核数(C)")
    @ColumnWidth(20)
    private String cpuSize;
    @ExcelProperty("内存型号")
    @ColumnWidth(20)
    private String memModel;
    @ExcelProperty("内存数量(个)")
    @ColumnWidth(20)
    private String memCount;
    @ExcelProperty("单个内存容量(GB)")
    @ColumnWidth(20)
    private String memSize;
    @ExcelProperty("硬盘1型号(系统盘)")
    @ColumnWidth(20)
    private String disk1Model;
    @ExcelProperty("硬盘1数量(个)")
    @ColumnWidth(20)
    private String disk1Count;
    @ExcelProperty("硬盘1大小(GB)")
    @ColumnWidth(20)
    private String disk1Size;
    @ExcelProperty("硬盘2型号(数据盘)")
    @ColumnWidth(20)
    private String disk2Model;
    @ExcelProperty("硬盘2数量(个)")
    @ColumnWidth(20)
    private String disk2Count;
    @ExcelProperty("硬盘2大小(GB)")
    @ColumnWidth(20)
    private String disk2Size;
    @ExcelProperty("硬盘3型号(其他盘)")
    @ColumnWidth(20)
    private String disk3Model;
    @ExcelProperty("硬盘3数量(个)")
    @ColumnWidth(20)
    private String disk3Count;
    @ExcelProperty("硬盘3大小(GB)")
    @ColumnWidth(20)
    private String disk3Size;
    @ExcelProperty("HBA卡型号")
    @ColumnWidth(20)
    private String HBAModel;
    @ExcelProperty("HBA卡数量")
    @ColumnWidth(20)
    private String HBACount;
    @ExcelProperty("物理网卡型号")
    @ColumnWidth(20)
    private String netCardModel;
    @ExcelProperty("物理网卡数量")
    @ColumnWidth(20)
    private String netCardNum;
    @ExcelProperty("RAID卡型号")
    @ColumnWidth(20)
    private String raidModel;
    @ExcelProperty("RAID卡数量")
    @ColumnWidth(20)
    private String raidCount;
    @ExcelProperty("PCIE卡型号")
    @ColumnWidth(20)
    private String pcieModel;
    @ExcelProperty("PCIE卡数量")
    @ColumnWidth(20)
    private String pcieCount;
    @ExcelProperty("电源功率")
    @ColumnWidth(20)
    private String endIp;
    @ExcelProperty("电源数量")
    @ColumnWidth(20)
    private String powerNum;
    @ExcelProperty("风扇数量")
    @ColumnWidth(20)
    private String fanNum;
    @ExcelProperty("创建者")
    @ColumnWidth(20)
    private String createor;
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date createTime;
    @ExcelProperty("最后更新时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date lastUpdateTime;
    @ExcelProperty("物理位置")
    @ColumnWidth(20)
    private String physicalPosition;
    @ExcelProperty("上架位置")
    @ColumnWidth(20)
    private String shelfPosition;
    @ExcelProperty("分配BMC IP")
    @ColumnWidth(20)
    private String allocateBMCIP;

    @ExcelProperty("物理位置")
    @ColumnWidth(20)
    private String locationCode;

    @ExcelProperty("分配BMC IP")
    @ColumnWidth(20)
    private String bmcIp;
    @Excel(name = "region名称")
    @ExcelProperty("region名称")
    private String bizregionName;


    // 部署管理员A
    private String deployAdminA;
    // 部署管理员B
    private String deployAdminB;
    // 是否接入4A字段（0已接入、1未接入）
    private Integer fourA;
    // 防病毒软件安全情况（0天擎在线，1天擎离线，2SEP在线，3SEP离线,4未接入，5白名单）
    private Integer antivirusSoftware;

}
