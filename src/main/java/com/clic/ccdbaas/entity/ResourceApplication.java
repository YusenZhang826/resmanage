package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@TableName("t_resource_application")
public class ResourceApplication {
    @ExcelProperty("ID")
    @Excel(name = "ID")
    private String resId;
    @ExcelProperty("标题")
    @Excel(name = "标题")
    private String workOrder;
    @ExcelProperty("归属产品")
    @Excel(name = "归属产品")
    private String belongProduct;
    @ExcelProperty("产品简称")
    @Excel(name = "产品简称")
    private String productAbbr;
    @ExcelProperty("产品Token")
    @Excel(name = "产品Token")
    private String productToken;
    @ExcelProperty("部署人员")
    @Excel(name = "部署人员")
    private String omUids;
    @ExcelProperty("系统人员")
    @Excel(name = "系统人员")
    private String smUids;
    @ExcelProperty("主机管理员")
    @Excel(name = "主机管理员")
    private String sysAdministrator;
    @ExcelProperty("主IP")
    @Excel(name = "主IP")
    private String mainIp;
    @ExcelProperty("虚IP")
    @Excel(name = "虚IP")
    private String virtualIp;
    @ExcelProperty("相关IP")
    @Excel(name = "相关IP")
    private String relatedIp;
    @ExcelProperty("序列号")
    @Excel(name = "序列号")
    private String sn;
    @ExcelProperty("应用名称")
    @Excel(name = "应用名称")
    private String projectName;
    @ExcelProperty("应用简称")
    @Excel(name = "应用简称")
    private String projectAbbr;
    @ExcelProperty("应用Token")
    @Excel(name = "应用Token")
    private String projectToken;
    @ExcelProperty("机器类型")
    @Excel(name = "机器类型")
    private String machineType;
    @ExcelProperty("主机规格")
    @Excel(name = "主机规格")
    private String hostSpecs;
    @ExcelProperty("存储量(GB)")
    @Excel(name = "存储量(GB)")
    private String storageNum;
    @ExcelProperty("操作系统")
    @Excel(name = "操作系统")
    private String systemType;
    @ExcelProperty("资源用户描述")
    @Excel(name = "资源用户描述")
    private String resourceDetail;
    @ExcelProperty("数据库类型")
    @Excel(name = "数据库类型")
    private String databaseType;
    @ExcelProperty("中间件类型")
    @Excel(name = "中间件类型")
    private String middlewareType;
    @ExcelProperty("网络区域")
    @Excel(name = "网络区域")
    private String networkArea;
    @ExcelProperty("资源分配代码")
    @Excel(name = "资源分配代码")
    private String resourceCode;
    @ExcelProperty("分配意见")
    @Excel(name = "分配意见")
    private String allocOpinion;
    @ExcelProperty("复核意见")
    @Excel(name = "复核意见")
    private String reviewOpinion;
    @ExcelProperty("申请人")
    @Excel(name = "申请人")
    private String uid;
    @ExcelProperty("主机运行团队")
    @Excel(name = "主机运行团队")
    private String sysAdminTeam;
    @ExcelProperty("部署环境")
    @Excel(name = "部署环境")
    private String deployEnv;
    @ExcelProperty("物理机位置编号")
    @Excel(name = "物理机位置编号")
    private String bmsNetAreaNo;
    @ExcelProperty("物理机wwn号")
    @Excel(name = "物理机wwn号")
    private String bmsWwnNo;
    @ExcelProperty("最后修改时间")
    @Excel(name = "最后修改时间")
    private Date last_Modified;
    private String isApply;

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

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct;
    }

    public String getProductAbbr() {
        return productAbbr;
    }

    public void setProductAbbr(String productAbbr) {
        this.productAbbr = productAbbr;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getOmUids() {
        return omUids;
    }

    public void setOmUids(String omUids) {
        this.omUids = omUids;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAbbr() {
        return projectAbbr;
    }

    public void setProjectAbbr(String projectAbbr) {
        this.projectAbbr = projectAbbr;
    }

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getHostSpecs() {
        return hostSpecs;
    }

    public void setHostSpecs(String hostSpecs) {
        this.hostSpecs = hostSpecs;
    }

    public String getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(String storageNum) {
        this.storageNum = storageNum;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(String resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getMiddlewareType() {
        return middlewareType;
    }

    public void setMiddlewareType(String middlewareType) {
        this.middlewareType = middlewareType;
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getAllocOpinion() {
        return allocOpinion;
    }

    public void setAllocOpinion(String allocOpinion) {
        this.allocOpinion = allocOpinion;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator;
    }

    public String getSmUids() {
        return smUids;
    }

    public void setSmUids(String smUids) {
        this.smUids = smUids;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam;
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public String getBmsNetAreaNo() {
        return bmsNetAreaNo;
    }

    public void setBmsNetAreaNo(String bmsNetAreaNo) {
        this.bmsNetAreaNo = bmsNetAreaNo;
    }

    public String getBmsWwnNo() {
        return bmsWwnNo;
    }

    public void setBmsWwnNo(String bmsWwnNo) {
        this.bmsWwnNo = bmsWwnNo;
    }

    public Date getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(Date last_Modified) {
        this.last_Modified = last_Modified;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }
}
