package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@TableName(value = "t_object_storage")
@Data
public class ObjectStorage {
    private String bizRegionName;

    private String resId;
    private String name;

    public String getBizRegionName() {
        return bizRegionName;
    }

    public void setBizRegionName(String bizRegionName) {
        this.bizRegionName = bizRegionName;
    }

    private Date createDate;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(int objectNumber) {
        this.objectNumber = objectNumber;
    }

    public BigInteger getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(BigInteger usedSize) {
        this.usedSize = usedSize;
    }

    private int size;
    private int objectNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectStorage storage = (ObjectStorage) o;
        return size == storage.size && objectNumber == storage.objectNumber && Objects.equals(bizRegionName, storage.bizRegionName) && resId.equals(storage.resId) && Objects.equals(name, storage.name) && Objects.equals(createDate, storage.createDate) && Objects.equals(usedSize, storage.usedSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bizRegionName, resId, name, createDate, size, objectNumber, usedSize);
    }

    private BigInteger usedSize;
}
