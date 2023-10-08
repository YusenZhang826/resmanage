package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.service.ECSService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * description:
 */

@RestController
@RequestMapping(value = {"/v1/cloudvm"})
public class ECSController extends BaseController {

    @Autowired
    private ECSService ecsService;

    @CrossOrigin
    @PostMapping(value = "/getList")
    public JsonResult getECSList(@RequestBody JSONObject requestParams){
        if(!requestParams.isEmpty()){
            return renderSuccess(ecsService.getJson(requestParams));
        }
        return renderSuccess(ecsService.getJson());
    }
    //单个更新
    @CrossOrigin
    @RequestMapping(value = "/updateOne")
    public JsonResult updatePhysical(@RequestBody JSONObject requestParams) {
        return renderSuccess(ecsService.updateData(requestParams.toString()));
    }

    //批量更新
    @CrossOrigin
    @RequestMapping(value = "/updateList")
    public JsonResult updatePhysicallist(@RequestBody JSONArray requestParams) {
        return renderSuccess(ecsService.updatePhysicalList(requestParams));
    }

    /**
     * 批量删除库存设备
     * @param paramJson
     */
    @CrossOrigin
    @PostMapping(value = "/deleteList")
    public void deleteSingleDevice(@RequestBody JSONArray paramJson){
        ecsService.delelteDevice(paramJson);
    }

    //查询单个设备信息
    @CrossOrigin
    @GetMapping(value = "/getOne")
    public JsonResult getDeviceMessage(@RequestParam String instanceId){
        return renderSuccess(ecsService.getMessage(instanceId));
    }

    //单个新增
    @CrossOrigin
    @RequestMapping(value = "/insertOne")
    public JSONObject insertDevice(@RequestBody JSONObject requestParams) {
        return ecsService.insertDevice(requestParams);
    }

    //excel导出
    @CrossOrigin
    @RequestMapping(value = "/exportList")
    public void exportDevice(@RequestBody JSONArray List, HttpServletResponse response) {
        ecsService.exportDevice(List,response);
    }

}
