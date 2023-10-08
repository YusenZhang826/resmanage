package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.AppInfo;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appInfo")
public class AppInfoController extends CloudBaseController {

    @Autowired
    AppInfoService appInfoService;


    @GetMapping("/saveAppInfoInfo")
    public void saveAppInfoInfo() {
        appInfoService.saveAllAppInfo();
    }




    @GetMapping(value = "/getAppInfoByPage")
    public TableDataInfo getClusterByPage(AppInfo appInfo) {
        startPage();
        List<AppInfo> allAppInfoInfo = appInfoService.getAllAppInfo(appInfo);
        return getDataTable(allAppInfoInfo);

    }


}
