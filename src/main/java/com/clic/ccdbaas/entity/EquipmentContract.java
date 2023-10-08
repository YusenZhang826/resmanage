package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import com.clic.ccdbaas.utils.excel.Excel.Type;

import java.io.Serializable;
import java.util.Date;
@TableName("t_equipment_contract")
public class EquipmentContract implements Serializable {
   @Excel(name = "资源id",type = Type.EXPORT)
    private String resId;
   @Excel(name = "IT资源配置项目（采购批次）")
    private String procureBatch;
   @Excel(name = "设备名称")
    private String equipmentName;
   @Excel(name = "合同编号")
    private String contractNo;
   @Excel(name = "硬件数量")
    private Integer equipmentNum;
   @Excel(name = "合同金额(元)")
    private Double contractAmount;
   @Excel(name = "签署日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;
   @Excel(name = "厂商")
    private String manufacturer;
   @Excel(name = "品牌")
    private String brand;
   @Excel(name = "到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arriveTime;
   @Excel(name = "初验日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstCheckTime;
   @Excel(name = "终验日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastCheckTime;
   @Excel(name = "维保起止时间")
    private String maintenanceTime;
   @Excel(name = "合同名称")
    private String contractName;
   @Excel(name = "合同签订情况")
    private String contractStatus;
    @Excel(name = "合同内容")
    private String content;
    @Excel(name = "设备类型")
    private String equipmentType;

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    private String linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getProcureBatch() {
        return procureBatch;
    }

    public void setProcureBatch(String procureBatch) {
        this.procureBatch = procureBatch == null ? null : procureBatch.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public Integer getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(Integer equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getFirstCheckTime() {
        return firstCheckTime;
    }

    public void setFirstCheckTime(Date firstCheckTime) {
        this.firstCheckTime = firstCheckTime;
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public String getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(String maintenanceTime) {
        this.maintenanceTime = maintenanceTime == null ? null : maintenanceTime.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus == null ? null : contractStatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", procureBatch=").append(procureBatch);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", equipmentNum=").append(equipmentNum);
        sb.append(", contractAmount=").append(contractAmount);
        sb.append(", signTime=").append(signTime);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", brand=").append(brand);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", firstCheckTime=").append(firstCheckTime);
        sb.append(", lastCheckTime=").append(lastCheckTime);
        sb.append(", maintenanceTime=").append(maintenanceTime);
        sb.append(", contractName=").append(contractName);
        sb.append(", contractStatus=").append(contractStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}