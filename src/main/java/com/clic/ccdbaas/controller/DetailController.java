package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.DetailService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/detail")
public class DetailController extends CloudBaseController {

    @Autowired
    DetailService detailService;
    @Autowired
    RelationService relationService;

    @PostMapping("/ocInstance")
    public JsonResult getBaseInstance(@RequestBody JSONObject queryJSON) {
        String resourceType = queryJSON.getString("resourceType");
        String resId = queryJSON.getString("resId");
        if (StringUtils.isEmpty(resourceType) || StringUtils.isEmpty(resId))
            return renderError(400, "参数错误！");

        return renderSuccess(detailService.getInstanceInfo(resourceType, resId));
    }

    @PostMapping("/ocRelateInstance")
    public TableDataInfo getRelateInstance(@RequestBody JSONObject queryJSON) {
        String baseType = queryJSON.getString("baseType");
        String relateType = queryJSON.getString("relateType");
        String resId = queryJSON.getString("resId");
        int pageNum = queryJSON.getInteger("pageNum");
        int pageSize = queryJSON.getInteger("pageSize");

        try {
            JSONObject jsonObject = detailService.getRelateInstanceInfo(baseType, relateType, resId, pageNum, pageSize);
            if (jsonObject == null || jsonObject.size() == 0) return getDataTable(new ArrayList<>());

            JSONArray objList = jsonObject.getJSONArray("objList");
            int totalNum = jsonObject.getInteger("totalNum");
            return getOcDataTable(objList, totalNum);
        } catch (Exception e) {
            e.printStackTrace();
            return getDataTable(new ArrayList<>());
        }
    }

    @GetMapping("/getAllInstance")
    public TableDataInfo getAllInstance(@RequestParam String className){
        startPage();
        return detailService.getAllInstance(className);
    }

    @GetMapping("/getInstanceDetail")
    public JsonResult getInstanceDetail(@RequestParam String className,@RequestParam String resId){
        try{
            return renderSuccess(detailService.getInstanceDetail(className, resId));
        }catch (Exception e){
            return renderError("获取实例详情失败");
        }
    }

    @GetMapping("/getRelationClass")
    public JsonResult getRelationClass(@RequestParam String className){
        try{
            return renderSuccess(relationService.getRelationClass(className));
        }catch (Exception e){
            return renderError("获取关联资源类型失败");
        }
    }

    @PostMapping("/getAllRelationInstance")
    public JsonResult getAllRelationInstance(@RequestBody JSONObject obj){
        try{
            return renderSuccess(relationService.getAllRelationInstance(obj));
        }catch (Exception e){
            return renderError("获取关联资源实例失败");
        }
    }

    @PostMapping("/getRelationInstance")
    public TableDataInfo getRelationInstance(@RequestBody JSONObject obj){
        JSONObject object = new JSONObject();
        String relationId = relationService.getResIdByClassName(obj.getString("className"), obj.getString("relationClass"));
        if(relationId == null){
            object.put("flag",1);
            object.put("relationId", relationService.getResIdByClassName(obj.getString("relationClass"), obj.getString("className")));
        }else{
            object.put("flag",0);
            object.put("relationId", relationId);
        }
        object.put("relationClass", obj.getString("relationClass"));
        object.put("resId", obj.getString("resId"));
        startPage();
        return relationService.getRelationInstance(object);
    }

    @PostMapping(value = "/getResCount")
    public JsonResult getResCount(@RequestBody JSONObject queryJson){
        JSONArray classNameArr = queryJson.getJSONArray("classNameArr");

        JSONObject instanceCount = detailService.getResCount(classNameArr);


        return renderSuccess(instanceCount,"success");

    }
}
