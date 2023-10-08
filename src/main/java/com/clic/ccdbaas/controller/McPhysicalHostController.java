package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.service.McPhysicalHostService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/v1/mcPhysicalHost"})
public class McPhysicalHostController extends CloudBaseController {
    @Autowired
    private McPhysicalHostService mcPhysicalHost;

    @CrossOrigin
    @PostMapping(value = "/deletePhysicalHost")
    public JsonResult addPhysicalHostByExcel(@RequestPart("file") MultipartFile file) {
        BaseReadByLineListener<PhysicalHost> readListener = new BaseReadByLineListener<PhysicalHost>() {
            @Override
            public void process(PhysicalHost physicalHost) {
                mcPhysicalHost.deletePhysicalHost(physicalHost);
            }
        };
        return renderUploadExcelFileResult(file, readListener, PhysicalHost.class, 0, 1);
    }
}
