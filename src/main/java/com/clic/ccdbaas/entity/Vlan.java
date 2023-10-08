package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_vlan_plan")
public class Vlan {
    private String resId;
    private String areaName;
    private String name;
    private String dataStatus;
    private String lastModified;
    private String dataSource;
    private String ipSegment;
    private String startIp;
    private String endIp;
    private String gatewayIp;
    private String remarks;
    // 用途大类
    private String useParType;
    // 用途子类
    private String useChiType;
    private String vlanId;
}
