package com.clic.ccdbaas.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.clic.ccdbaas.BaseController;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.service.*;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.RedisClientUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/v1/statistics"})
public class ResourceCountController extends BaseController {

    @Autowired
    private CloudVmareService cloudVmareService;
    @Autowired
    private PhysicalHostService physicalHostService;
    @Autowired
    private PhysicalMachineService physicalMachineService;
    @Autowired
    private VlanService vlanService;
    @Autowired
    private NetworkAreaService networkAreaService;


    @GetMapping("/nonCompliantResource")
    public JsonResult countNonCompliant() {
        Map<String, Integer> statistic = new HashMap<>();
        // 未监控（zabbix）
        statistic.put("nonCompliantZbx", cloudVmareService.countNonCompliantZbxStatus());
        // hids异常
        statistic.put("nonCompliantHids", cloudVmareService.countNonCompliantHidsStatus());
        // mainIp缺失
        statistic.put("nullMainIp", cloudVmareService.countNonCompliantMainIp());
        // 未归属产品
        statistic.put("nullBelongProduct", cloudVmareService.countNoncompliantAttr("productToken", "productToken", "productToken"));
        // 主机运行团队缺失
        statistic.put("nullSysAdminTeam", cloudVmareService.countNoncompliantAttr("sysAdminTeam", "sysAdminTeam", "sysAdminTeam"));
        // 主机管理员缺失
        statistic.put("nullSysAdministrator", cloudVmareService.countNoncompliantAttr("sysAdministrator", "sysAdministrator", "sysAdministrator"));
        // 部署环境缺失
        statistic.put("nullDeployEnv", cloudVmareService.countNoncompliantDeployEnv());
        // 操作系统缺失
        statistic.put("nullDeployOs", cloudVmareService.countNoncompliantAttrActive("deployOs", "deployOs", "deployOs"));
        // 资源分配代码缺失
        statistic.put("nullResourceCode", cloudVmareService.countNoncompliantAttr("resourceCode", "resourceCode", "resourceCode"));
        // CPU缺失
        statistic.put("nullCPU", cloudVmareService.countNoncompliantAttr("cpuCoreNum", "cpuCores", "totalVcpuCores"));
        // 内存缺失
        statistic.put("nullMemory", cloudVmareService.countNoncompliantAttr("memorySize", "memoryCapacity", "totalMemory"));
        // 存储盘缺失
        statistic.put("nullDisk", cloudVmareService.countNoncompliantAttr("diskCapacity", "diskCapacity", "totalDiskSizeMB"));
        // 物理位置缺失
        statistic.put("nullPhysicalLocation", cloudVmareService.countNoncompliantAttr(null, "locationCode", "locationCode"));
        // BMC IP缺失
        statistic.put("nullBMCIP", cloudVmareService.countNoncompliantAttr(null, "mgmtIp", "bmcIp"));
        // 序列号缺失
        statistic.put("nullSN", cloudVmareService.countNoncompliantAttr(null, "sn", "sn"));
        // 未接入4A
        statistic.put("nonCompliant4A", 0);
        return renderSuccess(statistic);
    }

    @GetMapping("/MainResource")
    public JsonResult countMainResource() {
        Map<String, Integer> resources = new HashMap<>();
        resources.put("cloudVmCount", cloudVmareService.getCloudVmareCount());
        resources.put("hostCount", physicalHostService.getPhysicalHostCount());
        resources.put("physicalCount", physicalMachineService.getPhysicalCount());
        resources.put("vdeskVM", cloudVmareService.getVdeskVMCount());
        resources.put("bmsCount", cloudVmareService.getBmsCount());
        resources.put("safeEquipmentCount", cloudVmareService.getSafeEquCount());
        return renderSuccess(resources);
    }

    @GetMapping("/LocationResource")
    public JsonResult countLocationResource() {
        Map<String, Integer> resources = new HashMap<>();
        resources.put("vlanCount", vlanService.getVlanCount());
        resources.put("networkCount", networkAreaService.getNetworkAreaCount());
        return renderSuccess(resources);
    }


    @GetMapping(value = "/getResCountInfo")
    public JsonResult getResCountInfo(CloudVmare cloudVmare) {
        return renderSuccess(cloudVmareService.getResCountInfo());

    }

    @GetMapping(value = "/getResProportion")
    public JsonResult getResProportion() {
        return renderSuccess(cloudVmareService.getResProportion());

    }


    @GetMapping(value = "/getChangeRecordCountInfo")
    public JsonResult getChangeRecordCountInfo() {
        return renderSuccess(cloudVmareService.getChangeRecordCountInfo());

    }


    @GetMapping(value = "/getTopProductHostInfo")
    public JsonResult getTopProductHostInfo(@RequestParam(required = false) String teamName) {
        return renderSuccess(cloudVmareService.getTopProduct(teamName));

    }


    @GetMapping(value = "/getAllResCountInfo")
    public JsonResult getAllResCountInfo() {
        return renderSuccess(cloudVmareService.getAllResCountInfo());

    }


    @GetMapping(value = "/getResTrendInfo")
    public JsonResult getResTrendInfo() {
        String resStr = null;
        try {
            resStr = RedisClientUtil.jedis.get("getResTrendInfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!Strings.isBlank(resStr)) {
            JSONObject objJson = JSON.parseObject(resStr);
            return renderSuccess(objJson);
        }
        Object res = cloudVmareService.getResTrendInfo();
        //res转json
        String jsonStrRes = JSON.toJSONString(res);
        //2 hours
        RedisClientUtil.jedis.setex("getResTrendInfo", 7200, jsonStrRes);
        JSONObject objJson = JSON.parseObject(jsonStrRes);
        return renderSuccess(objJson);
    }


}
