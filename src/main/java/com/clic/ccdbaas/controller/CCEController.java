package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.CCEService;
import com.clic.ccdbaas.service.OcService;
import com.clic.ccdbaas.utils.JsonResult;
import com.clic.ccdbaas.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/v1/cce")
public class CCEController extends CloudBaseController {
    @Autowired
    CCEService cceService;

    @Value("${oc.cmdb.cce.cluster}")
    private String clusterClassName;
    @Value("${oc.cmdb.cce.workload}")
    private String workloadClassName;
    @Value("${oc.cmdb.cce.node}")
    private String nodeClassName;
    @Value("${oc.cmdb.cce.pod}")
    private String podClassName;
    @Value("${oc.cmdb.cce.service}")
    private String serviceClassName;
    @Value("${oc.cmdb.cce.container}")
    private String containerClassName;

    /**
     * 分页获取容器集群数据
     * @param queryJSON
     * @return
     */
    @PostMapping(value = "/getCCEClusterByPage")
    public TableDataInfo getCCEClusterByPage(@RequestBody JSONObject queryJSON) {
        Set<String> keySet = queryJSON.keySet();
        String name = "", value = "";
        for (String key : keySet) {
            if (!key.equals("pageNum") && !key.equals("pageSize")) {
                name = key;
                value = queryJSON.getString(key);
                break;
            }
        }
        int pageNum = queryJSON.getInteger("pageNum");
        int pageSize = queryJSON.getInteger("pageSize");

        JSONObject jsonObject = cceService.getClusterListInfo(pageNum, pageSize, name, value);
        JSONArray objList = jsonObject.getJSONArray("objList");
        int totalNum = jsonObject.getInteger("totalNum");
        return getOcDataTable(objList, totalNum);
    }

    /**
     * 分页获取容器工作负载数据
     * @param queryJSON
     * @return
     */
    @PostMapping(value = "/getWorkLoadByPage")
    public TableDataInfo getWorkLoadByPage(@RequestBody JSONObject queryJSON){
        Set<String> keySet = queryJSON.keySet();
        String name = "", value = "";
        for (String key : keySet) {
            if (!key.equals("pageNum") && !key.equals("pageSize")) {
                name = key;
                value = queryJSON.getString(key);
                break;
            }
        }
        int pageNum = queryJSON.getInteger("pageNum");
        int pageSize = queryJSON.getInteger("pageSize");

        JSONObject jsonObject = cceService.getWorkloadListInfo(pageNum, pageSize, name, value);
        JSONArray objList =jsonObject.getJSONArray("objList");
        int totalNum = jsonObject.getInteger("totalNum");
        return getOcDataTable(objList, totalNum);
    }
}
