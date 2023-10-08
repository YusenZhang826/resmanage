package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.EquipmentContract;
import com.clic.ccdbaas.entity.SysUser;
import com.clic.ccdbaas.model.AjaxResult;
import com.clic.ccdbaas.service.EquipmentContractService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.config.RuoYiConfig;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import com.clic.ccdbaas.utils.file.FileUploadUtils;
import com.clic.ccdbaas.utils.file.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/7/25 9:30
 * @email chenjianhua@bmsoft.com.cn
 */
@RestController
@RequestMapping(value = {"/v1/equipmentContract"})
public class EquipmentContractController  extends CloudBaseController {

    @Autowired
    private EquipmentContractService equipmentContractService;
    //单个新增
    @RequestMapping(value = "/insert")
    public JsonResult insertDevice(@RequestBody EquipmentContract equipmentContract) {
        equipmentContractService.addEquipmentContract(equipmentContract);
        return renderSuccess("success");
    }

    //单个更新
    @RequestMapping(value = "/update")
    public JsonResult updateDevice(@RequestBody EquipmentContract equipmentContract) {
        equipmentContractService.updateEquipmentContract(equipmentContract);
        return renderSuccess("success");
    }

    @PostMapping("/uploadEquipmentContract")
    public JsonResult uploadEquipmentContract(@RequestParam("equipmentContract") MultipartFile file,@RequestParam String resId) throws Exception
    {
        String linkUrl = equipmentContractService.uploadContract(resId, file);
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
        equipmentContractService.deleteEquipmentContract(resIdArr);
        return renderSuccess("success");
    }

    /**
     * 电子设备合同导入模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<EquipmentContract> util = new ExcelUtil<EquipmentContract>(EquipmentContract.class);
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
        ExcelUtil<EquipmentContract> util = new ExcelUtil<EquipmentContract>(EquipmentContract.class);
        List<EquipmentContract> equipmentContractList = util.importExcel(file.getInputStream(),"sheet1");

        String message = equipmentContractService.importEquipmentContract(equipmentContractList, false);
        return renderSuccess(message);
    }
}
