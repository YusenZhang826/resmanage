package com.clic.ccdbaas.entity;

import com.clic.ccdbaas.utils.sql.AdvancedSearch;

import java.util.Date;
import java.util.List;

public class ComplianceRule {
    private String resId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    private String name;
    private String categoryName;
    private String categoryId;
    private int status;
    private String statusStr;
    private List<Integer> statusArr;
    private int riskLevel;
    private int triggerMechanism;
    private String execPeriod;
    private String resourceTypeId;
    private Integer runningStatus;
    private Integer lastExecResult;

    public Date getLastExecTime() {
        return lastExecTime;
    }

    public void setLastExecTime(Date lastExecTime) {
        this.lastExecTime = lastExecTime;
    }

    private Date lastModified;
    private Date lastExecTime;
    private long total;
    private long compliance;
    private long incompliance;
    private String description;
    private String maintenanceGuidance;
    private Date createTime;
    private String createdBy;
    private String lastUpdateBy;
    private int checkType;
    private String conditionOp;
    private AdvancedSearch advancedSearch;
    private String executeStatement;
    private String resourceName;

    private Integer type;

    private Integer searchType;
    private Integer configFlag;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(Integer runningStatus) {
        this.runningStatus = runningStatus;
    }

    public Integer getLastExecResult() {
        return lastExecResult;
    }

    public void setLastExecResult(Integer lastExecResult) {
        this.lastExecResult = lastExecResult;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Integer> getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(List<Integer> statusArr) {
        this.statusArr = statusArr;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public int getTriggerMechanism() {
        return triggerMechanism;
    }

    public void setTriggerMechanism(int triggerMechanism) {
        this.triggerMechanism = triggerMechanism;
    }

    public String getExecPeriod() {
        return execPeriod;
    }

    public void setExecPeriod(String execPeriod) {
        this.execPeriod = execPeriod;
    }

    public String getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(String resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public long getTotal() {
        return total;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCompliance() {
        return compliance;
    }

    public void setCompliance(long compliance) {
        this.compliance = compliance;
    }

    public long getIncompliance() {
        return incompliance;
    }

    public void setIncompliance(long incompliance) {
        this.incompliance = incompliance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaintenanceGuidance() {
        return maintenanceGuidance;
    }

    public void setMaintenanceGuidance(String maintenanceGuidance) {
        this.maintenanceGuidance = maintenanceGuidance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(int checkType) {
        this.checkType = checkType;
    }

    public String getConditionOp() {
        return conditionOp;
    }

    public void setConditionOp(String conditionOp) {
        this.conditionOp = conditionOp;
    }

    public AdvancedSearch getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(AdvancedSearch advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

    public String getExecuteStatement() {
        return executeStatement;
    }

    public void setExecuteStatement(String executeStatement) {
        this.executeStatement = executeStatement;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getConfigFlag() {
        return configFlag;
    }

    public void setConfigFlag(Integer configFlag) {
        this.configFlag = configFlag;
    }
}
