package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;

import java.io.Serializable;
@TableName("t_vm_summary")
public class VmSummaryInfo implements Serializable {

  /*  云团名称	管理IP	宿主机数量	虚拟机数量	CPU总量(THz)	CPU剩余(THz)
    内存总量(TB)	内存剩余(TB)	存储总量(TB)	存储剩余(TB)	团队	集群管理员*/

    private Double VCDB_ID;
    @Excel(name = "管理IP")
    private String VC_IP;
    @Excel(name = "云团名称")
    private String VCDB_USAGE;
    @Excel(name = "宿主机数量")
    private Long HOST;
    @Excel(name = "虚拟机数量")
    private Long VM;
    @Excel(name = "CPU总量(THz)")
    private Double p_CPU;
    @Excel(name = "CPU剩余(THz)")
    private Double v_CPU;
    @Excel(name = "内存总量(TB)")
    private Double p_MEM;
    @Excel(name = "内存剩余(TB)")
    private Double v_MEM;
    @Excel(name = "存储总量(TB)")
    private Double CAPACITY;
    @Excel(name = "存储剩余(TB)")
    private Double FREE_SPACE;
    @Excel(name = "集群管理员")
    private String VC_ADMIN;
    @Excel(name = "团队")
    private String DEPARTMENT;
    private Integer VC_TYPE;

    private static final long serialVersionUID = 1L;

    public Double getVCDB_ID() {
        return VCDB_ID;
    }

    public void setVCDB_ID(Double VCDB_ID) {
        this.VCDB_ID = VCDB_ID;
    }

    public String getVC_IP() {
        return VC_IP;
    }

    public void setVC_IP(String VC_IP) {
        this.VC_IP = VC_IP == null ? null : VC_IP.trim();
    }

    public String getVCDB_USAGE() {
        return VCDB_USAGE;
    }

    public void setVCDB_USAGE(String VCDB_USAGE) {
        this.VCDB_USAGE = VCDB_USAGE == null ? null : VCDB_USAGE.trim();
    }

    public Long getHOST() {
        return HOST;
    }

    public void setHOST(Long HOST) {
        this.HOST = HOST;
    }

    public Long getVM() {
        return VM;
    }

    public void setVM(Long VM) {
        this.VM = VM;
    }

    public Double getP_CPU() {
        return p_CPU;
    }

    public void setP_CPU(Double p_CPU) {
        this.p_CPU = p_CPU;
    }

    public Double getV_CPU() {
        return v_CPU;
    }

    public void setV_CPU(Double v_CPU) {
        this.v_CPU = v_CPU;
    }

    public Double getP_MEM() {
        return p_MEM;
    }

    public void setP_MEM(Double p_MEM) {
        this.p_MEM = p_MEM;
    }

    public Double getV_MEM() {
        return v_MEM;
    }

    public void setV_MEM(Double v_MEM) {
        this.v_MEM = v_MEM;
    }

    public Double getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(Double CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public Double getFREE_SPACE() {
        return FREE_SPACE;
    }

    public void setFREE_SPACE(Double FREE_SPACE) {
        this.FREE_SPACE = FREE_SPACE;
    }

    public String getVC_ADMIN() {
        return VC_ADMIN;
    }

    public void setVC_ADMIN(String VC_ADMIN) {
        this.VC_ADMIN = VC_ADMIN == null ? null : VC_ADMIN.trim();
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT == null ? null : DEPARTMENT.trim();
    }

    public Integer getVC_TYPE() {
        return VC_TYPE;
    }

    public void setVC_TYPE(Integer VC_TYPE) {
        this.VC_TYPE = VC_TYPE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", VCDB_ID=").append(VCDB_ID);
        sb.append(", VC_IP=").append(VC_IP);
        sb.append(", VCDB_USAGE=").append(VCDB_USAGE);
        sb.append(", HOST=").append(HOST);
        sb.append(", VM=").append(VM);
        sb.append(", p_CPU=").append(p_CPU);
        sb.append(", v_CPU=").append(v_CPU);
        sb.append(", p_MEM=").append(p_MEM);
        sb.append(", v_MEM=").append(v_MEM);
        sb.append(", CAPACITY=").append(CAPACITY);
        sb.append(", FREE_SPACE=").append(FREE_SPACE);
        sb.append(", VC_ADMIN=").append(VC_ADMIN);
        sb.append(", DEPARTMENT=").append(DEPARTMENT);
        sb.append(", VC_TYPE=").append(VC_TYPE);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}