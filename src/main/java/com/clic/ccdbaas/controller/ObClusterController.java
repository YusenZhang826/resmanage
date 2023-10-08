package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ObCluster;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ObClusterService;
import com.clic.ccdbaas.service.ObTenantService;
import com.clic.ccdbaas.utils.AESUtils;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/obCluster")
public class ObClusterController extends CloudBaseController {
    @Autowired
    ObTenantService obTenantService;
    @Autowired
    ObClusterService obClusterService;

    @GetMapping("/getObUserInfo")
    public JsonResult getObUserInfo(@RequestParam String clusterId, @RequestParam String tenantId){
        JSONObject tenUserInfo = obTenantService.getTenUserInfo(clusterId, tenantId);
        return renderSuccess(tenUserInfo);
    }

    @GetMapping("/dealClusterInfo")
    public void dealClusterInfo(){
        obClusterService.saveObCluster();
    }


    @GetMapping("/encryptInfo")
    public JsonResult encryptInfo(@RequestParam String content){
        String encryptContent = AESUtils.encrypt(content);
        return renderSuccess(encryptContent);
    }

    @GetMapping(value = "/getClusterByPage")
    public TableDataInfo getClusterByPage(ObCluster obCluster){
        startPage();
        List<ObCluster> allClusterInfo = obClusterService.getAllClusterInfo(obCluster);
        return getDataTable(allClusterInfo);

    }




}
