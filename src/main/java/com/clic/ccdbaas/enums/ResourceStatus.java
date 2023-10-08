package com.clic.ccdbaas.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ResourceStatus {
    RUNNING("运行"), RESERVE("库存"), ALLOCATED("已分配"), AVAILABLE("已上架待分配"), REMOVED("下架"), SCRAPPED("报废"), OFFLINE("下线"), CONSTRUCTION("工程");
    @EnumValue
    @JsonValue
    private final String name;

    ResourceStatus(String name) {
        this.name = name;
    }

    public static ResourceStatus getEnum(String name) {
        for (ResourceStatus resourceStatus : ResourceStatus.values()) {
            if (resourceStatus.getName().equals(name)) {
                return resourceStatus;
            }
        }
        return null;
    }

    public String getValue() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
