package com.clic.ccdbaas.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AllocateStatus {
    TEMPALLOCATED("临时分配"), RECYCLED("已回收"), ALLOCATED("已分配"), AVAILABLE("未分配"), RESERVE("保留"), BOOKING("预约");
    @EnumValue
    @JsonValue
    private final String name;

    AllocateStatus(String name) {
        this.name = name;
    }


    public String getValue() {
        return this.name;
    }


    public String toString() {
        return this.name;
    }
}
