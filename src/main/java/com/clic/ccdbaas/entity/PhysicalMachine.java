package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.Annotation.Alias;
import com.clic.ccdbaas.Annotation.EnumNote;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import javax.persistence.Table;
import java.util.List;

@Data
@TableName(value = "t_physicalserver")
@Table(name = "t_physicalserver")
public class PhysicalMachine {
    @Alias(columnName = "resId", alias = "资源ID")
    @Excel(name = "资源ID")
    private String resId;
    @Alias(columnName = "mainIp", alias = "主机IP")
    @Excel(name = "IP地址")
    private String mainIp;
    @Alias(columnName = "relateIp", alias = "相关IP")
    @Excel(name = "相关IP")
    private String relateIp;
    @Alias(columnName = "virtualIp", alias = "虚IP")
    @Excel(name = "虚IP")
    private String virtualIp;
    @Alias(columnName = "networkArea", alias = "网路区域")
    @Excel(name = "网络逻辑区域")
    private String networkArea;
    @Alias(columnName = "locationCode", alias = "位置编码")
    @Excel(name = "位置编码")
    private String locationCode;
    @Alias(columnName = "mgmtIp", alias = "BMCIP")
    @Excel(name = "带外ip")
    private String bmcIp;
    @Alias(columnName = "productToken", alias = "产品Token")
    private String productToken;
    @Alias(columnName = "name", alias = "名称")
    @Excel(name = "名称")
    private String name;
    @Alias(columnName = "deployEnv", alias = "部署环境", businessType = "enum")
    @EnumNote(code = {"0", "1"}, value = {"生产", "测试"})
    @Excel(name = "部署环境", readConverterExp = "0=生产,1=测试")
    private String deployEnv;
    @Alias(columnName = "usageDes", alias = "用途描述")
    @Excel(name = "用途描述")
    private String usageDes;
    @Excel(name = "资源类型", readConverterExp = "CLOUD_VM_NOVA=弹性云服务器,SYS_X86Server=物理服务器,SYS_PhysicalHost=宿主机")
    private String class_Name;
    @Alias(columnName = "deployOs", alias = "操作系统")
    @Excel(name = "部署操作系统")
    private String deployOs;
    @Alias(columnName = "resourceCode", alias = "资源分配代码")
    @Excel(name = "资源分配代码")
    private String resourceCode;
    @Alias(columnName = "cpuCores", alias = "VCPU（个）")
    @Excel(name = "CPU")
    private String cpuCores;
    @Alias(columnName = "memoryCapacity", alias = "内存（MB）")
    @Excel(name = "内存大小")
    private String memoryCapacity;

    private List deployEnvArr;
    @Alias(columnName = "diskCapacity", alias = "存储（GB）")
    @Excel(name = "磁盘大小")
    private String diskCapacity;
    @Alias(columnName = "status", alias = "状态", businessType = "enum")
    @Excel(name = "状态", readConverterExp = "active=运行中,deleted=已删除,error=故障,fault_resuming=故障恢复中,offline=离线（关机）,recycling=回收中,removed=软删除,starting=启动中,stopping=停止中,shutting_down=删除中,unknown=未知,verify_resize=更新规格校验中,other=其他,storage_migrating=磁盘迁移中,hibernated=已休眠,live_volume_migrating_fail=跨存储热迁移失败,live_volume_migrating=跨存储热迁移中,migrating=迁移中,building=创建中,_failed=创建失败,create_success=创建成功,reboot=重启中,resize=修改中,booting=上电中,initial=初始化,shutdowning=下电中")
    @EnumNote(code = {"deleted", "active", "error", "fault_resuming", "offline", "recycling", "removed", "starting", "stopping", "shutting_down", "unknown", "verify_resize", "other", "storage_migrating", "hibernated", "hibernating", "live_volume_migrating_fail", "live_volume_migrating", "migrating", "building", "create_failed", "create_success", "reboot", "resize", "booting", "initial", "shutdowning"},
            value = {"已删除", "运行中", "故障", "恢复中", "离线", "回收中", "软删除", "启动中", "停止中", "删除中", "未知", "更新规格校验中", "其他", "磁盘迁移中", "已休眠", "休眠中", "跨存储热迁移失败", "跨存储热迁移中", "迁移中", "创建中", "创建失败", "创建成功", "重启中", "修改中", "上电中", "初始化", "下电中"})
    private String status;
    @Alias(columnName = "belongProduct", alias = "归属产品")
    @Excel(name = "归属产品")
    private String belongProduct;
    @Alias(columnName = "belongCompany", alias = "产品属主单位")
    @Excel(name = "产品属主单位")
    private String belongCompany;
    @Alias(columnName = "sysAdminBackup", alias = "主机管理员B")
    @Excel(name = "主机管理员B")
    private String sysAdminBackup;
    @Alias(columnName = "sysAdministrator", alias = "主机管理员A")
    @Excel(name = "主机管理员A")
    private String sysAdministrator;
    @Alias(columnName = "stakeholder", alias = "干系人")
    @Excel(name = "干系人")
    private String stakeholder;
    @Alias(columnName = "pingStatus", alias = "Ping状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2"}, value = {"在线", "离线", "白名单"})
    @Excel(name = "Ping状态")
    private String pingStatus;
    @Alias(columnName = "hidsStatus", alias = "Hids状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2", "3", "4"}, value = {"在线", "离线", "停用", "未接入", "白名单"})
    @Excel(name = "Hids状态")
    private String hidsStatus;
    @Alias(columnName = "zabbixStatus", alias = "Zabbix状态", businessType = "enum")
    @EnumNote(code = {"0", "1", "2", "3"}, value = {"在线", "离线", "未接入", "白名单"})
    @Excel(name = "Zabbix状态")
    private String zabbixStatus;
    @Alias(columnName = "osAdminUser", alias = "OS管理员")
    @Excel(name = "Os管理员用户")
    private String osAdminUser;
    @Alias(columnName = "osNormalUser", alias = "OS普通用户")
    @Excel(name = "Os普通用户")
    private String osNormalUSer;
    @Alias(columnName = "cpuArch", alias = "CPU架构", businessType = "enum")
    @EnumNote(code = {"x86", "arm", "other"}, value = {"x86", "arm", "其他"})
    @Excel(name = "cpu架构")
    private String cpuArch;
    private String paramValue;
    @Alias(columnName = "bizregionName", alias = "Region名称")
    @Excel(name = "region名称")
    private String bizregionName;
    @Alias(columnName = "nativeId", alias = "原ID")
    @Excel(name = "原Id")
    private String nativeId;
    @Alias(columnName = "sysAdminTeam", alias = "主机运行团队")
    @Excel(name = "主机运行团队")
    private String sysAdminTeam;
    @Alias(columnName = "last_Modified", alias = "最后修改时间", businessType = "DateTime")
    @Excel(name = "最后修改时间")
//    @DateTimeFormat("yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String last_Modified;
    private List statusArr;
    private List regionNameArr;

    public PhysicalMachine(String mainIp, String resId) {
        this.mainIp = mainIp;
        this.resId = resId;
    }

    public PhysicalMachine(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public PhysicalMachine() {
    }

    // 部署管理员A
    private String deployAdminA;
    // 部署管理员B
    private String deployAdminB;
    // 是否接入4A字段（0已接入、1未接入）
    private Integer fourA;
    // 防病毒软件安全情况（0天擎在线，1天擎离线，2SEP在线，3SEP离线,4未接入，5白名单）
    private Integer antivirusSoftware;
    //序列号
    private String sn;
}
