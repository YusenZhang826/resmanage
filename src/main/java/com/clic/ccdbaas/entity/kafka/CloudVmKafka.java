package com.clic.ccdbaas.entity.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CloudVmKafka {

    private String resourceCode;
    private String belongProduct;
    private String ProductToken;
    private String appModName;
    private String appModToken;
    private String mainIp;
    private String sysAdministrator;
    private String stakeholder;
    private String sysAdminBackup;
    private String sysAdminTeam;
    private String deployAdminA;

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getBelongProduct() {
        return belongProduct;
    }

    public void setBelongProduct(String belongProduct) {
        this.belongProduct = belongProduct;
    }

    public String getProductToken() {
        return ProductToken;
    }

    public void setProductToken(String productToken) {
        ProductToken = productToken;
    }

    public String getAppModName() {
        return appModName;
    }

    public void setAppModName(String appModName) {
        this.appModName = appModName;
    }

    public String getAppModToken() {
        return appModToken;
    }

    public void setAppModToken(String appModToken) {
        this.appModToken = appModToken;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    public String getSysAdministrator() {
        return sysAdministrator;
    }

    public void setSysAdministrator(String sysAdministrator) {
        this.sysAdministrator = sysAdministrator;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getSysAdminBackup() {
        return sysAdminBackup;
    }

    public void setSysAdminBackup(String sysAdminBackup) {
        this.sysAdminBackup = sysAdminBackup;
    }

    public String getSysAdminTeam() {
        return sysAdminTeam;
    }

    public void setSysAdminTeam(String sysAdminTeam) {
        this.sysAdminTeam = sysAdminTeam;
    }

    public String getDeployAdminA() {
        return deployAdminA;
    }

    public void setDeployAdminA(String deployAdminA) {
        this.deployAdminA = deployAdminA;
    }
}
