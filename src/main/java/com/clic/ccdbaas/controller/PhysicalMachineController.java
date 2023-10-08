package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.PhysicalMachineService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/physical")
public class PhysicalMachineController extends CloudBaseController {

    @Autowired
    private PhysicalMachineService physicalMachineService;

    @GetMapping(value = "/getPhysicalByPage")
    public TableDataInfo getPhysicalByPage(PhysicalMachine physicalMachine){
        startPage();
        List<PhysicalMachine> physicalList = physicalMachineService.getPhysicalList(physicalMachine);
        return getDataTable(physicalList);

    }

    @GetMapping("/countPhysicalByStatus")
    public TableDataInfo countByStatus(PhysicalMachine physicalMachine) {
        List<HashMap> countNum = physicalMachineService.countInstanceByStatus(physicalMachine);
        return getDataTable(countNum);
    }

    @PostMapping("/exportList")
    public void export(HttpServletResponse response, PhysicalMachine physicalMachine)
    {
        List<PhysicalMachine> list = physicalMachineService.getAllPhysicalHost(physicalMachine);
        ExcelUtil<PhysicalMachine> util = new ExcelUtil<PhysicalMachine>(PhysicalMachine.class);
        util.exportExcel(response, list, "个人资源");
    }

    @CrossOrigin
    @RequestMapping(value = "/updateone")
    public JsonResult updatePhysical(@RequestBody JSONObject requestParams) {
        return renderSuccess(physicalMachineService.updateData(requestParams.toString()));
    }

    @PostMapping(value = "/uploadCmdbPhysicalServer")
    public JsonResult uploadCmdbPhysicalServer(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<PhysicalServer> readListener = new BaseReadByLineListener<PhysicalServer>() {
            @Override
            public void process(PhysicalServer physicalServer) {
                if (physicalServer.getIsPhysicalServer().equals("安全设备") || physicalServer.getIsPhysicalServer().equals("物理机")) {
                    physicalMachineService.uploadCmdbPhysicalServer(physicalServer);
                }
            }
        };
        return renderUploadExcelFileResult(file, readListener, PhysicalServer.class);
    }

    @PostMapping(value = "/uploadTestPhysicalServer")
    public JsonResult uploadTestPhysicalServer(@RequestParam(value = "file") MultipartFile file) {
        List<PhysicalMachine> physicalServerList = physicalMachineService.getPhysicalList(new PhysicalMachine("1"));
        Map<String, PhysicalMachine> originalPhysicalServerListMap = physicalServerList.stream().collect(Collectors.toMap(k -> k.getLocationCode(), PhysicalMachine -> PhysicalMachine));
        BaseReadByLineListener<PhysicalServer> readListener = new BaseReadByLineListener<PhysicalServer>() {
            @Override
            public void process(PhysicalServer physicalServer) {
                if (originalPhysicalServerListMap.containsKey(physicalServer.getLocationCode())) {
                    originalPhysicalServerListMap.remove(physicalServer.getLocationCode());
                    physicalMachineService.updateTestPhysicalServer(physicalServer);
                } else {
                    physicalMachineService.addTestPhysicalServer(physicalServer);
                }
            }
        };
        physicalMachineService.deleteTestPhysicalServer(originalPhysicalServerListMap);
        return renderUploadExcelFileResult(file, readListener, PhysicalServer.class);
    }

    /**
     * oc裸金属资源同步（新增、更新、删除）
     */
    @PostMapping("executeCloudBmsSynchronization")
    public JsonResult executeCloudBmsSynchronization() {
        return renderSuccess(physicalMachineService.executeCloudBmsSynchronization());
    }
}
