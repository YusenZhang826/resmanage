package com.clic.ccdbaas.service;

import com.clic.ccdbaas.dao.EquipmentContractMapper;
import com.clic.ccdbaas.entity.EquipmentContract;
import com.clic.ccdbaas.entity.SysUser;
import com.clic.ccdbaas.model.AjaxResult;
import com.clic.ccdbaas.utils.BusinessException;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.config.RuoYiConfig;
import com.clic.ccdbaas.utils.file.FileUploadUtils;
import com.clic.ccdbaas.utils.file.MimeTypeUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.apache.kafka.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/7/24 16:23
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class EquipmentContractService {
    @Autowired
    private EquipmentContractMapper equipmentContractMapper;
    @Value("${contract.prefix.url}")
    private String contractLinkPrefix;

    private static final Logger logger = LoggerFactory.getLogger(EquipmentContractService.class);


    /**
     * 获取电子设备合同列表
     * @param equipmentContract
     * @return
     */
    public List<EquipmentContract>getAllEquipmentContract(EquipmentContract equipmentContract){
        return equipmentContractMapper.getAllInstance(equipmentContract);
    }

    /**
     * 更新设备信息
     * @param equipmentContract
     */
    public void updateEquipmentContract(EquipmentContract equipmentContract){
        equipmentContractMapper.updateByPrimaryKeySelective(equipmentContract);
    }

    /**
     * 新增设备信息
     * @param equipmentContract
     */
    public void addEquipmentContract(EquipmentContract equipmentContract){
        equipmentContractMapper.insert(equipmentContract);
    }

    /**
     * 批量删除设备信息
     * @param
     */
    public void deleteEquipmentContract(List  resIdArr){

        equipmentContractMapper.batchDelete(resIdArr);
    }

    /**
     * 上传合同内容
     * @param resId
     * @param file
     */
     public String uploadContract(String resId,MultipartFile file){
         String linkUrl = null;
         if (!file.isEmpty())
         {

             try {
                 linkUrl = FileUploadUtils.upload(RuoYiConfig.geContractPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                 linkUrl = contractLinkPrefix +linkUrl;
             } catch (IOException e) {
                 e.printStackTrace();
             }
             EquipmentContract equipmentContract = new EquipmentContract();
             equipmentContract.setResId(resId);
             equipmentContract.setLinkUrl(linkUrl);
             equipmentContractMapper.updateByPrimaryKeySelective(equipmentContract);

         }
         return linkUrl;
     }


    /**
     * 导入设备合同数据
     *
     * @param equipmentContractList 设备合同列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */

    public String importEquipmentContract(List<EquipmentContract> equipmentContractList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(equipmentContractList) || equipmentContractList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (EquipmentContract equipmentContract : equipmentContractList)
        {
            try
            {
                // 验证是否存在这个合同
                List<EquipmentContract> allInstance = equipmentContractMapper.getAllInstance(equipmentContract);
                if (allInstance.size() == 0)
                {
                    String resId = IdUtils.generatedUUID();
                    equipmentContract.setResId(resId);
                    equipmentContractMapper.insert(equipmentContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + equipmentContract.getContractName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {

                    equipmentContractMapper.updateByPrimaryKey(equipmentContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、合同 " + equipmentContract.getContractName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、合同 " + equipmentContract.getContractName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、合同 " + equipmentContract.getContractName() + " 导入失败：";
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
