package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.CloudVolume;
import com.clic.ccdbaas.entity.ObTenant;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.CloudVolumeService;
import com.clic.ccdbaas.service.ResourceSearchService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cloudVolume")
public class CloudVolumeController extends CloudBaseController {
    @Autowired
    CloudVolumeService cloudVolumeService;
    @Autowired
    ResourceSearchService resourceSearchService;


    @GetMapping("/saveVolumeInfo")
    public void saveVolumeInfo() {
        cloudVolumeService.saveAllCloudVolume();
    }

    @GetMapping("/getVmDetailInfo")
    public JsonResult getVmDetailInfo(@RequestParam String resId) {
        CloudVolume cloudVolumeDetail = cloudVolumeService.vmDetailInfo(resId);
        return renderSuccess(cloudVolumeDetail);

    }

    @GetMapping(value = "/getVolumeByPage")
    public TableDataInfo getVolumeByPage(CloudVolume cloudVolume) {
        startPage();
        List<ObTenant> allClusterInfo = cloudVolumeService.getAllCloudVolume(cloudVolume);
        return getDataTable(allClusterInfo);

    }

    @PostMapping(value = "/advancedSearchByPage")
    public TableDataInfo advancedSearchByPage(AdvancedSearch advancedSearch) {
        advancedSearch.setClassName("CloudVolume");
        startPage();
        return getDataTable(resourceSearchService.advancedSearchInstance(advancedSearch));
    }

    /**
     * 条件查询虚机下所有的磁盘信息
     *
     * @param resId
     * @return
     */
    @GetMapping(value = "/getVolumesByVmId")
    public TableDataInfo getVolumesByVmId(@RequestParam(required = true) String resId) {

        return getDataTable(cloudVolumeService.getAllCloudVolume(resId));
    }


}
