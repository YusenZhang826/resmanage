package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.service.CmdbService;
import com.clic.ccdbaas.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cmdb")
public class CmdbController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CmdbController.class);
    @Autowired
    CmdbService cmdbService;

    /**
     * 手动执行CMDB全量主机信息上报
     */
    @GetMapping("/executeCmdbCloudVmUpload")
    public JsonResult executeCmdbCloudVmUpload() {
        cmdbService.uploadAllCloudVms();
        return renderSuccess("手动上报成功");
    }

    /**
     * 手动执行CMDB网络信息上报
     */
    @GetMapping("/executeCmdbNetworkVlanUpload")
    public JsonResult executeCmdbNetworkVlanUpload() {
        return renderSuccess(cmdbService.executeCmdbNetworkVlanUpload());
    }

    /**
     * 手动执行CMDB库存设备信息上报
     */
    @GetMapping("/executeCmdbReserveDeviceUpload")
    public JsonResult executeCmdbReserveDeviceUpload() {
        cmdbService.executeCmdbReserveDeviceUpload();
        return renderSuccess("手动上报成功");
    }

}
