package com.clic.ccdbaas.entity.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KafkaMessage {

    private String nativeId;
    private String title;
    private String moreInfo;
    private String belongProduct;
    private String productAbbr;
    private String productToken;
    private String type;
    private String projectName;
    private String projectAbbr;
    private String projectToken;
    private String resourceCode;
    private String networkArea;
    private String allocNum;
    private String allocMachineType;
    private String allocHostSpecs;
    private String allocIsITII;
    private String allocStorageType;
    private String allocStorageNum;
    private String allocOpinion;
    private String mainIp;
    private String reviewOpinion;
    private String createdDate;
    private String sysAdministrator;
    private String createdUser;
    private String status;
    private String workOrder;
    private String lastModified;
    private String manageNum;

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getNetworkArea() {
        return networkArea;
    }

    public void setNetworkArea(String networkArea) {
        this.networkArea = networkArea;
    }

    public String getAllocNum() {
        return allocNum;
    }

    public void setAllocNum(String allocNum) {
        this.allocNum = allocNum;
    }

    public String getAllocMachineType() {
        return allocMachineType;
    }

    public void setAllocMachineType(String allocMachineType) {
        this.allocMachineType = allocMachineType;
    }

    public String getAllocHostSpecs() {
        return allocHostSpecs;
    }

    public void setAllocHostSpecs(String allocHostSpecs) {
        this.allocHostSpecs = allocHostSpecs;
    }

    public String getAllocIsITII() {
        return allocIsITII;
    }

    public void setAllocIsITII(String allocIsITII) {
        this.allocIsITII = allocIsITII;
    }

    public String getAllocStorageType() {
        return allocStorageType;
    }

    public void setAllocStorageType(String allocStorageType) {
        this.allocStorageType = allocStorageType;
    }

    public String getAllocStorageNum() {
        return allocStorageNum;
    }

    public void setAllocStorageNum(String allocStorageNum) {
        this.allocStorageNum = allocStorageNum;
    }

    public String getAllocOpinion() {
        return allocOpinion;
    }

    public void setAllocOpinion(String allocOpinion) {
        this.allocOpinion = allocOpinion;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getManageNum() {
        return manageNum;
    }

    public void setManageNum(String manageNum) {
        this.manageNum = manageNum;
    }
}
