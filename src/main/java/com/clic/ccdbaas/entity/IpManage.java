package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@TableName(value = "t_ip_manage")
public class IpManage implements Serializable {
    @TableField(exist = false)
    private List<String> userOrgArr;
    @TableField(exist = false)
    private List<String> resIdArr;
    @TableField(exist = false)
    @Excel(name = "网络逻辑区域")
    @ExcelProperty("网络逻辑区域")
    private String areaName;
    @TableField(exist = false)
    private int exploratoryStateNullFlag;
    @Excel(name = "ID")
    @ExcelProperty("ID")
    private String resId;
    @Excel(name = "归属VLAN名称")
    @ExcelProperty("归属VLAN名称")
    private String vlanName;
    @Excel(name = "IP")
    @ExcelProperty("IP")
    private String ip;
    @Excel(name = "分配状态")
    @ExcelProperty("分配状态")
    private String allocateStatus;
    @Excel(name = "探活状态")
    @ExcelProperty("探活状态")
    private String exploratoryState;
    @Excel(name = "探活时间")
    @ExcelProperty("探活时间")
    private Date exploratoryDate;
    @Excel(name = "申请信息")
    @ExcelProperty("申请信息")
    private String applicationInfo;
    @Excel(name = "归属产品")
    @ExcelProperty("归属产品")
    private String belongProduct;
    @Excel(name = "申请人")
    @ExcelProperty("申请人")
    private String applicant;
    @Excel(name = "分配人")
    @ExcelProperty("分配人")
    private String allocator;
    @Excel(name = "分配日期")
    @ExcelProperty("分配日期")
    private Date allocateDate;
    @Excel(name = "回收日期")
    @ExcelProperty("回收日期")
    private Date recycleDate;
    @Excel(name = "最近更新时间")
    @ExcelProperty("最近更新时间")
    private Date lastModified;
    @Excel(name = "备注")
    @ExcelProperty("备注")
    private String remark;
    @Excel(name = "申请工单号")
    @ExcelProperty("申请工单号")
    private String applicationOrder;
    @Excel(name = "回收工单号")
    @ExcelProperty("回收工单号")
    private String recycleOrder;
    @Excel(name = "回收申请人")
    @ExcelProperty("回收申请人")
    private String recycleApplicant;
    @TableField(exist = false)
    private List<String> allocateStatuss;
    @TableField(exist = false)
    private List<String> exploratoryStates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpManage ipManage = (IpManage) o;
        return resId.equals(ipManage.resId) && Objects.equals(vlanName, ipManage.vlanName) && Objects.equals(ip, ipManage.ip) && allocateStatus == ipManage.allocateStatus && Objects.equals(exploratoryState, ipManage.exploratoryState) && Objects.equals(exploratoryDate, ipManage.exploratoryDate) && Objects.equals(applicationInfo, ipManage.applicationInfo) && Objects.equals(belongProduct, ipManage.belongProduct) && Objects.equals(applicant, ipManage.applicant) && Objects.equals(allocator, ipManage.allocator) && Objects.equals(allocateDate, ipManage.allocateDate) && Objects.equals(recycleDate, ipManage.recycleDate) && Objects.equals(remark, ipManage.remark) && Objects.equals(applicationOrder, ipManage.applicationOrder) && Objects.equals(recycleOrder, ipManage.recycleOrder) && Objects.equals(recycleApplicant, ipManage.recycleApplicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, vlanName, ip, allocateStatus, exploratoryState, exploratoryDate, applicationInfo, belongProduct, applicant, allocator, allocateDate, recycleDate, lastModified, remark, applicationOrder, recycleOrder, recycleApplicant);
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getVlanName() {
        return vlanName;
    }

    public void setVlanName(String vlanName) {
        this.vlanName = vlanName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAllocateStatus() {
        return allocateStatus;
    }

    public void setAllocateStatus(String allocateStatus) {
        this.allocateStatus = allocateStatus;
    }

    public String getExploratoryState() {
        return exploratoryState;
    }

    public void setExploratoryState(String exploratoryState) {
        this.exploratoryState = exploratoryState;
    }

    public Date getExploratoryDate() {
        return exploratoryDate;
    }

    public void setExploratoryDate(Date exploratoryDate) {
        this.exploratoryDate = exploratoryDate;
    }

    public String getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(String applicationInfo) {
        this.applicationInfo = applicationInfo;
    }

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAllocator() {
        return allocator;
    }

    public void setAllocator(String allocator) {
        this.allocator = allocator;
    }

    public Date getAllocateDate() {
        return allocateDate;
    }

    public void setAllocateDate(Date allocateDate) {
        this.allocateDate = allocateDate;
    }

    public Date getRecycleDate() {
        return recycleDate;
    }

    public void setRecycleDate(Date recycleDate) {
        this.recycleDate = recycleDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplicationOrder() {
        return applicationOrder;
    }

    public void setApplicationOrder(String applicationOrder) {
        this.applicationOrder = applicationOrder;
    }

    public String getRecycleOrder() {
        return recycleOrder;
    }

    public void setRecycleOrder(String recycleOrder) {
        this.recycleOrder = recycleOrder;
    }

    public String getRecycleApplicant() {
        return recycleApplicant;
    }

    public void setRecycleApplicant(String recycleApplicant) {
        this.recycleApplicant = recycleApplicant;
    }

    private static final long serialVersionUID = 1L;
}