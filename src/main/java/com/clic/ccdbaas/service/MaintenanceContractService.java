package com.clic.ccdbaas.service;

import com.clic.ccdbaas.dao.MaintenanceContractMapper;
import com.clic.ccdbaas.dao.MaintenanceContractMapper;
import com.clic.ccdbaas.entity.MaintenanceContract;
import com.clic.ccdbaas.utils.BusinessException;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.config.RuoYiConfig;
import com.clic.ccdbaas.utils.file.FileUploadUtils;
import com.clic.ccdbaas.utils.file.MimeTypeUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
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
public class MaintenanceContractService {
    @Autowired
    private MaintenanceContractMapper maintenanceContractMapper;
    @Value("${contract.prefix.url}")
    private String contractLinkPrefix;

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceContractService.class);


    /**
     * 获取电子维保合同列表
     * @param maintenanceContract
     * @return
     */
    public List<MaintenanceContract>getAllMaintenanceContract(MaintenanceContract maintenanceContract){
        return maintenanceContractMapper.getAllInstance(maintenanceContract);
    }

    /**
     * 更新维保信息
     * @param maintenanceContract
     */
    public void updateMaintenanceContract(MaintenanceContract maintenanceContract){
        maintenanceContractMapper.updateByPrimaryKeySelective(maintenanceContract);
    }

    /**
     * 新增维保信息
     * @param maintenanceContract
     */
    public void addMaintenanceContract(MaintenanceContract maintenanceContract){
        maintenanceContractMapper.insert(maintenanceContract);
    }

    /**
     * 批量删除维保信息
     * @param
     */
    public void deleteMaintenanceContract(List  resIdArr){

        maintenanceContractMapper.batchDelete(resIdArr);
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
             MaintenanceContract maintenanceContract = new MaintenanceContract();
             maintenanceContract.setResId(resId);
             maintenanceContract.setLinkUrl(linkUrl);
             maintenanceContractMapper.updateByPrimaryKeySelective(maintenanceContract);

         }
         return linkUrl;
     }


    /**
     * 导入维保合同数据
     *
     * @param maintenanceContractList 维保合同列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */

    public String importMaintenanceContract(List<MaintenanceContract> maintenanceContractList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(maintenanceContractList) || maintenanceContractList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (MaintenanceContract maintenanceContract : maintenanceContractList)
        {
            try
            {
                // 验证是否存在这个合同
                List<MaintenanceContract> allInstance = maintenanceContractMapper.getAllInstance(maintenanceContract);
                if (allInstance.size() == 0)
                {
                    String resId = IdUtils.generatedUUID();
                    maintenanceContract.setResId(resId);
                    maintenanceContractMapper.insert(maintenanceContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、维保合同 " + maintenanceContract.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {

                    maintenanceContractMapper.updateByPrimaryKey(maintenanceContract);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、维保合同 " + maintenanceContract.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、维保合同 " + maintenanceContract.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、维保合同 " + maintenanceContract.getName() + " 导入失败：";
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
