package com.clic.ccdbaas.entity.kafka;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReserveServerKafka {

    private String sn;
    private String resAllocationCode;
    private String allocateStatus;
    private String cpuArch;
    private String physicalPosition;
    private String assetBelong;
    private String allocateBMCIP;
    private int cpuSize;
    private int cpuCount;
    private int memSize;
    private int memCount;
    private String resId;
    private String shelfPosition;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getResAllocationCode() {
        return resAllocationCode;
    }

    public void setResAllocationCode(String resAllocationCode) {
        this.resAllocationCode = resAllocationCode;
    }

    public String getAllocateStatus() {
        return allocateStatus;
    }

    public void setAllocateStatus(String allocateStatus) {
        this.allocateStatus = allocateStatus;
    }

    public String getCpuArch() {
        return cpuArch;
    }

    public void setCpuArch(String cpuArch) {
        this.cpuArch = cpuArch;
    }

    public String getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(String physicalPosition) {
        this.physicalPosition = physicalPosition;
    }

    public String getAssetBelong() {
        return assetBelong;
    }

    public void setAssetBelong(String assetBelong) {
        this.assetBelong = assetBelong;
    }

    public String getAllocateBMCIP() {
        return allocateBMCIP;
    }

    public void setAllocateBMCIP(String allocateBMCIP) {
        this.allocateBMCIP = allocateBMCIP;
    }

    public int getCpuSize() {
        return cpuSize;
    }

    public void setCpuSize(int cpuSize) {
        this.cpuSize = cpuSize;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getMemSize() {
        return memSize;
    }

    public void setMemSize(int memSize) {
        this.memSize = memSize;
    }

    public int getMemCount() {
        return memCount;
    }

    public void setMemCount(int memCount) {
        this.memCount = memCount;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getShelfPosition() {
        return shelfPosition;
    }

    public void setShelfPosition(String shelfPosition) {
        this.shelfPosition = shelfPosition;
    }
}
