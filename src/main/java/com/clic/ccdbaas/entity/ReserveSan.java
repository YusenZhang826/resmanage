package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.enums.ResourceStatus;
import com.clic.ccdbaas.model.BaseEntity;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import java.util.Date;

@TableName(value = "t_reserve_san")
@Data
public class ReserveSan extends BaseEntity {
    @TableId
    private String resId;

    @ExcelProperty("设备名称")
    @Excel(name = "设备名称")
    private String name;

    @ExcelProperty("序列号")
    @Excel(name = "序列号")
    private String sn;

    @ExcelProperty("资源状态")
    @Excel(name = "资源状态")
    private ResourceStatus resourceStatus;

    @ExcelProperty("维护部门")
    @Excel(name = "维护部门")
    private String maintenanceDepartment;

    @ExcelProperty("管理员")
    @Excel(name = "管理员")
    private String admins;

    @ExcelProperty("管理用户名")
    @Excel(name = "管理用户名")
    private String adminUserName;

    @ExcelProperty("别名")
    @Excel(name = "别名")
    private String alias;

    @ExcelProperty("用途描述")
    @Excel(name = "用途描述")
    private String useDescription;

    @ExcelProperty("所属机房")
    @Excel(name = "所属机房")
    private String physicalPosition;

    @ExcelProperty("相关IP地址")
    @Excel(name = "相关IP地址")
    private String ip;

    @ExcelProperty("厂商")
    @Excel(name = "厂商")
    private String manufacturer;

    @ExcelProperty("设备型号")
    @Excel(name = "设备型号")
    private String deviceModel;

    @ExcelProperty("资产归属")
    @Excel(name = "资产归属")
    private String assetAttribution;

    @ExcelProperty("SAP号")
    @Excel(name = "SAP号")
    private String sap;

    @ExcelProperty("维保标记")
    @Excel(name = "维保标记")
    private String warrantyMarking;

    @ExcelProperty("维保备注")
    @Excel(name = "维保备注")
    private String warrantyRemark;

    @ExcelProperty("采购合同")
    @Excel(name = "采购合同")
    private String purchaseContract;

    @ExcelProperty("维保开始日期")
    @Excel(name = "维保开始日期")
    private Date warrantyStartTime;

    @ExcelProperty("维保结束日期")
    @Excel(name = "维保结束日期")
    private Date warrantyEndTime;

    @ExcelProperty("创建者")
    @Excel(name = "创建者")
    private String creator;

    @ExcelProperty("维保合同")
    @Excel(name = "维保合同")
    private String warrantyContract;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getMaintenanceDepartment() {
        return maintenanceDepartment;
    }

    public void setMaintenanceDepartment(String maintenanceDepartment) {
        this.maintenanceDepartment = maintenanceDepartment == null ? null : maintenanceDepartment.trim();
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins == null ? null : admins.trim();
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName == null ? null : adminUserName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getUseDescription() {
        return useDescription;
    }

    public void setUseDescription(String useDescription) {
        this.useDescription = useDescription == null ? null : useDescription.trim();
    }

    public String getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(String physicalPosition) {
        this.physicalPosition = physicalPosition == null ? null : physicalPosition.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getAssetAttribution() {
        return assetAttribution;
    }

    public void setAssetAttribution(String assetAttribution) {
        this.assetAttribution = assetAttribution == null ? null : assetAttribution.trim();
    }

    public String getSap() {
        return sap;
    }

    public void setSap(String sap) {
        this.sap = sap == null ? null : sap.trim();
    }

    public String getWarrantyMarking() {
        return warrantyMarking;
    }

    public void setWarrantyMarking(String warrantyMarking) {
        this.warrantyMarking = warrantyMarking == null ? null : warrantyMarking.trim();
    }

    public String getWarrantyRemark() {
        return warrantyRemark;
    }

    public void setWarrantyRemark(String warrantyRemark) {
        this.warrantyRemark = warrantyRemark == null ? null : warrantyRemark.trim();
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract == null ? null : purchaseContract.trim();
    }

    public Date getWarrantyStartTime() {
        return warrantyStartTime;
    }

    public void setWarrantyStartTime(Date warrantyStartTime) {
        this.warrantyStartTime = warrantyStartTime;
    }

    public Date getWarrantyEndTime() {
        return warrantyEndTime;
    }

    public void setWarrantyEndTime(Date warrantyEndTime) {
        this.warrantyEndTime = warrantyEndTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getWarrantyContract() {
        return warrantyContract;
    }

    public void setWarrantyContract(String warrantyContract) {
        this.warrantyContract = warrantyContract == null ? null : warrantyContract.trim();
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
        sb.append(", sn=").append(sn);
        sb.append(", resourceStatus=").append(resourceStatus);
        sb.append(", maintenanceDepartment=").append(maintenanceDepartment);
        sb.append(", admins=").append(admins);
        sb.append(", adminUserName=").append(adminUserName);
        sb.append(", alias=").append(alias);
        sb.append(", useDescription=").append(useDescription);
        sb.append(", physicalPosition=").append(physicalPosition);
        sb.append(", ip=").append(ip);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", deviceModel=").append(deviceModel);
        sb.append(", assetAttribution=").append(assetAttribution);
        sb.append(", sap=").append(sap);
        sb.append(", warrantyMarking=").append(warrantyMarking);
        sb.append(", warrantyRemark=").append(warrantyRemark);
        sb.append(", purchaseContract=").append(purchaseContract);
        sb.append(", warrantyStartTime=").append(warrantyStartTime);
        sb.append(", warrantyEndTime=").append(warrantyEndTime);
        sb.append(", creator=").append(creator);
        sb.append(", warrantyContract=").append(warrantyContract);
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