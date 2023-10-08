package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.MaintenanceContract;
import com.clic.ccdbaas.service.MaintenanceContractService;
import com.clic.ccdbaas.service.MaintenanceContractService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/08/01 10:30
 * @email chenjianhua@bmsoft.com.cn
 */
@RestController
@RequestMapping(value = {"/v1/maintenanceContract"})
public class MaintenanceContractController extends CloudBaseController {

    @Autowired
    private MaintenanceContractService maintenanceContractService;
    //单个新增
    @RequestMapping(value = "/insert")
    public JsonResult insertDevice(@RequestBody MaintenanceContract maintenanceContract) {
        maintenanceContractService.addMaintenanceContract(maintenanceContract);
        return renderSuccess("success");
    }

    //单个更新
    @RequestMapping(value = "/update")
    public JsonResult updateDevice(@RequestBody MaintenanceContract maintenanceContract) {
        maintenanceContractService.updateMaintenanceContract(maintenanceContract);
        return renderSuccess("success");
    }

    @PostMapping("/uploadMaintenanceContract")
    public JsonResult uploadMaintenanceContract(@RequestParam("maintenanceContract") MultipartFile file,@RequestParam String resId) throws Exception
    {
        String linkUrl = maintenanceContractService.uploadContract(resId, file);
        JSONObject resJson = new JSONObject();
        if(StringUtils.isNotEmpty(linkUrl)){
            resJson.put("linkUrl",linkUrl);
            return renderSuccess(resJson);
        }

        return renderError("上传合同失败,请联系管理员");
    }



    //批量删除
    @RequestMapping(value = "/batchDelete")
    public JsonResult batchDelete(@RequestBody JSONArray array) {
        List resIdArr = JSONArray.parseArray(array.toJSONString());
        maintenanceContractService.deleteMaintenanceContract(resIdArr);
        return renderSuccess("success");
    }

    /**
     * 电子设备合同导入模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<MaintenanceContract> util = new ExcelUtil<MaintenanceContract>(MaintenanceContract.class);
        util.importTemplateExcel(response, "sheet1");
    }

    /**
     * 批量导入文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/importData")
    public JsonResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<MaintenanceContract> util = new ExcelUtil<MaintenanceContract>(MaintenanceContract.class);
        List<MaintenanceContract> maintenanceContractList = util.importExcel(file.getInputStream(),"sheet1");

        String message = maintenanceContractService.importMaintenanceContract(maintenanceContractList, false);
        return renderSuccess(message);
    }
}
