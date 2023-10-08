package com.clic.ccdbaas.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@ExcelIgnoreUnannotated
@TableName("t_physical_tape")
public class PhysicalTape {
    @TableId(type = IdType.ASSIGN_UUID)
    private String resId;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("序列号")
    private String sn;
    @ExcelProperty("运行状态")
    private String resourceStatus;
    @ExcelProperty("设备品牌")
    private String manufacturer;
    @ExcelProperty("管理员")
    private String admins;
    @ExcelProperty("机房")
    private String physicalPosition;
    @ExcelProperty("机房位置")
    private String machinePosition;
    @ExcelProperty("设备型号")
    private String deviceModel;
    @ExcelProperty("购买合同")
    private String purchaseContract;
    @ExcelProperty("维保厂商")
    private String warrantyManufacturer;
    @ExcelProperty("维保合同")
    private String warrantyContract;
    @ExcelProperty("维保开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date warrantyStartTime;
    @ExcelProperty("维保结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date warrantyEndTime;
    @ExcelProperty("槽位")
    private Integer slotCount;
    @ExcelProperty("IO槽位")
    private Integer ioSlotCount;
    @ExcelProperty("驱动器数量")
    private Integer driveCount;
    @ExcelProperty("管理IP")
    private String adminIps;
    @ExcelProperty("备注")
    private String remark;
    /**
     * 创建时间
     */
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date updateTime;
}
