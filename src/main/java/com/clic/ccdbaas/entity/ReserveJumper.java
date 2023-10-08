package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.model.BaseEntity;
import com.clic.ccdbaas.utils.excel.Excel;

import java.util.Date;

@TableName(value = "t_reserve_jumper")
public class ReserveJumper extends BaseEntity {
    @TableId
    private String resId;

    @ExcelProperty("服务器")
    @Excel(name = "服务器")
    private String server;
    @ExcelProperty("服务器端口")
    @Excel(name = "服务器端口")
    private String portServer;
    @ExcelProperty("配线架1")
    @Excel(name = "配线架1")
    private String portPxj1;
    @ExcelProperty("配线架2")
    @Excel(name = "配线架2")
    private String portPxj2;
    @ExcelProperty("配线架3")
    @Excel(name = "配线架3")
    private String portPxj3;
    @ExcelProperty("配线架4")
    @Excel(name = "配线架4")
    private String portPxj4;
    @ExcelProperty("交换机")
    @Excel(name = "交换机")
    private String switcher;
    @ExcelProperty("交换机端口")
    @Excel(name = "交换机端口")
    private String portSwitch;
    @ExcelProperty("速率")
    @Excel(name = "速率")
    private String speed;
    @ExcelProperty("绑定模式")
    @Excel(name = "绑定模式")
    private String bond;
    @ExcelProperty("端口模式")
    @Excel(name = "端口模式")
    private String type;
    @ExcelProperty("vlan")
    @Excel(name = "vlan")
    private String vlan;
    @ExcelProperty("描述")
    @Excel(name = "描述")
    private String description;
    @ExcelProperty("备注")
    @Excel(name = "备注")
    private String comment;
    @ExcelProperty("交换机类型")
    @Excel(name = "交换机类型")
    private String category;
    @ExcelProperty("状态")
    @Excel(name = "状态")
    private String status;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server == null ? null : server.trim();
    }

    public String getPortServer() {
        return portServer;
    }

    public void setPortServer(String portServer) {
        this.portServer = portServer == null ? null : portServer.trim();
    }

    public String getPortPxj1() {
        return portPxj1;
    }

    public void setPortPxj1(String portPxj1) {
        this.portPxj1 = portPxj1 == null ? null : portPxj1.trim();
    }

    public String getPortPxj2() {
        return portPxj2;
    }

    public void setPortPxj2(String portPxj2) {
        this.portPxj2 = portPxj2 == null ? null : portPxj2.trim();
    }

    public String getPortPxj3() {
        return portPxj3;
    }

    public void setPortPxj3(String portPxj3) {
        this.portPxj3 = portPxj3 == null ? null : portPxj3.trim();
    }

    public String getPortPxj4() {
        return portPxj4;
    }

    public void setPortPxj4(String portPxj4) {
        this.portPxj4 = portPxj4 == null ? null : portPxj4.trim();
    }

    public String getSwitcher() {
        return switcher;
    }

    public void setSwitcher(String switcher) {
        this.switcher = switcher == null ? null : switcher.trim();
    }

    public String getPortSwitch() {
        return portSwitch;
    }

    public void setPortSwitch(String portSwitch) {
        this.portSwitch = portSwitch == null ? null : portSwitch.trim();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond == null ? null : bond.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan == null ? null : vlan.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", server=").append(server);
        sb.append(", portServer=").append(portServer);
        sb.append(", portPxj1=").append(portPxj1);
        sb.append(", portPxj2=").append(portPxj2);
        sb.append(", portPxj3=").append(portPxj3);
        sb.append(", portPxj4=").append(portPxj4);
        sb.append(", switcher=").append(switcher);
        sb.append(", portSwitch=").append(portSwitch);
        sb.append(", speed=").append(speed);
        sb.append(", bond=").append(bond);
        sb.append(", type=").append(type);
        sb.append(", vlan=").append(vlan);
        sb.append(", description=").append(description);
        sb.append(", comment=").append(comment);
        sb.append(", category=").append(category);
        sb.append(", status=").append(status);
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