package com.clic.ccdbaas.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * description:excel导出库存设备模板
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class StorageDevice {

    @ExcelProperty("昵称")
    @ColumnWidth(10)
    private String name;
    @ExcelProperty("原ID")
    @ColumnWidth(20)
    private String id;
    @ExcelProperty("设备分类")
    @ColumnWidth(20)
    private String deviceAssort;
    @ExcelProperty("序列号")
    @ColumnWidth(20)
    private String sn;
    @ExcelProperty("分配状态")
    @ColumnWidth(20)
    private String allocateStatus;
    @ExcelProperty("维护部门")
    @ColumnWidth(20)
    private String standTeam;
    @ExcelProperty("设备厂商")
    @ColumnWidth(20)
    private String manufacturer;
    @ExcelProperty("设备型号")
    @ColumnWidth(20)
    private String deviceModel;
    @ExcelProperty("资产状态")
    @ColumnWidth(20)
    private String assetStatus;
    @ExcelProperty("资产归属")
    @ColumnWidth(20)
    private String assetBelong;
    @ExcelProperty("SAP号")
    @ColumnWidth(20)
    private String SAPNum;
    @ExcelProperty("新SAP号")
    @ColumnWidth(20)
    private String NewSAPNum;
    @ExcelProperty("采购合同号")
    @ColumnWidth(20)
    private String purchaseContract;
    @ExcelProperty("维保合同号")
    @ColumnWidth(20)
    private String warrantyContract;
    @ExcelProperty("维保开始日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date warrantyStartTime;
    @ExcelProperty("维保结束日期")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date warrantyEndTime;
    @ExcelProperty("维保厂商")
    @ColumnWidth(20)
    private String warrantyManufacturer;
    @ExcelProperty("维保备注")
    @ColumnWidth(20)
    private String warrantyRemark;
    @ExcelProperty("CPU架构")
    @ColumnWidth(20)
    private String cpuArch;
    @ExcelProperty("CPU厂商")
    @ColumnWidth(20)
    private String cpuManufacturer;
    @ExcelProperty("CPU型号")
    @ColumnWidth(20)
    private String cpuModel;
    @ExcelProperty("物理CPU个数(个)")
    @ColumnWidth(20)
    private String cpuCount;
    @ExcelProperty("单个CPU核数(C)")
    @ColumnWidth(20)
    private String cpuSize;
    @ExcelProperty("内存型号")
    @ColumnWidth(20)
    private String memModel;
    @ExcelProperty("内存数量(个)")
    @ColumnWidth(20)
    private String memCount;
    @ExcelProperty("单个内存容量(GB)")
    @ColumnWidth(20)
    private String memSize;
    @ExcelProperty("硬盘1型号(系统盘)")
    @ColumnWidth(20)
    private String disk1Model;
    @ExcelProperty("硬盘1数量(个)")
    @ColumnWidth(20)
    private String disk1Count;
    @ExcelProperty("硬盘1大小(GB)")
    @ColumnWidth(20)
    private String disk1Size;
    @ExcelProperty("硬盘2型号(数据盘)")
    @ColumnWidth(20)
    private String disk2Model;
    @ExcelProperty("硬盘2数量(个)")
    @ColumnWidth(20)
    private String disk2Count;
    @ExcelProperty("硬盘2大小(GB)")
    @ColumnWidth(20)
    private String disk2Size;
    @ExcelProperty("硬盘3型号(其他盘)")
    @ColumnWidth(20)
    private String disk3Model;
    @ExcelProperty("硬盘3数量(个)")
    @ColumnWidth(20)
    private String disk3Count;
    @ExcelProperty("硬盘3大小(GB)")
    @ColumnWidth(20)
    private String disk3Size;
    @ExcelProperty("HBA卡型号")
    @ColumnWidth(20)
    private String HBAModel;
    @ExcelProperty("HBA卡数量")
    @ColumnWidth(20)
    private String HBACount;
    @ExcelProperty("物理网卡型号")
    @ColumnWidth(20)
    private String netCardModel;
    @ExcelProperty("物理网卡数量")
    @ColumnWidth(20)
    private String netCardNum;
    @ExcelProperty("RAID卡型号")
    @ColumnWidth(20)
    private String raidModel;
    @ExcelProperty("RAID卡数量")
    @ColumnWidth(20)
    private String raidCount;
    @ExcelProperty("PCIE卡型号")
    @ColumnWidth(20)
    private String pcieModel;
    @ExcelProperty("PCIE卡数量")
    @ColumnWidth(20)
    private String pcieCount;
    @ExcelProperty("电源功率")
    @ColumnWidth(20)
    private String endIp;
    @ExcelProperty("电源数量")
    @ColumnWidth(20)
    private String powerNum;
    @ExcelProperty("风扇数量")
    @ColumnWidth(20)
    private String fanNum;
    @ExcelProperty("创建者")
    @ColumnWidth(20)
    private String createor;
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date createTime;
    @ExcelProperty("最后更新时间")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date lastUpdateTime;
    @ExcelProperty("物理位置")
    @ColumnWidth(20)
    private String physicalPosition;
    @ExcelProperty("上架位置")
    @ColumnWidth(20)
    private String shelfPosition;
    @ExcelProperty("分配BMC IP")
    @ColumnWidth(20)
    private String allocateBMCIP;

}
