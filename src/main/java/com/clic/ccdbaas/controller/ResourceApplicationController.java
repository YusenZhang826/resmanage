package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.ResourceApplication;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.ResourceApplicationService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/resourceApplication"})
public class ResourceApplicationController extends CloudBaseController {
    @Autowired
    ResourceApplicationService resourceApplicationService;

    @GetMapping(value = "/getResourceApplicationByPage")
    public TableDataInfo getResourceApplicationByPage(ResourceApplication resourceApplication){
        startPage();
        List<ResourceApplication> list = resourceApplicationService.getResourceApplicationList(resourceApplication);
        return getDataTable(list);

    }

    /**
     * 更新纳管信息（mainIp status lastModified）
     * @param requestParams（nativeId last_Modified IpList count）
     * @return
     */
    @RequestMapping(value = "updateResourceApplicationByNativeId")
    public boolean updateResourceApplicationByNativeId(@RequestBody JSONObject requestParams) {
         return resourceApplicationService.updateResourceApplicationStatus(requestParams);
    }

    /**
     * 更新全景展示数据
     * @param requestParams
     * @return
     */
    @PostMapping(value = "/updateLocalhostResource")
    public boolean updateLocalhostResource(@RequestBody JSONObject requestParams) {
        return resourceApplicationService.updateLocalhostResource(requestParams);
    }

    /**
     * 检查同一工单号是否均已纳管
     * @param requestParams
     * @return
     */
    @PostMapping(value = "/checkResourceStatus")
    public int checkResourceStatus(@RequestBody JSONObject requestParams) {
        return resourceApplicationService.checkResourceStatus(requestParams);
    }

    /**
     * 资源申请工单导出
     */
    @PostMapping(value = "/exportResourceApplicationInstanceByResId")
    public void exportResourceApplicationInstance(HttpServletResponse response, @RequestBody List<String> resIdList) {
        List<ResourceApplication> resourceApplicationList = resourceApplicationService.getExportResourceApplicationInfo(resIdList);
        ExcelUtil<ResourceApplication> util = new ExcelUtil<>(ResourceApplication.class);
        util.hideColumn("resId", "last_Modified");
        util.exportExcel(response, resourceApplicationList, "资源申请工单");
    }

    /**
     * 根据资源申请工单更新物理机位置信息
     */
    @PostMapping(value = "/updateShelfPositionByResourceApplication")
    public JsonResult updateShelfPositionByResourceApplication() {
        resourceApplicationService.updateShelfPositionByResourceApplication();
        return renderSuccess("更新物理机位置信息成功");
    }

    /**
     * 清除废弃工单及对应申请物理机信息
     */
    @PostMapping(value = "/deletePhysicalServerByResourceApplication")
    public JsonResult deletePhysicalServerByResourceApplication(@RequestBody List<String> workOrderList) {
        resourceApplicationService.deletePhysicalServerByResourceApplication(workOrderList);
        return renderSuccess("清除废弃工单及对应申请物理机信息成功");
    }

    /**
     * 手动触发资源回收流程（存量物理机下线）
     */
    @PostMapping(value = "/executePhysicalServerResourceRecycling")
    public JsonResult executePhysicalServerResourceRecycling(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<ResourceApplication> readListener = new BaseReadByLineListener<ResourceApplication>() {
            @Override
            public void process(ResourceApplication resourceApplication) {
                if (!StringUtils.isEmpty(resourceApplication.getMainIp())
                        && !StringUtils.isEmpty(resourceApplication.getWorkOrder())) {
                    resourceApplicationService.executePhysicalServerResourceRecycling(resourceApplication);
                }
            }
        };
        return renderUploadExcelFileResult(file, readListener, ResourceApplication.class);
    }
}
