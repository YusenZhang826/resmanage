package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
@TableName("t_app_info")
public class AppInfo implements Serializable {
    private String resId;

    private String agentId;

    private String hostname;

    private String displayIp;

    private String connectionIp;

    private String externalIp;

    private String internalIp;

    private String name;

    private String version;

    private Date createTime;

    private Date udpateTime;

    private Integer bizGroupIp;

    private String bizGroup;

    private String bindIp;

    private String uname;

    private String hostTagList;

    private String remark;

    private String processes;

    private String binPath;

    private String configPath;

    private Integer deployEnv;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public String getDisplayIp() {
        return displayIp;
    }

    public void setDisplayIp(String displayIp) {
        this.displayIp = displayIp == null ? null : displayIp.trim();
    }

    public String getConnectionIp() {
        return connectionIp;
    }

    public void setConnectionIp(String connectionIp) {
        this.connectionIp = connectionIp == null ? null : connectionIp.trim();
    }

    public String getExternalIp() {
        return externalIp;
    }

    public void setExternalIp(String externalIp) {
        this.externalIp = externalIp == null ? null : externalIp.trim();
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp == null ? null : internalIp.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }

    public Integer getBizGroupIp() {
        return bizGroupIp;
    }

    public void setBizGroupIp(Integer bizGroupIp) {
        this.bizGroupIp = bizGroupIp;
    }

    public String getBizGroup() {
        return bizGroup;
    }

    public void setBizGroup(String bizGroup) {
        this.bizGroup = bizGroup == null ? null : bizGroup.trim();
    }

    public String getBindIp() {
        return bindIp;
    }

    public void setBindIp(String bindIp) {
        this.bindIp = bindIp == null ? null : bindIp.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getHostTagList() {
        return hostTagList;
    }

    public void setHostTagList(String hostTagList) {
        this.hostTagList = hostTagList == null ? null : hostTagList.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProcesses() {
        return processes;
    }

    public void setProcesses(String processes) {
        this.processes = processes == null ? null : processes.trim();
    }

    public String getBinPath() {
        return binPath;
    }

    public void setBinPath(String binPath) {
        this.binPath = binPath == null ? null : binPath.trim();
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath == null ? null : configPath.trim();
    }

    public Integer getDeployEnv() {
        return deployEnv;
    }

    public void setDeployEnv(Integer deployEnv) {
        this.deployEnv = deployEnv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", agentId=").append(agentId);
        sb.append(", hostname=").append(hostname);
        sb.append(", displayIp=").append(displayIp);
        sb.append(", connectionIp=").append(connectionIp);
        sb.append(", externalIp=").append(externalIp);
        sb.append(", internalIp=").append(internalIp);
        sb.append(", name=").append(name);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", udpateTime=").append(udpateTime);
        sb.append(", bizGroupIp=").append(bizGroupIp);
        sb.append(", bizGroup=").append(bizGroup);
        sb.append(", bindIp=").append(bindIp);
        sb.append(", uname=").append(uname);
        sb.append(", hostTagList=").append(hostTagList);
        sb.append(", remark=").append(remark);
        sb.append(", processes=").append(processes);
        sb.append(", binPath=").append(binPath);
        sb.append(", configPath=").append(configPath);
        sb.append(", deployEnv=").append(deployEnv);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}