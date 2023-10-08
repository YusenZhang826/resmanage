package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.ContractDetailMapper;
import com.clic.ccdbaas.entity.ContractDetail;
import com.clic.ccdbaas.entity.ReserveDevice;
import com.clic.ccdbaas.utils.BusinessException;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContractDetailService {
    @Autowired
    ContractDetailMapper contractDetailMapper;
    private static final Logger logger = LoggerFactory.getLogger(ContractDetailService.class);


    @Transactional(rollbackFor = Exception.class)
    public void addContractDetail(ContractDetail contractDetail) {
        contractDetailMapper.insertSelective(contractDetail);
    }

    @Transactional
    public void deleteContractDetailByIds(List<String> ids) {
        for (String id : ids) {
            contractDetailMapper.deleteByPrimaryKey(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateContractDetailById(ContractDetail contractDetail) {
        String id = contractDetail.getResId();
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("id为必填项");
        }

        ContractDetail temp = contractDetailMapper.selectByPrimaryKey(id);
        if (temp == null) {
            throw new RuntimeException(String.format("id为%s的Contract不存在", id));
        }
        contractDetailMapper.updateByPrimaryKeySelective(contractDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateContractDetailByIds(List<ContractDetail> contractDetailList) {
        for (ContractDetail contractDetail : contractDetailList) {
            updateContractDetailById(contractDetail);
        }
    }

    public ContractDetail getContractDetailById(String resId) {
        ContractDetail contractDetail = contractDetailMapper.selectByPrimaryKey(resId);
        return contractDetail;
    }

    public String importContractDetail(List<ContractDetail> contractDetailList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(contractDetailList) || contractDetailList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ContractDetail contractDetail : contractDetailList)
        {
            try
            {
                // 验证是否存在这个合同
                List<ContractDetail> allInstance = contractDetailMapper.getAllInstance(contractDetail);

                if (allInstance.size() == 0)
                {
                    String resId = IdUtils.generatedUUID();
                    contractDetail.setResId(resId);
                    contractDetailMapper.insert(contractDetail);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + contractDetail.getProcureBatch() + " 导入成功");
                }
                else if (isUpdateSupport)
                {

                    contractDetailMapper.updateByPrimaryKey(contractDetail);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + contractDetail.getProcureBatch() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合同 " + contractDetail.getProcureBatch() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同 " + contractDetail.getProcureBatch() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                logger.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }




}
