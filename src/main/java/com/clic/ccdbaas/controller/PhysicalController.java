package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.service.PhysicalService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * description:
 */
@RestController
@RequestMapping(value = {"/v1/physical"})
public class PhysicalController extends BaseController {

    @Autowired
    private PhysicalService physicalService;

    @CrossOrigin
    @PostMapping(value = "/getList")
    public JsonResult getAllPhysicalList(@RequestBody JSONObject requestParams) {
        if (!requestParams.isEmpty()) {
            return renderSuccess(physicalService.getJson(requestParams));
        }
        return renderSuccess(physicalService.getJson());
    }

    /**
     * 获取合并资源集
     *
     * @param requestParams
     * @return
     */
    @PostMapping(value = "/getMergeResList")
    public JsonResult getMergeResList(@RequestBody JSONObject requestParams) {
       /* if(!requestParams.isEmpty()){
            return renderSuccess(physicalService.getMergeCloudResouce(requestParams));
        }*/
        return renderSuccess(physicalService.getMergeCloudResouce(requestParams));
    }


    /*  //单个更新
      @CrossOrigin
      @RequestMapping(value = "/updateOne")
      public JsonResult updatePhysical(@RequestBody JSONObject requestParams) {
          return renderSuccess(physicalService.updateData(requestParams.toString()));
      }
  */
    //批量更新
    @CrossOrigin
    @RequestMapping(value = "/updateList")
    public JsonResult updatePhysicallist(@RequestBody JSONObject requestParams) {
        return renderSuccess(physicalService.updatePhysicalList(requestParams));
    }

    @CrossOrigin
    @RequestMapping(value = "/updateTestList")
    public JsonResult updateTestList(@RequestBody JSONObject requestParams) {
        return renderSuccess(physicalService.updatePhysicalList(requestParams));
    }

    @CrossOrigin
    @RequestMapping(value = "/userName")
    public void userName(@RequestParam String user_name) {
        physicalService.getName(user_name);
    }

    /**
     * 批量删除库存设备
     *
     * @param paramJson
     */
    @CrossOrigin
    @PostMapping(value = "/deleteList")
    public void deleteSingleDevice(@RequestBody JSONArray paramJson) {
        physicalService.delelteDevice(paramJson);
    }

    //查询单个设备信息
    @CrossOrigin
    @GetMapping(value = "/getOne")
    public JsonResult getDeviceMessage(@RequestParam String instanceId) {
        return renderSuccess(physicalService.getMessage(instanceId));
    }

    //单个新增
    @CrossOrigin
    @RequestMapping(value = "/insertOne")
    public JSONObject insertDevice(@RequestBody JSONObject requestParams) {
        return physicalService.insertDevice(requestParams);
    }

    @CrossOrigin
    @RequestMapping(value = "/insertList")
    public String insertList(@RequestBody JSONArray requestParams) {
        return physicalService.insertList(requestParams);
    }

    //excel导出
    @CrossOrigin
    @RequestMapping(value = "/exportList")
    public void exportDevice(@RequestBody JSONArray List, HttpServletResponse response) {
        physicalService.exportDevice(List, response);
    }

    @CrossOrigin
    @RequestMapping(value = "/getComputeInfo")
    public JsonResult exportDevice(@RequestParam String resId) {
        return renderSuccess(physicalService.getCloudVMInfo(resId));
    }

    @CrossOrigin
    @RequestMapping(value = "/updateCloudVmComputeInfo")
    public JsonResult updateCloudVmDevice(@RequestParam String resId, @RequestParam String usageDes) {
        return renderSuccess(physicalService.updateComputeInfo(resId, usageDes));
    }

    @CrossOrigin
    @RequestMapping(value = "/updatePhysicalComputeInfo")
    public JsonResult updatePhysicalDevice(@RequestParam String resId, @RequestParam String usageDes) {
        return renderSuccess(physicalService.updatePhysicalComputeInfo(resId, usageDes));
    }
}
