package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("t_relation_db")
public class RelationDb implements Serializable {
    private String resid;

    private String agentid;

    private String hostname;

    private String paramValue;

    private String displayip;

    private String name;

    private String version;

    private String binpath;

    private String configpath;

    private Date createtime;

    private Date udpatetime;

    private Integer port;

    private String prototype;

    private String bindip;

    private String confpath;

    private String logpath;

    private String datadir;

    private String user;

    private String status;

    private String dbName;

    private int deployEnv;

    private static final long serialVersionUID = 1L;

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid == null ? null : resid.trim();
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid == null ? null : agentid.trim();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public String getDisplayip() {
        return displayip;
    }

    public void setDisplayip(String displayip) {
        this.displayip = displayip == null ? null : displayip.trim();
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

    public String getBinpath() {
        return binpath;
    }

    public void setBinpath(String binpath) {
        this.binpath = binpath == null ? null : binpath.trim();
    }

    public String getConfigpath() {
        return configpath;
    }

    public void setConfigpath(String configpath) {
        this.configpath = configpath == null ? null : configpath.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUdpatetime() {
        return udpatetime;
    }

    public void setUdpatetime(Date udpatetime) {
        this.udpatetime = udpatetime;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPrototype() {
        return prototype;
    }

    public void setPrototype(String prototype) {
        this.prototype = prototype == null ? null : prototype.trim();
    }

    public String getBindip() {
        return bindip;
    }

    public void setBindip(String bindip) {
        this.bindip = bindip == null ? null : bindip.trim();
    }

    public String getConfpath() {
        return confpath;
    }

    public void setConfpath(String confpath) {
        this.confpath = confpath == null ? null : confpath.trim();
    }

    public String getLogpath() {
        return logpath;
    }

    public void setLogpath(String logpath) {
        this.logpath = logpath == null ? null : logpath.trim();
    }

    public String getDatadir() {
        return datadir;
    }

    public void setDatadir(String datadir) {
        this.datadir = datadir == null ? null : datadir.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resid=").append(resid);
        sb.append(", agentid=").append(agentid);
        sb.append(", hostname=").append(hostname);
        sb.append(", displayip=").append(displayip);
        sb.append(", name=").append(name);
        sb.append(", version=").append(version);
        sb.append(", binpath=").append(binpath);
        sb.append(", configpath=").append(configpath);
        sb.append(", createtime=").append(createtime);
        sb.append(", udpatetime=").append(udpatetime);
        sb.append(", port=").append(port);
        sb.append(", prototype=").append(prototype);
        sb.append(", bindip=").append(bindip);
        sb.append(", confpath=").append(confpath);
        sb.append(", logpath=").append(logpath);
        sb.append(", datadir=").append(datadir);
        sb.append(", user=").append(user);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}