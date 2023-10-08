package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ObCluster;
import com.clic.ccdbaas.entity.ObTenant;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ObClusterService;
import com.clic.ccdbaas.service.ObTenantService;
import com.clic.ccdbaas.utils.AESUtils;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/obTenant")
public class ObTenantController extends CloudBaseController {
    @Autowired
    ObTenantService obTenantService;
    @Autowired
    ObClusterService obClusterService;

    @GetMapping("/getObUserInfo")
    public JsonResult getObUserInfo(@RequestParam String clusterId, @RequestParam String tenantId){
        JSONObject tenUserInfo = obTenantService.getTenUserInfo(clusterId, tenantId);
        return renderSuccess(tenUserInfo);
    }

    @GetMapping("/dealTenantInfo")
    public void saveTenInfo(){
        obTenantService.saveTenInfo();
    }
    @GetMapping("/getTenantDetail")
    public JsonResult getTenantDetail(@RequestParam String resId){
        return  renderSuccess(obTenantService.getTenantDetail(resId));
    }


    @GetMapping(value = "/getTenantByPage")
    public TableDataInfo getClusterByPage(ObTenant obTenant){
        startPage();
        List<ObTenant> allClusterInfo = obTenantService.getAllTenantInfo(obTenant);
        return getDataTable(allClusterInfo);

    }

    @GetMapping(value = "/toOut/getTenantByPage")
    public TableDataInfo toOutGetTenant(ObTenant obTenant){
        startPage();
        List<ObTenant> allClusterInfo = obTenantService.getAllTenantInfo(obTenant);
        return getDataTable(allClusterInfo);

    }
}
