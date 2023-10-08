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
@TableName("t_vlan_plan")
public class VlanPlan {

    @ExcelProperty("ID")
    @Excel(name = "ID")
    private String resId;
    @ExcelProperty("名称【关键属性】")
    @Excel(name = "名称【关键属性】")
    private String name;
    @ExcelProperty("vlan ID")
    @Excel(name = "vlan ID")
    private String vlanId;
    @ExcelProperty("网络区域名称")
    @Excel(name = "网络区域名称")
    private String areaName;
    @ExcelProperty("用途子类")
    @Excel(name = "用途子类")
    private String userchiType;
    @ExcelProperty("用途大类")
    @Excel(name = "用途大类")
    private String userParType;
    @ExcelProperty("IP网段")
    @Excel(name = "IP网段")
    private String ipSegment;
    @ExcelProperty("起始IP")
    @Excel(name = "起始IP")
    private String startIp;
    @ExcelProperty("终止IP")
    @Excel(name = "终止IP")
    private String endIp;
    @ExcelProperty("网关IP")
    @Excel(name = "网关IP")
    private String gatewayIp;
    @ExcelProperty("掩码地址")
    @Excel(name = "掩码地址")
    private String subnetMask;
    @ExcelProperty("备注")
    @Excel(name = "备注")
    private String remarks;
    @ExcelProperty("最后修改时间")
    @Excel(name = "最后修改时间")
    private Date last_Modified;
    private int ipCount;
    private List<String> areaNameArray;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLast_Modified() {
        return last_Modified;
    }

    public void setLast_Modified(Date last_Modified) {
        this.last_Modified = last_Modified;
    }

    public String getIpSegment() {
        return ipSegment;
    }

    public void setIpSegment(String ipSegment) {
        this.ipSegment = ipSegment;
    }

    public String getStartIp() {
        return startIp;
    }

    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserParType() {
        return userParType;
    }

    public void setUserParType(String userParType) {
        this.userParType = userParType;
    }

    public String getUserchiType() {
        return userchiType;
    }

    public void setUserchiType(String userchiType) {
        this.userchiType = userchiType;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }
}
