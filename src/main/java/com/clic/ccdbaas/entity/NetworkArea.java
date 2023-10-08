package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@TableName("t_network_logic_area")
public class NetworkArea {
    @Excel(name = "ID")
    @ExcelProperty("ID")
    private String resId;
    @Excel(name = "用途描述")
    @ExcelProperty("用途描述")
    private String purposeDesc;
    @ExcelProperty("名称")
    @Excel(name = "名称")
    private String name;
    @ExcelProperty("类别")
    @Excel(name = "类别")
    private String category;
    @ExcelProperty("地理位置")
    @Excel(name = "地理位置")
    private String location;
    @ExcelProperty("最后修改时间")
    @Excel(name = "最后修改时间")
    private Date last_Modified;
    @ExcelProperty("部署环境")
    @Excel(name = "部署环境")
    private String deployEnv;
    @ExcelProperty("管理员")
    @Excel(name = "管理员")
    private String netAdministrator;
    @ExcelProperty("使用机构")
    @Excel(name = "使用机构")
    private String userOrg;
    private String instanceNum;
    private List<String> userOrgArray;

    public List<String> getUserOrgArray() {
        return userOrgArray;
    }

    public void setUserOrgArray(List<String> userOrgArray) {
        this.userOrgArray = userOrgArray;
    }

    public List<String> getDeployEnvArray() {
        return deployEnvArray;
    }

    public void setDeployEnvArray(List<String> deployEnvArray) {
        this.deployEnvArray = deployEnvArray;
    }

    private List<String> deployEnvArray;

    public String getInstanceNum() {
        return instanceNum;
    }

    public void setInstanceNum(String instanceNum) {
        this.instanceNum = instanceNum;
    }

    public String getVlanNum() {
        return vlanNum;
    }

    public void setVlanNum(String vlanNum) {
        this.vlanNum = vlanNum;
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

    private String vlanNum;
    private String groupFlag;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getPurposeDesc() {
        return purposeDesc;
    }

    public void setPurposeDesc(String purposeDesc) {
        this.purposeDesc = purposeDesc;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(Date last_Modified) {
        this.last_Modified = last_Modified;
    }

    public String getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(String deployEnv) {
        this.deployEnv = deployEnv;
    }

    public String getNetAdministrator() {
        return netAdministrator;
    }

    public void setNetAdministrator(String netAdministrator) {
        this.netAdministrator = netAdministrator;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }

}

