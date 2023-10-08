package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.AppInfo;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.HcsZoneSummaryInfo;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.AppInfoService;
import com.clic.ccdbaas.service.HcsSummaryInfoService;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/hcsSummaryInfo")
public class HcsSummaryInfoController extends CloudBaseController {

    @Autowired
    HcsSummaryInfoService hcsSummaryInfoService;


    @GetMapping("/saveHcsSummaryInfoInfo")
    public void saveAppInfoInfo() {
        hcsSummaryInfoService.saveHcsSummaryInfo();
    }

    @GetMapping(value = "/getAzoneList")
    public TableDataInfo getAzoneList(HcsZoneSummaryInfo hcsZoneSummaryInfo) {
        startPage();
        List<HcsZoneSummaryInfo> azoneInfo = hcsSummaryInfoService.getAzoneInfo(hcsZoneSummaryInfo);
        return getDataTable(azoneInfo);

    }


    /**
     * 获取region橄榄信息
     * @param params
     */
    @PostMapping("/getRegionSummaryInfo")
    public JsonResult getRegionSummaryInfo(@RequestBody(required = false) Map params) {
        return renderSuccess(hcsSummaryInfoService.getRegionSummaryInfo(params));
    }


    /**
     * 获取azone获取所有的cluster
     * @param azoneId
     */
    @GetMapping("/getClusterByAzone")
    public JsonResult getClusterByAzone(@RequestParam String azoneId) {
        return renderSuccess(hcsSummaryInfoService.getClusterByAzoneId(azoneId));
    }
    /**
     * 获取region存储信息
     * @param params
     */
    @PostMapping("/getRegionStorageInfo")
    public JsonResult getRegionStorageInfo(@RequestBody(required = false) Map params) {
        return renderSuccess(hcsSummaryInfoService.getRegionStorageInfo(params));
    }

    /**
     * 获取cluster资源池信息
     * @param params
     */
    @PostMapping("/getHcsClusterSummaryInfo")
    public JsonResult getHcsClusterSummaryInfo(@RequestBody(required = false) Map params) {
        List<Map> hcsClusterSummaryInfo = hcsSummaryInfoService.getHcsClusterSummaryInfo(params);
        return renderSuccess(hcsClusterSummaryInfo);
    }


    /**
     * 获取region概览信息
     * @param params
     */
    @PostMapping("/getRegionSummaryInfoTmp")
    public JsonResult getRegionSummaryInfoTmp(@RequestBody(required = false) Map params) {
        return renderSuccess(hcsSummaryInfoService.getRegionSummaryInfoTmp(params));
    }

    /**
     * 获取region存储信息
     * @param params
     */
    @PostMapping("/getRegionStorageInfoTmp")
    public JsonResult getRegionStorageInfoTmp(@RequestBody(required = false) Map params) {
        return renderSuccess(hcsSummaryInfoService.getRegionStorageInfoTmp(params));
    }
}
