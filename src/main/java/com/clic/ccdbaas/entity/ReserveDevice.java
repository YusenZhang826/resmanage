package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_reserve_device_view")
public class ReserveDevice {
    private String resId;
    private String name;
    private Date createTime;
    private Date updateTime;
    private String ip;
    private String shelfPosition;
    private String physicalPosition;
    private String cpuManufacturer;
    private String warrantyRemark;
    private String warrantyManufacturer;
    private Date warrantyEndTime;
    private Date warrantyStartTime;
    private String warrantyContract;
    private String purchaseContract;
    private String deviceModel;
    private String manufacturer;
    private String standTeam;
    private String resourceStatus;
    private String sn;
    private String type;
}
