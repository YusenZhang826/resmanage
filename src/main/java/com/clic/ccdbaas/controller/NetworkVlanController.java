package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.*;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.*;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.excel.BaseReadByLineListener;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/networkVlan")
public class NetworkVlanController extends CloudBaseController {

    private static final Logger logger = LoggerFactory.getLogger(NetworkVlanController.class);
    @Autowired
    private VlanService vlanService;
    @Autowired
    private NetworkAreaService networkAreaService;
    @Autowired
    CloudVmareService cloudVmareService;
    @Autowired
    PhysicalHostService physicalHostService;
    @Autowired
    PhysicalMachineService physicalMachineService;
    @Value("${oc.cmdb.vm}")
    private String vmClassName;
    @Value("${oc.cmdb.host}")
    private String physicalHostClassName;
    @Value("${oc.cmdb.server}")
    private String physicalServerClassName;

    @GetMapping("/getAllInstance")
    public TableDataInfo getAllVLAN() {
        startPage();
        List<Vlan> vlanList = vlanService.getAllVlanInfo();
        return getDataTable(vlanList);
    }

    /**
     * 获取单条vlan信息
     */
    @GetMapping("getVlanInfoByResId")
    public JsonResult getVlanInfoByResId(String resId) {
        HashMap vlanPlan = vlanService.getVlanInfoByResId(resId);
        logger.info("成功获取 1 条数据");
        return renderSuccess(vlanPlan);
    }

    /**
     * 获取所有vlan信息
     */
    @GetMapping("getAllVlanInstance")
    public TableDataInfo getAllVlanInstance(VlanPlan vlanPlan) {
        startPage();
        List<VlanPlan> vlanPlanList = vlanService.getAllVlanInstance(vlanPlan);
        return getDataTable(vlanPlanList);
    }

    @GetMapping("/getAllVlanNoPage")
    public JsonResult getAllVlanNoPage(VlanPlan vlanPlan) {
        try {
            List<VlanPlan> vlanPlanList = vlanService.getAllVlanNameAndIpCount(vlanPlan);
            return renderSuccess(vlanPlanList);
        } catch (Exception e) {
            return renderError("获取全部vlan失败");
        }
    }

    /**
     * 插入单条vlan信息
     */
    @PostMapping("insertVlanInfo")
    public JsonResult insertSingleVlanInfo(@RequestBody JSONObject requestParams) {
        try {
            vlanService.insertSingleVlanInfo(requestParams);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("插入单条vlan信息成功！");
    }

    /**
     * 删除单条vlan信息
     */
    @PostMapping("deleteVlanInfo")
    public JsonResult deleteSingleVlanInfo(@RequestParam String resId) {
        try {
            vlanService.deleteSingleVlanInfo(resId);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("删除单条vlan信息成功！");

    }

    /**
     * 更新单条vlan信息
     */
    @PostMapping("updateVlanInfo")
    public JsonResult updateSingleVlanInfo(@RequestBody JSONObject requestParams) {
        try {
            vlanService.updateSingleVlanInfo(requestParams);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("更新单条vlan信息成功！");
    }

    /**
     * 获取全部网络区域
     */
    @GetMapping(value = "/getNetworkAreaByPage")
    public TableDataInfo getNetworkAreaByPage(NetworkArea networkArea) {
        startPage();
        List<NetworkArea> networkAreas = networkAreaService.getNetworkAreaList(networkArea);
        return getDataTable(networkAreas);

    }

    /**
     * 根据resId获取网络区域详情
     */
    @GetMapping(value = "/getNetworkAreaById")
    public JsonResult getNetworkAreaById(String resId) {
        NetworkArea networkArea = networkAreaService.getNetworkAreaById(resId);
        return renderSuccess(networkArea);
    }

    /**
     * 根据网络区域名称获取网络区域详情
     */
    @GetMapping(value = "/getNetworkAreaByName")
    public JsonResult getNetworkAreaByName(String name) {
        NetworkArea networkArea = networkAreaService.getNetworkAreaByName(name);
        return renderSuccess(networkArea);
    }

    /**
     * 删除网络区域
     */
    @PostMapping(value = "/deleteNetworkArea")
    public JsonResult deleteNetworkArea(@RequestBody JSONArray networkAreas) {
        try {
            networkAreaService.deleteNetworkArea(networkAreas);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("删除网络逻辑区域失败");
        }
        return renderSuccess("删除网络逻辑区域成功");
    }

    /**
     * 更新单个网络区域
     */
    @PostMapping(value = "/updateNetworkAreaById")
    public JsonResult updateNetworkAreaById(@RequestBody JSONObject networkArea) {
        try {
            networkAreaService.updateNetworkAreaById(networkArea);
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("更新网络逻辑区域失败");
        }
        return renderSuccess("更新网络逻辑区域成功");
    }

    /**
     * 添加网络区域
     */
    @RequestMapping(value = "/addNetworkArea")
    public JsonResult addNetworkArea(@RequestBody JSONObject networkArea) {
        try {
            networkAreaService.addNetworkArea(networkArea);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("添加网络逻辑区域成功！");
    }

    //通过excel导入新增网络区域
    @CrossOrigin
    @PostMapping(value = "/addNetworkAreaByExcel")
    public JsonResult addNetworkAreaByImport(@RequestPart("file") MultipartFile file) {
        BaseReadByLineListener<NetworkArea> readListener = new BaseReadByLineListener<NetworkArea>() {
            @Override
            public void process(NetworkArea networkArea) {
                networkAreaService.addNetworkArea(JSON.parseObject(JSON.toJSONString(networkArea)));
            }
        };
        return renderUploadExcelFileResult(file, readListener, NetworkArea.class, 1, 3);
    }

    //excel导出
    @RequestMapping(value = "/exportNetworkAreaExcel")
    public void exportNetworkAreaExcel(HttpServletResponse response, NetworkArea networkArea) {
        List<NetworkArea> list = networkAreaService.getNetworkAreaList(networkArea);
        ExcelUtil<NetworkArea> util = new ExcelUtil<NetworkArea>(NetworkArea.class);
        util.exportExcel(response, list, "网络逻辑区域");
    }

    /**
     * excel导入资源关系,废用
     */
    @CrossOrigin
    @PostMapping(value = "/addResourceRelationByExcel")
    public JsonResult addResourceRelationByExcel(@RequestPart("file") MultipartFile file) {
        try {
            networkAreaService.addResourceRelationByExcel(file, 2);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("导入资源关系成功！");
    }

    //添加单个资源关系
    @PostMapping(value = "/addResourceRelation")
    public JsonResult addResourceRelation(@RequestBody JSONObject relation) {
        try {
            networkAreaService.addNetworkVlanRelation(relation);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("添加资源关系成功！");
    }

    //删除单个资源关系
    @PostMapping(value = "/deleteResourceRelation")
    public JsonResult deleteResourceRelation(@RequestBody JSONObject relation) {
        try {
            networkAreaService.deleteNetworkVlanRelation(relation);
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
        return renderSuccess("删除资源关系成功！");
    }

    //获取网络逻辑区域对应的所有vlan
    @GetMapping(value = "/getVlanRelationByAreaId")
    public TableDataInfo getVlanRelationByAreaId(String resId) {
        return networkAreaService.getVlanRelationByAreaId(resId);
    }


    /**
     * 根据网络逻辑区域查单种资源列表 (CLOUD_VM_NOVA,CLOUD_HOST,SYS_X86Server)
     */
    @GetMapping(value = "/getInstanceByNetworkArea")
    public TableDataInfo getInstanceByNetworkArea(String className, String networkArea) {
        startPage();
        List instanceList = new ArrayList<>();
        if (className.equals(vmClassName)) {
            CloudVmare cloudVmare = new CloudVmare();
            cloudVmare.setNetworkArea(networkArea);
            instanceList = cloudVmareService.getCloudvmList(cloudVmare);
        } else if (className.equals(physicalHostClassName)) {
            PhysicalHost physicalHost = new PhysicalHost();
            physicalHost.setNetworkArea(networkArea);
            instanceList = physicalHostService.getAllPhysicalHost(physicalHost);
        } else if (className.equals(physicalServerClassName)) {
            PhysicalMachine physicalMachine = new PhysicalMachine();
            physicalMachine.setNetworkArea(networkArea);
            instanceList = physicalMachineService.getPhysicalList(physicalMachine);
        }
        return getDataTable(instanceList);
    }

    /**
     * 根据网络逻辑区域查全部资源列表
     */
    @GetMapping(value = "/getAllInstanceByNetworkArea")
    public TableDataInfo getAllInstanceByNetworkArea(String networkArea) {
        startPage();
        CloudVmare cloudVmare = new CloudVmare();
        cloudVmare.setNetworkArea(networkArea);
        List<CloudVmare> allInstanceList = cloudVmareService.getAllResourceList(cloudVmare);
        return getDataTable(allInstanceList);
    }

    /**
     * excel导入vlan
     */
    @PostMapping(value = "/updateVlanInstanceByExcel")
    public JsonResult updateVlanInstanceByExcel(@RequestParam(value = "file") MultipartFile file) {
        BaseReadByLineListener<VlanPlan> readListener = new BaseReadByLineListener<VlanPlan>() {
            @Override
            public void process(VlanPlan vlanPlan) {
                vlanService.updateVlanInstanceByExcel(vlanPlan);
            }
        };
        return renderUploadExcelFileResult(file, readListener, VlanPlan.class, 0, 1);
    }

    /**
     * excel导出vlan
     */
    @PostMapping(value = "/exportVlanInstanceByResId")
    public void exportVlanInstance(HttpServletResponse response, @RequestBody List<String> resIdList, VlanPlan vlanPlan) {
        List<VlanPlan> vlanPlanList = vlanService.getExportVlanInstanceInfo(resIdList, vlanPlan);
        ExcelUtil<VlanPlan> util = new ExcelUtil<>(VlanPlan.class);
        util.hideColumn("resId", "last_Modified");
        util.exportExcel(response, vlanPlanList, "VLAN子网");
    }

    @GetMapping(value = "/getDetailCount")
    public JsonResult getDetailCount() {
        JSONObject count = new JSONObject();
        try {
            count = networkAreaService.getDetailCount();
        } catch (Exception e) {
            return renderError("获取部署环境及归属单位分组数量失败");
        }
        return renderSuccess(count);
    }

    /**
     * oc网络逻辑区域同步上报
     */
    @PostMapping("executeNetworkLogicAreaSynchronization")
    public JsonResult executeNetworkLogicAreaSynchronization() {
        return renderSuccess(networkAreaService.executeNetworkAreaSynchronization());
    }

    /**
     * oc Vlan同步上报
     */
    @PostMapping("executeVlanPlanSynchronization")
    public JsonResult executeVlanPlanSynchronization() {
        return renderSuccess(vlanService.executeVlanPlanSynchronization());
    }
}
