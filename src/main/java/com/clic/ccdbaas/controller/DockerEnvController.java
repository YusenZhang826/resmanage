package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.DockerService;
import com.clic.ccdbaas.entity.ObCluster;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.service.DockerEnvService;
import com.clic.ccdbaas.service.ObClusterService;
import com.clic.ccdbaas.service.ObTenantService;
import com.clic.ccdbaas.utils.AESUtils;
import com.clic.ccdbaas.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/dockerEnv")
public class DockerEnvController extends CloudBaseController {
    @Autowired
    private DockerEnvService dockerService;


    @GetMapping("/dealDockerEnvInfo")
    public void dealDockerEnvInfo(){
        dockerService.saveAllDockerEnv();
    }

    /**
     * 根据环境信息获取所有关联的主机
     * @param envId
     * @return
     */
    @GetMapping("/getHostByDockerEnv")
    public TableDataInfo getHostByDockerEnv(@RequestParam String envId){
        startPage();
        List<CloudVmare> cloudvmList  =  dockerService.getRemoteEnvHostJSONArray(envId);

        // AsyncManager.me().execute(AsyncFactory.recordOper("123456","17710461",1,"主机管理员A","陈建华","陈建华",1));
        return getDataTable(cloudvmList);

    }

}
