package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import com.clic.ccdbaas.utils.excel.Excel.Type;


import java.io.Serializable;
import java.util.Date;
@TableName("t_maintenance_contract")
public class MaintenanceContract implements Serializable {
    @Excel(name = "记录ID")
    private String resId;
    @Excel(name = "合同号")
    private String contractNo;
    @Excel(name = "合同名称")
    private String name;
    @Excel(name = "合同甲方")
    private String partyAName;

    @Excel(name = "所属部门")
    private String belongDepartment;

    @Excel(name = "预算科目")
    private String budgetAccount;

    @Excel(name = "维保类型")
    private String maintenanceType;

    @Excel(name = "合同总金额(元)")
    private Double contractAmount;

    @Excel(name = "合同付款状态")
    private String contractStatus;

    @Excel(name = "合同签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    @Excel(name = "起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;
    @Excel(name = "结束日期备注")
    private String endTimeRemark;

    @Excel(name = "初验日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstCheckTime;

    @Excel(name = "终验日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastCheckTime;
    @Excel(name = "预算所属年度")
    private String budgetYear;
    @Excel(name = "签报号")
    private String signNo;
    @Excel(name = "合同执行人")
    private String contractExecutor;
    @Excel(name = "服务商")
    private String manufacturer;
    @Excel(name = "服务商账号")
    private String manufacturerAccount;
    @Excel(name = "供应商管理")
    private String manufacturerManager;
    @Excel(name = "厂商联系人")
    private String manufacturerContacts;
    @Excel(name = "厂商联系方式")
    private String manufacturerContactWay;
    @Excel(name = "后续合同编号")
    private String nextContractNo;
    @Excel(name = "后续签订状态")
    private String nextContractStatus;
    @Excel(name = "后续签订进展")
    private String nextContractProcess;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "合同附件")
    private String annex;
    @Excel(name = "累计支付额(元)")
    private Double accumulatedPaymentAmount;
    @Excel(name = "合同付款比例(%)")
    private String payProportion;
    @Excel(name = "创建者")
    private String creator;
    @Excel(name = "拥有者")
    private String owner;

    private String linkUrl;

    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "最后更新日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPartyAName() {
        return partyAName;
    }

    public void setPartyAName(String partyAName) {
        this.partyAName = partyAName == null ? null : partyAName.trim();
    }

    public String getBelongDepartment() {
        return belongDepartment;
    }

    public void setBelongDepartment(String belongDepartment) {
        this.belongDepartment = belongDepartment == null ? null : belongDepartment.trim();
    }

    public String getBudgetAccount() {
        return budgetAccount;
    }

    public void setBudgetAccount(String budgetAccount) {
        this.budgetAccount = budgetAccount == null ? null : budgetAccount.trim();
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType == null ? null : maintenanceType.trim();
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus == null ? null : contractStatus.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeRemark() {
        return endTimeRemark;
    }

    public void setEndTimeRemark(String endTimeRemark) {
        this.endTimeRemark = endTimeRemark == null ? null : endTimeRemark.trim();
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

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear == null ? null : budgetYear.trim();
    }

    public String getSignNo() {
        return signNo;
    }

    public void setSignNo(String signNo) {
        this.signNo = signNo == null ? null : signNo.trim();
    }

    public String getContractExecutor() {
        return contractExecutor;
    }

    public void setContractExecutor(String contractExecutor) {
        this.contractExecutor = contractExecutor == null ? null : contractExecutor.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getManufacturerAccount() {
        return manufacturerAccount;
    }

    public void setManufacturerAccount(String manufacturerAccount) {
        this.manufacturerAccount = manufacturerAccount == null ? null : manufacturerAccount.trim();
    }

    public String getManufacturerManager() {
        return manufacturerManager;
    }

    public void setManufacturerManager(String manufacturerManager) {
        this.manufacturerManager = manufacturerManager == null ? null : manufacturerManager.trim();
    }

    public String getManufacturerContacts() {
        return manufacturerContacts;
    }

    public void setManufacturerContacts(String manufacturerContacts) {
        this.manufacturerContacts = manufacturerContacts == null ? null : manufacturerContacts.trim();
    }

    public String getManufacturerContactWay() {
        return manufacturerContactWay;
    }

    public void setManufacturerContactWay(String manufacturerContactWay) {
        this.manufacturerContactWay = manufacturerContactWay == null ? null : manufacturerContactWay.trim();
    }

    public String getNextContractNo() {
        return nextContractNo;
    }

    public void setNextContractNo(String nextContractNo) {
        this.nextContractNo = nextContractNo == null ? null : nextContractNo.trim();
    }

    public String getNextContractStatus() {
        return nextContractStatus;
    }

    public void setNextContractStatus(String nextContractStatus) {
        this.nextContractStatus = nextContractStatus == null ? null : nextContractStatus.trim();
    }

    public String getNextContractProcess() {
        return nextContractProcess;
    }

    public void setNextContractProcess(String nextContractProcess) {
        this.nextContractProcess = nextContractProcess == null ? null : nextContractProcess.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex == null ? null : annex.trim();
    }

    public Double getAccumulatedPaymentAmount() {
        return accumulatedPaymentAmount;
    }

    public void setAccumulatedPaymentAmount(Double accumulatedPaymentAmount) {
        this.accumulatedPaymentAmount = accumulatedPaymentAmount;
    }

    public String getPayProportion() {
        return payProportion;
    }

    public void setPayProportion(String payProportion) {
        this.payProportion = payProportion == null ? null : payProportion.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", name=").append(name);
        sb.append(", partyAName=").append(partyAName);
        sb.append(", belongDepartment=").append(belongDepartment);
        sb.append(", budgetAccount=").append(budgetAccount);
        sb.append(", maintenanceType=").append(maintenanceType);
        sb.append(", contractAmount=").append(contractAmount);
        sb.append(", contractStatus=").append(contractStatus);
        sb.append(", signTime=").append(signTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", endTimeRemark=").append(endTimeRemark);
        sb.append(", firstCheckTime=").append(firstCheckTime);
        sb.append(", lastCheckTime=").append(lastCheckTime);
        sb.append(", budgetYear=").append(budgetYear);
        sb.append(", signNo=").append(signNo);
        sb.append(", contractExecutor=").append(contractExecutor);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", manufacturerAccount=").append(manufacturerAccount);
        sb.append(", manufacturerManager=").append(manufacturerManager);
        sb.append(", manufacturerContacts=").append(manufacturerContacts);
        sb.append(", manufacturerContactWay=").append(manufacturerContactWay);
        sb.append(", nextContractNo=").append(nextContractNo);
        sb.append(", nextContractStatus=").append(nextContractStatus);
        sb.append(", nextContractProcess=").append(nextContractProcess);
        sb.append(", remark=").append(remark);
        sb.append(", annex=").append(annex);
        sb.append(", accumulatedPaymentAmount=").append(accumulatedPaymentAmount);
        sb.append(", payProportion=").append(payProportion);
        sb.append(", creator=").append(creator);
        sb.append(", owner=").append(owner);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}