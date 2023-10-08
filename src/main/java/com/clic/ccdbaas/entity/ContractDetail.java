package com.clic.ccdbaas.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.clic.ccdbaas.utils.excel.Excel;
import lombok.Data;

import java.io.Serializable;

//合同excel模板
@Data
@TableName(value = "t_contract_detail")
public class ContractDetail implements Serializable {
    @Excel(name = "资源id",type = Excel.Type.EXPORT)
    private String resId;
    @Excel(name = "IT资源配置项目（采购批次）")
    private String procureBatch;
    @Excel(name = "设备型号")
    private String equipmentModel;
    @Excel(name = "配置详情")
    private String deployDetail;
    @Excel(name = "数值")
    private Integer num;
    @Excel(name = "配置用途")
    private String deployUsage;
    @Excel(name = "信创情况")
    private String xinChuang;
    @Excel(name = "合同数量")
    private Integer contractCount;
    @Excel(name = "合同编号")
    private String contractNo;
    @Excel(name = "到货数量")
    private Integer arriveNum;
    @Excel(name = "已分配数量")
    private Integer allocatedNum;
    @Excel(name = "可分配数量")
    private Integer availableNum;
    @Excel(name = "云平台是否适配")
    private Boolean adapterCloudPlat;
    @Excel(name = "云平台适配进度")
    private Double adapterCloudProcess;
    @Excel(name = "到货详情")
    private String arriveDetail;
    @Excel(name = "分配详情")
    private String allocatedDetail;

    private static final long serialVersionUID = 1L;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getProcureBatch() {
        return procureBatch;
    }

    public void setProcureBatch(String procureBatch) {
        this.procureBatch = procureBatch == null ? null : procureBatch.trim();
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel == null ? null : equipmentModel.trim();
    }

    public String getDeployDetail() {
        return deployDetail;
    }

    public void setDeployDetail(String deployDetail) {
        this.deployDetail = deployDetail == null ? null : deployDetail.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDeployUsage() {
        return deployUsage;
    }

    public void setDeployUsage(String deployUsage) {
        this.deployUsage = deployUsage == null ? null : deployUsage.trim();
    }

    public String getXinChuang() {
        return xinChuang;
    }

    public void setXinChuang(String xinChuang) {
        this.xinChuang = xinChuang == null ? null : xinChuang.trim();
    }

    public Integer getContractCount() {
        return contractCount;
    }

    public void setContractCount(Integer contractCount) {
        this.contractCount = contractCount;
    }

    public String getContarctNo() {
        return contractNo;
    }

    public void setContarctNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public Integer getArriveNum() {
        return arriveNum;
    }

    public void setArriveNum(Integer arriveNum) {
        this.arriveNum = arriveNum;
    }

    public Integer getAllocatedNum() {
        return allocatedNum;
    }

    public void setAllocatedNum(Integer allocatedNum) {
        this.allocatedNum = allocatedNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Boolean getAdapterCloudPlat() {
        return adapterCloudPlat;
    }

    public void setAdapterCloudPlat(Boolean adapterCloudPlat) {
        this.adapterCloudPlat = adapterCloudPlat;
    }

    public Double getAdapterCloudProcess() {
        return adapterCloudProcess;
    }

    public void setAdapterCloudProcess(Double adapterCloudProcess) {
        this.adapterCloudProcess = adapterCloudProcess;
    }

    public String getArriveDetail() {
        return arriveDetail;
    }

    public void setArriveDetail(String arriveDetail) {
        this.arriveDetail = arriveDetail == null ? null : arriveDetail.trim();
    }

    public String getAllocatedDetail() {
        return allocatedDetail;
    }

    public void setAllocatedDetail(String allocatedDetail) {
        this.allocatedDetail = allocatedDetail == null ? null : allocatedDetail.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resId=").append(resId);
        sb.append(", procureBatch=").append(procureBatch);
        sb.append(", equipmentModel=").append(equipmentModel);
        sb.append(", deployDetail=").append(deployDetail);
        sb.append(", num=").append(num);
        sb.append(", deployUsage=").append(deployUsage);
        sb.append(", xinChuang=").append(xinChuang);
        sb.append(", contractCount=").append(contractCount);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", arriveNum=").append(arriveNum);
        sb.append(", allocatedNum=").append(allocatedNum);
        sb.append(", availableNum=").append(availableNum);
        sb.append(", adapterCloudPlat=").append(adapterCloudPlat);
        sb.append(", adapterCloudProcess=").append(adapterCloudProcess);
        sb.append(", arriveDetail=").append(arriveDetail);
        sb.append(", allocatedDetail=").append(allocatedDetail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}