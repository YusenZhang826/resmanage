package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.HostInstance;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.entity.PhysicalMachine;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.CloudVmareService;
import com.clic.ccdbaas.service.OcService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/physical"})
public class CloudVmareController extends CloudBaseController {

    @Autowired
    CloudVmareService cloudVmareService;
    @Autowired
    OcService ocService;
    @Value("${oc.cmdb.vm}")
    private String vmClassName;

    @CrossOrigin
    @PostMapping(value = "/exportCloudVmare")
    public JSONObject exportCloudVmare(@RequestBody JSONObject requestParams) {
        return cloudVmareService.exportCloudVmare(requestParams);
    }

    @GetMapping(value = "/getCloudVmByPage")
    public TableDataInfo getCloudVmByPage(CloudVmare cloudVmare) {
        startPage();
        List<CloudVmare> cloudvmList = cloudVmareService.getCloudvmList(cloudVmare);

        // AsyncManager.me().execute(AsyncFactory.recordOper("123456","17710461",1,"主机管理员A","陈建华","陈建华",1));
        return getDataTable(cloudvmList);

    }


    @PostMapping(value = "/getResByParam")
    public TableDataInfo getCCEByPage(@RequestBody JSONObject queryJson) {
        JSONObject allResByParams = cloudVmareService.getAllResByParams(queryJson);
        Integer totalNum = allResByParams.getInteger("totalNum");
        JSONArray objList = allResByParams.getJSONArray("objList");
        return getOcDataTable(objList, totalNum);

    }

    @PostMapping(value = "/testDeleteAllData")
    public void testDeleteAllData(@RequestBody JSONObject queryJson) {
        cloudVmareService.testDeleteAllData();

    }


    /**
     * 定时保存数据量个数
     *
     * @return
     */
    @PostMapping(value = "/saveResCountInfo")
    public JsonResult saveResCountInfo() {
        cloudVmareService.saveResCountInfo();
        return renderSuccess("success");

    }


    /**
     * 定时保存数据流向信息
     *
     * @return
     */
    @PostMapping(value = "/saveResTrendInfo")
    public JsonResult saveResTrendInfo() {
        cloudVmareService.saveResTrendInfo();
        return renderSuccess("success");

    }


    /**
     * 定时保存数据库流向信息
     *
     * @return
     */
    @PostMapping(value = "/saveDbTrendInfo")
    public JsonResult saveDbTrendInfo() {
        cloudVmareService.saveDbTrendInfo();
        return renderSuccess("success");

    }

    @PostMapping(value = "/getResCount")
    public JsonResult getResNum(@RequestBody JSONObject queryJson) {
        JSONArray classNameArr = queryJson.getJSONArray("classNameArr");

        JSONObject instanceCount = cloudVmareService.getAllInstanceCount(classNameArr);


        return renderSuccess(instanceCount, "success");

    }


    @GetMapping(value = "/getCloudMergeResourceByPage")
    public TableDataInfo getCloudMergeResourceByPage(CloudVmare cloudVmare) {
        startPage();
        List<CloudVmare> cloudvmList = cloudVmareService.getAllResourceList(cloudVmare);

        return getDataTable(cloudvmList);

    }

    @GetMapping(value = "/getNullBelongProduct")
    public TableDataInfo getNullBelongProduct(CloudVmare cloudVmare) {
        startPage();
        List<CloudVmare> cloudvmList = cloudVmareService.getNullBelongProductList(cloudVmare);

        return getDataTable(cloudvmList);

    }

    @PostMapping("/export")
    public void export(HttpServletResponse response,@RequestBody CloudVmare cloudVmare) {
        List<CloudVmare> list = cloudVmareService.exportCloudvmList(cloudVmare);
        ExcelUtil<CloudVmare> util = new ExcelUtil<CloudVmare>(CloudVmare.class);
        util.exportExcel(response, list, "个人资源");
    }

    @PostMapping("/exportMerge")
    public void exportMerge(HttpServletResponse response,@RequestBody CloudVmare cloudVmare) {
        List<CloudVmare> list = cloudVmareService.exportAllResourceList(cloudVmare);
        ExcelUtil<CloudVmare> util = new ExcelUtil<CloudVmare>(CloudVmare.class);
        util.exportExcel(response, list, "全量主机");
    }

    @GetMapping("/countCloudVmByStatus")
    public TableDataInfo countByStatus(CloudVmare cloudVmare) {
        List<HashMap> cvmCountByStatus = cloudVmareService.countByStatus(cloudVmare);
        return getDataTable(cvmCountByStatus);
    }

    @GetMapping("/countInstanceBydeployenv")
    public TableDataInfo countInstanceBydeployenv(CloudVmare cloudVmare) {
        List<HashMap> cvmCountByStatus = cloudVmareService.countInstanceBydeployenv(cloudVmare);
        return getDataTable(cvmCountByStatus);
    }

    @GetMapping("/countInstanceByStatusAzoneName")
    public TableDataInfo countByStatusGroudByAzoneName(CloudVmare cloudVmare) {
        List<HashMap> cvmCountByStatus = cloudVmareService.countByStatusGroudByAzoneName(cloudVmare);
        return getDataTable(cvmCountByStatus);
    }

    @GetMapping("/countPhysicalServerByDeployEnv")
    public TableDataInfo countPhysicalServerByDeployEnv(CloudVmare cloudVmare) {
        List<HashMap> cvmCountByStatus = cloudVmareService.countPhysicalServerByDeployEnv(cloudVmare);
        return getDataTable(cvmCountByStatus);
    }

    @GetMapping("/countPhysicalHostByDeployEnv")
    public TableDataInfo countPhysicalHostByDeployEnv(CloudVmare cloudVmare) {
        List<HashMap> cvmCountByStatus = cloudVmareService.countPhysicalHostByDeployEnv(cloudVmare);
        return getDataTable(cvmCountByStatus);
    }

    @GetMapping("/countMergeInstanceByDeployEnv")
    public TableDataInfo countMergeInstanceByDeployEnv(CloudVmare cloudVmare) {
        List<HashMap> MergeInstanceByDeployEnv = cloudVmareService.countMergeInstanceByDeployEnv(cloudVmare);
        return getDataTable(MergeInstanceByDeployEnv);
    }

    @GetMapping("/countCloudMergeResourceByStatus")
    public TableDataInfo countCloudMergeResourceByStatus(CloudVmare cloudVmare) {
        List<HashMap> resourceCount = cloudVmareService.countAllResourceByStatus(cloudVmare);
        return getDataTable(resourceCount);
    }

    /**
     * 根据云服务器id查询对应云硬盘详情
     *
     * @param vmId
     */
    @PostMapping("/getVolumeInstance")
    public JSONObject getVolumeInstance(@RequestParam String vmId) {
        return cloudVmareService.getStorageInstance(vmId, vmClassName);
    }

    @GetMapping(value = "/getCloudVmClassInfo")
    public JsonResult getCloudVmClassInfo() {
        return renderClassInfo(CloudVmare.class);
    }

    @GetMapping(value = "/getPhysicalClassInfo")
    public JsonResult getPhysicalClassInfo() {
        return renderClassInfo(PhysicalMachine.class);
    }

    @GetMapping(value = "/getPhysicalHostClassInfo")
    public JsonResult getPhysicalHostClassInfo() {
        return renderClassInfo(PhysicalHost.class);
    }

    @GetMapping(value = "/getHostInstanceClassInfo")
    public JsonResult getHostInstanceClassInfo() {
        return renderClassInfo(HostInstance.class);
    }

    @GetMapping("/getRegionNameOfMergedInstance")
    public JsonResult getRegionNameOfMergedInstance() {
        return renderSuccess(cloudVmareService.getRegionNameOfMergedInstance());
    }

    /**
     * 云硬盘关联关系落库
     */
    @GetMapping("/cloudVolumeRelationsInstance")
    public JsonResult cloudVolumeRelationsInstance() {
        return renderSuccess(cloudVmareService.getCloudVolumeRelationsInstance());
    }

    /**
     * oc虚机资源同步（新增、更新、删除）
     */
    @PostMapping("executeCloudVmSynchronization")
    public JsonResult executeCloudVmSynchronization() {
        return renderSuccess(cloudVmareService.executeCloudVmSynchronization());
    }

    /**
     * hicloud虚机资源同步（deployOs）
     */
    @PostMapping("executeHiCloudVmSynchronizationDeployOs")
    public JsonResult executeHiCloudVmSynchronizationDeployOs() {
        return renderSuccess(cloudVmareService.executeHiCloudVmSynchronizationDeployOs());
    }
}