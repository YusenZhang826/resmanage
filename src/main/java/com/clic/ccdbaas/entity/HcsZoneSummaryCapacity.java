package com.clic.ccdbaas.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class HcsZoneSummaryCapacity implements Serializable {
    private String azoneId;

    private String dimensionType;

    private String azoneName;

    private String regionId;

    private String regionName;

    private Double totalCapacity;

    private Double usedCapacity;

    private Double freeCapacity;

    private BigDecimal capUsedRatio;

    private BigDecimal allocatedCapacity;

    private static final long serialVersionUID = 1L;

    public String getAzoneId() {
        return azoneId;
    }

    public void setAzoneId(String azoneId) {
        this.azoneId = azoneId == null ? null : azoneId.trim();
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType == null ? null : dimensionType.trim();
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

    public BigDecimal getCapUsedRatio() {
        return capUsedRatio;
    }

    public void setCapUsedRatio(BigDecimal capUsedRatio) {
        this.capUsedRatio = capUsedRatio;
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
        sb.append(", dimensionType=").append(dimensionType);
        sb.append(", azoneName=").append(azoneName);
        sb.append(", regionId=").append(regionId);
        sb.append(", regionName=").append(regionName);
        sb.append(", totalCapacity=").append(totalCapacity);
        sb.append(", usedCapacity=").append(usedCapacity);
        sb.append(", freeCapacity=").append(freeCapacity);
        sb.append(", capUsedRatio=").append(capUsedRatio);
        sb.append(", allocatedCapacity=").append(allocatedCapacity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}