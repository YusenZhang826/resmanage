package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.entity.VCenter;
import com.clic.ccdbaas.service.VCenterService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/v1/vCenter"})
public class VCenterController extends BaseController {
    @Autowired
    private VCenterService vCenterService;

    @PostMapping("/uploadVCenterFile")
    public JsonResult uploadVCenterFile(@RequestPart("file") MultipartFile file) {
        BaseReadByLineListener<VCenter> readListener = new BaseReadByLineListener<VCenter>() {
            @Override
            public void process(VCenter vCenter) {
                vCenterService.createVCenter(vCenter);
            }
        };
        return renderUploadExcelFileResult(file, readListener, VCenter.class);
    }
}
