package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.enums.ResourceStatus;
import com.clic.ccdbaas.enums.converter.ResourceStatusConverter;
import com.clic.ccdbaas.model.BaseEntity;
import com.clic.ccdbaas.utils.excel.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "t_reserve_server")
public class ReserveServer extends BaseEntity {
    @TableId
    private String resId;
    @ExcelProperty("名称")
    @Excel(name = "名称")
    private String name;
    @ExcelProperty("物理网卡数量")
    @Excel(name = "物理网卡数量")
    private Integer netCardNum;
    @ExcelProperty("RAID卡型号")
    @Excel(name = "RAID卡型号")
    private String raidModel;
    @ExcelProperty("RAID卡数量")
    @Excel(name = "RAID卡数量")
    private Integer raidCount;
    @ExcelProperty("PCIE卡型号")
    @Excel(name = "PCIE卡型号")
    private String pcieModel;
    @ExcelProperty("PCIE卡数量")
    @Excel(name = "PCIE卡数量")
    private Integer pcieCount;
    @ExcelProperty("电源功率(W)")
    @Excel(name = "电源功率(W)")
    private String powerModel;
    @ExcelProperty("电源数量")
    @Excel(name = "PCIE卡数量")
    private Integer powerNum;
    @ExcelProperty("风扇数量")
    @Excel(name = "风扇数量")
    private Integer fanNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdateTime;
    @ExcelProperty("BMC IP")
    @Excel(name = "BMC IP")
    private String allocateBMCIP;
    @ExcelProperty("上架位置")
    @Excel(name = "上架位置")
    private String shelfPosition;
    @ExcelProperty("资源分配代码")
    @Excel(name = "资源分配代码")
    private String resAllocationCode;
    @ExcelProperty("物理位置")
    @Excel(name = "物理位置")
    private String physicalPosition;
    @ExcelProperty("单个内存容量(GB)")
    @Excel(name = "单个内存容量(GB)")
    private Integer memSize;
    @ExcelProperty("内存数量")
    @Excel(name = "内存数量")
    private Integer memCount;
    @ExcelProperty("内存型号")
    @Excel(name = "内存型号")
    private String memModel;
    @ExcelProperty("单个CPU核数")
    @Excel(name = "单个CPU核数")
    private Integer cpuSize;
    @ExcelProperty("物理CPU个数")
    @Excel(name = "物理CPU个数")
    private Integer cpuCount;
    @ExcelProperty("CPU型号")
    @Excel(name = "CPU型号")
    private String cpuModel;
    @ExcelProperty("CPU厂商")
    @Excel(name = "CPU厂商")
    private String cpuManufacturer;
    @ExcelProperty("CPU架构")
    @Excel(name = "CPU架构")
    private String cpuArch;
    @ExcelProperty("维保备注")
    @Excel(name = "维保备注")
    private String warrantyRemark;
    @ExcelProperty("维保厂商")
    @Excel(name = "维保厂商")
    private String warrantyManufacturer;
    @ExcelProperty("维保结束日期")
    @Excel(name = "维保结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date warrantyEndTime;
    @ExcelProperty("维保开始日期")
    @Excel(name = "维保开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date warrantyStartTime;
    @ExcelProperty("维保合同号")
    @Excel(name = "维保合同号")
    private String warrantyContract;
    @ExcelProperty("采购合同号")
    @Excel(name = "采购合同号")
    private String purchaseContract;
    @ExcelProperty("新SAP号")
    @Excel(name = "新SAP号")
    private String newSAPNum;
    @ExcelProperty("SAP号")
    @Excel(name = "SAP号")
    private String SAPNum;
    @ExcelProperty("资产归属")
    @Excel(name = "资产归属")
    private String assetBelong;
    @ExcelProperty("设备型号")
    @Excel(name = "设备型号")
    private String deviceModel;
    @ExcelProperty("设备厂商")
    @Excel(name = "设备厂商")
    private String manufacturer;
    @ExcelProperty("维护部门")
    @Excel(name = "维护部门")
    private String standTeam;
    @ExcelProperty(value = "分配状态", converter = ResourceStatusConverter.class)
    @Excel(name = "分配状态")
    private ResourceStatus allocateStatus;
    @ExcelProperty("序列号(SN)")
    @Excel(name = "序列号(SN)")
    private String sn;
    @ExcelProperty("设备分类")
    @Excel(name = "设备分类")
    private String deviceAssort;
    @ExcelProperty("HBA卡型号")
    @Excel(name = "HBA卡型号")
    private String HBAModel;
    @ExcelProperty("HBA卡数量")
    @Excel(name = "HBA卡数量")
    private Integer HBACount;
    @ExcelProperty("物理网卡型号")
    @Excel(name = "物理网卡型号")
    private String netCardModel;
    @ExcelProperty("硬盘槽位数量")
    @Excel(name = "硬盘槽位数量")
    private Integer remainingDiskSlot;
    private String creator;


    @ExcelProperty(value = "管理员")
    @Excel(name = "管理员")
    private String admins;

    @ExcelProperty(value = "别名")
    @Excel(name = "别名")
    private String alias;

    @ExcelProperty(value = "成员单位")
    @Excel(name = "成员单位")
    private String memberCompany;

    @ExcelProperty(value = "维保标记")
    @Excel(name = "维保标记")
    private String warrantyTag;

    @ExcelProperty(value = "管理口用户名")
    @Excel(name = "管理口用户名")
    private String manageUsername;

    @ExcelProperty(value = "管理口密码")
    @Excel(name = "管理口密码")
    private String managePassword;

    @ExcelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    @JsonIgnore
    @ExcelProperty("硬盘1接口类型")
    @TableField(exist = false)
    private String disk1InterfaceClass;
    @JsonIgnore
    @ExcelProperty("硬盘1介质类型")
    @TableField(exist = false)
    private String disk1MediaClass;
    @JsonIgnore
    @ExcelProperty("硬盘1用途")
    @TableField(exist = false)
    private String disk1Uses;
    @JsonIgnore
    @ExcelProperty("硬盘1大小(GB)")
    @TableField(exist = false)
    private Integer disk1Size;
    @JsonIgnore
    @ExcelProperty("硬盘1数量")
    @TableField(exist = false)
    private Integer disk1Count;


    @JsonIgnore
    @ExcelProperty("硬盘2接口类型")
    @TableField(exist = false)
    private String disk2InterfaceClass;
    @JsonIgnore
    @ExcelProperty("硬盘2介质类型")
    @TableField(exist = false)
    private String disk2MediaClass;
    @JsonIgnore
    @ExcelProperty("硬盘2用途")
    @TableField(exist = false)
    private String disk2Uses;
    @JsonIgnore
    @ExcelProperty("硬盘2大小(GB)")
    @TableField(exist = false)
    private Integer disk2Size;
    @JsonIgnore
    @ExcelProperty("硬盘2数量")
    @TableField(exist = false)
    private Integer disk2Count;

    @JsonIgnore
    @ExcelProperty("硬盘3接口类型")
    @TableField(exist = false)
    private String disk3InterfaceClass;
    @JsonIgnore
    @ExcelProperty("硬盘3介质类型")
    @TableField(exist = false)
    private String disk3MediaClass;
    @JsonIgnore
    @ExcelProperty("硬盘3用途")
    @TableField(exist = false)
    private String disk3Uses;
    @JsonIgnore
    @ExcelProperty("硬盘3大小(GB)")
    @TableField(exist = false)
    private Integer disk3Size;
    @JsonIgnore
    @ExcelProperty("硬盘3数量")
    @TableField(exist = false)
    private Integer disk3Count;

    @JsonIgnore
    @ExcelProperty("硬盘4接口类型")
    @TableField(exist = false)
    private String disk4InterfaceClass;
    @JsonIgnore
    @ExcelProperty("硬盘4介质类型")
    @TableField(exist = false)
    private String disk4MediaClass;
    @JsonIgnore
    @ExcelProperty("硬盘4用途")
    @TableField(exist = false)
    private String disk4Uses;
    @JsonIgnore
    @ExcelProperty("硬盘4大小(GB)")
    @TableField(exist = false)
    private Integer disk4Size;
    @JsonIgnore
    @ExcelProperty("硬盘4数量")
    @TableField(exist = false)
    private Integer disk4Count;

    private String regionName;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNetCardNum() {
        return netCardNum;
    }

    public void setNetCardNum(Integer netCardNum) {
        this.netCardNum = netCardNum;
    }

    public String getRaidModel() {
        return raidModel;
    }

    public void setRaidModel(String raidModel) {
        this.raidModel = raidModel == null ? null : raidModel.trim();
    }

    public Integer getRaidCount() {
        return raidCount;
    }

    public void setRaidCount(Integer raidCount) {
        this.raidCount = raidCount;
    }

    public String getPcieModel() {
        return pcieModel;
    }

    public void setPcieModel(String pcieModel) {
        this.pcieModel = pcieModel == null ? null : pcieModel.trim();
    }

    public Integer getPcieCount() {
        return pcieCount;
    }

    public void setPcieCount(Integer pcieCount) {
        this.pcieCount = pcieCount;
    }

    public String getPowerModel() {
        return powerModel;
    }

    public void setPowerModel(String powerModel) {
        this.powerModel = powerModel == null ? null : powerModel.trim();
    }

    public Integer getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(Integer powerNum) {
        this.powerNum = powerNum;
    }

    public Integer getFanNum() {
        return fanNum;
    }

    public void setFanNum(Integer fanNum) {
        this.fanNum = fanNum;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAllocateBMCIP() {
        return allocateBMCIP;
    }

    public void setAllocateBMCIP(String allocateBMCIP) {
        this.allocateBMCIP = allocateBMCIP == null ? null : allocateBMCIP.trim();
    }

    public String getShelfPosition() {
        return shelfPosition;
    }

    public void setShelfPosition(String shelfPosition) {
        this.shelfPosition = shelfPosition == null ? null : shelfPosition.trim();
    }

    public String getResAllocationCode() {
        return resAllocationCode;
    }

    public void setResAllocationCode(String resAllocationCode) {
        this.resAllocationCode = resAllocationCode == null ? null : resAllocationCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getCpuSize() {
        return cpuSize;
    }

    public void setCpuSize(Integer cpuSize) {
        this.cpuSize = cpuSize;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel == null ? null : cpuModel.trim();
    }

    public String getWarrantyRemark() {
        return warrantyRemark;
    }

    public void setWarrantyRemark(String warrantyRemark) {
        this.warrantyRemark = warrantyRemark == null ? null : warrantyRemark.trim();
    }

    public String getWarrantyManufacturer() {
        return warrantyManufacturer;
    }

    public void setWarrantyManufacturer(String warrantyManufacturer) {
        this.warrantyManufacturer = warrantyManufacturer == null ? null : warrantyManufacturer.trim();
    }

    public Date getWarrantyEndTime() {
        return warrantyEndTime;
    }

    public void setWarrantyEndTime(Date warrantyEndTime) {
        this.warrantyEndTime = warrantyEndTime;
    }

    public Date getWarrantyStartTime() {
        return warrantyStartTime;
    }

    public void setWarrantyStartTime(Date warrantyStartTime) {
        this.warrantyStartTime = warrantyStartTime;
    }

    public String getWarrantyContract() {
        return warrantyContract;
    }

    public void setWarrantyContract(String warrantyContract) {
        this.warrantyContract = warrantyContract == null ? null : warrantyContract.trim();
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract == null ? null : purchaseContract.trim();
    }

    public String getNewSAPNum() {
        return newSAPNum;
    }

    public void setNewSAPNum(String newSAPNum) {
        this.newSAPNum = newSAPNum == null ? null : newSAPNum.trim();
    }

    public String getSAPNum() {
        return SAPNum;
    }

    public void setSAPNum(String SAPNum) {
        this.SAPNum = SAPNum == null ? null : SAPNum.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getStandTeam() {
        return standTeam;
    }

    public void setStandTeam(String standTeam) {
        this.standTeam = standTeam == null ? null : standTeam.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getHBAModel() {
        return HBAModel;
    }

    public void setHBAModel(String HBAModel) {
        this.HBAModel = HBAModel == null ? null : HBAModel.trim();
    }

    public Integer getHBACount() {
        return HBACount;
    }

    public void setHBACount(Integer HBACount) {
        this.HBACount = HBACount;
    }

    public String getNetCardModel() {
        return netCardModel;
    }

    public void setNetCardModel(String netCardModel) {
        this.netCardModel = netCardModel == null ? null : netCardModel.trim();
    }

    public Integer getRemainingDiskSlot() {
        return remainingDiskSlot;
    }

    public void setRemainingDiskSlot(Integer remainingDiskSlot) {
        this.remainingDiskSlot = remainingDiskSlot;
    }

    public String getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(String physicalPosition) {
        this.physicalPosition = physicalPosition == null ? null : physicalPosition.trim();
    }

    public String getCpuManufacturer() {
        return cpuManufacturer;
    }

    public void setCpuManufacturer(String cpuManufacturer) {
        this.cpuManufacturer = cpuManufacturer == null ? null : cpuManufacturer.trim();
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch == null ? null : cpuArch.trim();
    }

    public String getAssetBelong() {
        return assetBelong;
    }

    public void setAssetBelong(String assetBelong) {
        this.assetBelong = assetBelong == null ? null : assetBelong.trim();
    }

    public String getDeviceAssort() {
        return deviceAssort;
    }

    public void setDeviceAssort(String deviceAssort) {
        this.deviceAssort = deviceAssort == null ? null : deviceAssort.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getMemSize() {
        return memSize;
    }

    public void setMemSize(Integer memSize) {
        this.memSize = memSize;
    }

    public Integer getMemCount() {
        return memCount;
    }

    public void setMemCount(Integer memCount) {
        this.memCount = memCount;
    }

    public String getMemModel() {
        return memModel;
    }

    public void setMemModel(String memModel) {
        this.memModel = memModel == null ? null : memModel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", name=").append(name);
        sb.append(", netCardNum=").append(netCardNum);
        sb.append(", raidModel=").append(raidModel);
        sb.append(", raidCount=").append(raidCount);
        sb.append(", pcieModel=").append(pcieModel);
        sb.append(", pcieCount=").append(pcieCount);
        sb.append(", powerModel=").append(powerModel);
        sb.append(", powerNum=").append(powerNum);
        sb.append(", fanNum=").append(fanNum);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", allocateBMCIP=").append(allocateBMCIP);
        sb.append(", shelfPosition=").append(shelfPosition);
        sb.append(", resAllocationCode=").append(resAllocationCode);
        sb.append(", regionName=").append(regionName);
        sb.append(", cpuSize=").append(cpuSize);
        sb.append(", cpuCount=").append(cpuCount);
        sb.append(", cpuModel=").append(cpuModel);
        sb.append(", warrantyRemark=").append(warrantyRemark);
        sb.append(", warrantyManufacturer=").append(warrantyManufacturer);
        sb.append(", warrantyEndTime=").append(warrantyEndTime);
        sb.append(", warrantyStartTime=").append(warrantyStartTime);
        sb.append(", warrantyContract=").append(warrantyContract);
        sb.append(", purchaseContract=").append(purchaseContract);
        sb.append(", newSAPNum=").append(newSAPNum);
        sb.append(", SAPNum=").append(SAPNum);
        sb.append(", deviceModel=").append(deviceModel);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", standTeam=").append(standTeam);
        sb.append(", sn=").append(sn);
        sb.append(", HBAModel=").append(HBAModel);
        sb.append(", HBACount=").append(HBACount);
        sb.append(", netCardModel=").append(netCardModel);
        sb.append(", remainingDiskSlot=").append(remainingDiskSlot);
        sb.append(", physicalPosition=").append(physicalPosition);
        sb.append(", cpuManufacturer=").append(cpuManufacturer);
        sb.append(", cpuArch=").append(cpuArch);
        sb.append(", assetBelong=").append(assetBelong);
        sb.append(", allocateStatus=").append(allocateStatus);
        sb.append(", deviceAssort=").append(deviceAssort);
        sb.append(", creator=").append(creator);
        sb.append(", memSize=").append(memSize);
        sb.append(", memCount=").append(memCount);
        sb.append(", memModel=").append(memModel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}