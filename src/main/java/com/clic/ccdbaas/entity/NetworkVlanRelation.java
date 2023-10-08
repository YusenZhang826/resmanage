package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NetworkVlanRelation {@ExcelProperty("ID")
    private String resId;
    private String areaId;
    private String vlanId;
    @ExcelProperty("网络逻辑区域名称")
    private String areaName;
    @ExcelProperty("vlan名称")
    private String vlanName;
}
