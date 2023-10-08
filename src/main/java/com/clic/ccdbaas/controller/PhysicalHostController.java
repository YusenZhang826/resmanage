package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.PhysicalHostService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = {"/v1/physicalhost"})
public class PhysicalHostController extends CloudBaseController {
    @Autowired
    private PhysicalHostService physicalService;

    @CrossOrigin
    @PostMapping(value = "/getList")
    public JsonResult getAllPhysicalList(@RequestBody JSONObject requestParams) {
        if (!requestParams.isEmpty()) {
            return renderSuccess(physicalService.getJson(requestParams));
        }
        return renderSuccess(physicalService.getJson());
    }


    //单个更新
    @CrossOrigin
    @RequestMapping(value = "/updateOne")
    public JsonResult updatePhysical(@RequestBody JSONObject requestParams) {
        return renderSuccess(physicalService.updateData(requestParams.toString()));
    }

    //批量更新
    @CrossOrigin
    @RequestMapping(value = "/updateList")
    public JsonResult updatePhysicallist(@RequestBody JSONArray requestParams) {
        return renderSuccess(physicalService.updatePhysicalList(requestParams));
    }

    /**
     * 批量删除
     *
     * @param paramJson
     */
    @CrossOrigin
    @PostMapping(value = "/deleteList")
    public void deleteSingleDevice(@RequestBody JSONArray paramJson) {
        physicalService.delelteDevice(paramJson);
    }

    //查询单个
    @CrossOrigin
    @GetMapping(value = "/getOne")
    public JsonResult getDeviceMessage(@RequestParam String instanceId) {
        return renderSuccess(physicalService.getMessage(instanceId));
    }

    //单个新增
    @CrossOrigin
    @RequestMapping(value = "/insertOne")
    public JSONObject insertDevice(@RequestBody JSONObject requestParams) {
        return physicalService.insertDevice(requestParams);
    }

    @CrossOrigin
    @RequestMapping(value = "/insertList")
    public String insertList(@RequestBody JSONArray requestParams) {
        return physicalService.insertList(requestParams);
    }

    //excel导出
    @CrossOrigin
    @RequestMapping(value = "/exportList")
    public void exportDevice(@RequestBody JSONArray List, HttpServletResponse response) {
        physicalService.exportDevice(List, response);
    }

    @GetMapping(value = "/getPhysicalHostByPage")
    public TableDataInfo getPhysicalHostByPage(PhysicalHost physicalHost) {
        startPage();
        List<PhysicalHost> hostList = physicalService.getPhysicalHostList(physicalHost);
        return getDataTable(hostList);

    }

    @GetMapping("/countPhysicalHostByStatus")
    public TableDataInfo countHostByStatus() {
        List<HashMap> countNum = physicalService.countHostInstanceByStatus();
        return getDataTable(countNum);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response, PhysicalHost physicalHost) {
        List<PhysicalHost> list = physicalService.getAllPhysicalHost(physicalHost);
        ExcelUtil<PhysicalHost> util = new ExcelUtil<PhysicalHost>(PhysicalHost.class);
        util.exportExcel(response, list, "个人资源");
    }

    @GetMapping("/getCloudVmByResId")
    public TableDataInfo getCloudVmByResId(@RequestParam String resId) {
        List<CloudVmare> countNum = physicalService.getCloudVmByResId(resId);
        return getDataTable(countNum);
    }

    @GetMapping("/countPhysicalByStatus")
    public TableDataInfo countByStatus(PhysicalHost physicalMachine) {
        List<HashMap> countNum = physicalService.countInstanceByStatus(physicalMachine);
        return getDataTable(countNum);
    }

    /**
     * oc宿主机资源同步（新增、更新、删除）
     */
    @PostMapping("executePhysicalHostSynchronization")
    public JsonResult executePhysicalHostSynchronization() {
        return renderSuccess(physicalService.executePhysicalHostSynchronization());
    }

    //通过excel导入新增宿主机,file为待导入的表，file2为cmdb信息表
    @CrossOrigin
    @PostMapping(value = "/addExcel")
    public JsonResult addExcel(@RequestPart("file") MultipartFile file, @RequestPart("file2") MultipartFile file2) {
        HashMap<String, PhysicalHost> hosts = new HashMap<>();
        BaseReadByLineListener<PhysicalHost> readListener1 = new BaseReadByLineListener<PhysicalHost>() {
            @Override
            public void process(PhysicalHost physicalHost) {
                hosts.put(physicalHost.getMainIp(), physicalHost);
            }
        };
        renderUploadExcelFileResult(file2, readListener1, PhysicalHost.class, 0, 1);

        BaseReadByLineListener<PhysicalHost> readListener = new BaseReadByLineListener<PhysicalHost>() {
            @Override
            public void process(PhysicalHost physicalHost) {
                physicalService.addExcel(hosts, physicalHost);
            }
        };
        return renderUploadExcelFileResult(file, readListener, PhysicalHost.class, 0, 1);
    }

    @CrossOrigin
    @PostMapping(value = "/addPhysicalHostByExcel")
    public JsonResult addPhysicalHostByExcel(@RequestPart("file") MultipartFile file) {
        BaseReadByLineListener<PhysicalHost> readListener = new BaseReadByLineListener<PhysicalHost>() {
            @Override
            public void process(PhysicalHost physicalHost) {
                physicalService.addPhysicalHostByExcel(physicalHost);
            }
        };
        return renderUploadExcelFileResult(file, readListener, PhysicalHost.class, 0, 1);
    }
}
