package com.clic.ccdbaas.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class HcsZoneSummaryInfo implements Serializable {
    private String azoneId;

    private String azoneName;

    private String regionId;

    private String regionName;

    private Integer cpuUsed;

    private Double memoryUsed;

    private Double diskUsed;

    private Integer cpuTotal;

    private Double memoryTotal;

    private Double diskTotal;

    private BigDecimal cpuUsedRatio;

    private BigDecimal memoryUsedRatio;

    private BigDecimal diskUsedRatio;

    private Integer vmOnNum;

    private Integer vmOffNum;

    private Integer serverOnNum;

    private Integer serverOffNum;

    private Double totalCapacity;

    private Double usedCapacity;

    private Double freeCapacity;

    private BigDecimal allocatedCapacity;

    private static final long serialVersionUID = 1L;

    public String getAzoneId() {
        return azoneId;
    }

    public void setAzoneId(String azoneId) {
        this.azoneId = azoneId == null ? null : azoneId.trim();
    }

    public String getAzoneName() {
        return azoneName;
    }

    public void setAzoneName(String azoneName) {
        this.azoneName = azoneName == null ? null : azoneName.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getCpuUsed() {
        return cpuUsed;
    }

    public void setCpuUsed(Integer cpuUsed) {
        this.cpuUsed = cpuUsed;
    }

    public Double getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Double memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Double getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(Double diskUsed) {
        this.diskUsed = diskUsed;
    }

    public Integer getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(Integer cpuTotal) {
        this.cpuTotal = cpuTotal;
    }

    public Double getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Double memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Double getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(Double diskTotal) {
        this.diskTotal = diskTotal;
    }

    public BigDecimal getCpuUsedRatio() {
        return cpuUsedRatio;
    }

    public void setCpuUsedRatio(BigDecimal cpuUsedRatio) {
        this.cpuUsedRatio = cpuUsedRatio;
    }

    public BigDecimal getMemoryUsedRatio() {
        return memoryUsedRatio;
    }

    public void setMemoryUsedRatio(BigDecimal memoryUsedRatio) {
        this.memoryUsedRatio = memoryUsedRatio;
    }

    public BigDecimal getDiskUsedRatio() {
        return diskUsedRatio;
    }

    public void setDiskUsedRatio(BigDecimal diskUsedRatio) {
        this.diskUsedRatio = diskUsedRatio;
    }

    public Integer getVmOnNum() {
        return vmOnNum;
    }

    public void setVmOnNum(Integer vmOnNum) {
        this.vmOnNum = vmOnNum;
    }

    public Integer getVmOffNum() {
        return vmOffNum;
    }

    public void setVmOffNum(Integer vmOffNum) {
        this.vmOffNum = vmOffNum;
    }

    public Integer getServerOnNum() {
        return serverOnNum;
    }

    public void setServerOnNum(Integer serverOnNum) {
        this.serverOnNum = serverOnNum;
    }

    public Integer getServerOffNum() {
        return serverOffNum;
    }

    public void setServerOffNum(Integer serverOffNum) {
        this.serverOffNum = serverOffNum;
    }

    public Double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Double getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Double usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public Double getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(Double freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public BigDecimal getAllocatedCapacity() {
        return allocatedCapacity;
    }

    public void setAllocatedCapacity(BigDecimal allocatedCapacity) {
        this.allocatedCapacity = allocatedCapacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", azoneId=").append(azoneId);
        sb.append(", azoneName=").append(azoneName);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionName=").append(regionName);
        sb.append(", cpuUsed=").append(cpuUsed);
        sb.append(", memoryUsed=").append(memoryUsed);
        sb.append(", diskUsed=").append(diskUsed);
        sb.append(", cpuTotal=").append(cpuTotal);
        sb.append(", memoryTotal=").append(memoryTotal);
        sb.append(", diskTotal=").append(diskTotal);
        sb.append(", cpuUsedRatio=").append(cpuUsedRatio);
        sb.append(", memoryUsedRatio=").append(memoryUsedRatio);
        sb.append(", diskUsedRatio=").append(diskUsedRatio);
        sb.append(", vmOnNum=").append(vmOnNum);
        sb.append(", vmOffNum=").append(vmOffNum);
        sb.append(", serverOnNum=").append(serverOnNum);
        sb.append(", serverOffNum=").append(serverOffNum);
        sb.append(", totalCapacity=").append(totalCapacity);
        sb.append(", usedCapacity=").append(usedCapacity);
        sb.append(", freeCapacity=").append(freeCapacity);
        sb.append(", allocatedCapacity=").append(allocatedCapacity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}