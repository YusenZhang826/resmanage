package com.clic.ccdbaas.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.entity.StorageDevice;
import com.clic.ccdbaas.service.StorageDeviceService;
import com.clic.ccdbaas.utils.JsonResult;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2022/11/17 13:49
 * @email chenjianhua@bmsoft.com.cn
 */
@RestController
@RequestMapping(value = {"/v1/storageDevice"})
public class StorageDeviceController extends BaseController {
    @Autowired
    StorageDeviceService storageDeviceService;

    /**
     * 批量删除库存设备
     * @param paramJson
     */
    @PostMapping(value = "/deleteDevice")
    public void deleteSingleDevice(@RequestBody JSONArray paramJson){
        storageDeviceService.delelteDevice(paramJson);
    }

    //查询库存设备列表
    @CrossOrigin
    @PostMapping(value = "/getAllDevice")
    public JsonResult getAllDevice(@RequestBody JSONObject requestParams){
        if(!requestParams.isEmpty()){
            return renderSuccess(storageDeviceService.getJson(requestParams));
        }
        return renderSuccess(storageDeviceService.getJson());
    }

    //查询单个设备信息
    @CrossOrigin
    @GetMapping(value = "/getDeviceMessage")
    public JsonResult getDeviceMessage(@RequestParam String instanceId){
        return renderSuccess(storageDeviceService.getMessage(instanceId));
    }

    //excel导出
    @CrossOrigin
    @RequestMapping(value = "/exportDeviceList")
    public void exportDevice(HttpServletResponse response) {
        storageDeviceService.exportDevice(response);
    }

        //excel导入
    @CrossOrigin
    @RequestMapping(value = "/importDeviceList")
    public JsonResult importDevice(@RequestPart("file") MultipartFile file) {
        return renderSuccess(storageDeviceService.importDevice(file));
    }

    //单个新增
    @CrossOrigin
    @RequestMapping(value = "/insertDevice")
    public JSONObject insertDevice(@RequestBody JSONObject requestParams) {
        return storageDeviceService.insertDevice(requestParams);
    }

    //单个更新
    @CrossOrigin
    @RequestMapping(value = "/updateDevice")
    public JSONObject updateDevice(@RequestBody JSONObject requestParams) {
        return storageDeviceService.updateDevice(requestParams);
    }
}
